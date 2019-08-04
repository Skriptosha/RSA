package rsa.encryption.realization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rsa.config.Parameters;
import rsa.encryption.interfaces.ReadFile;
import rsa.generateKeys.realization.GenerateKeysRSA;
import rsa.math.realization.HashAlgorithmMD5;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReadPrivateKey {
    private String d;
    private String p;
    private String q;

    @Autowired
    private ReadFile readFile;

    public String getD() {
        return d;
    }

    public String getP() {
        return p;
    }

    public String getQ() {
        return q;
    }

    public boolean readPrivateKey(){
        List<String> lines1;
        List<String> lines2;
        try {
            lines1 = readFile.readFile(Parameters.SavePrivateKey1Path, Parameters.Encode);
            lines2 = readFile.readFile(Parameters.SavePrivateKey2Path, Parameters.Encode);
            if (new HashAlgorithmMD5().isHashEquals(lines1.get(2), lines1.get(0) + lines1.get(1))
                    && new HashAlgorithmMD5().isHashEquals(lines2.get(2), lines2.get(0) + lines2.get(1))) {
                d = new String(Base64.getDecoder().decode(lines1.get(0)), Parameters.Encode);
                String date = new String(Base64.getDecoder().decode(lines1.get(1)), Parameters.Encode);
                //Add check for length
                p = new String(Base64.getDecoder().decode(lines2.get(0)), Parameters.Encode);
                //Add check for length
                q = new String(Base64.getDecoder().decode(lines2.get(1)), Parameters.Encode);
                if (!isDateFormat(date) || !LocalDate.now().isBefore(LocalDate.parse(date))) {
                    throw new DateTimeException("Срок действия ключа истек");
                }
            } else{
                System.out.println("Не соответствуют контрольные суммы.");
                throw new IllegalStateException("Не соответствуют контрольные суммы.");
            }
        } catch (Exception e) {
            GenerateKeysRSA generateKeysRSA = new GenerateKeysRSA();
            generateKeysRSA.generateKeysRSA(Parameters.keyLength);
            System.out.println("Exception e1");
            d = generateKeysRSA.getPrivateKey()[0];
            p = generateKeysRSA.getPrivateKey()[1];
            q = generateKeysRSA.getPrivateKey()[2];
        }
        return true;
    }

    private boolean isDateFormat(String date){
        String regex = "\\d\\d\\d\\d-\\d\\d-\\d\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.find();
    }
}
