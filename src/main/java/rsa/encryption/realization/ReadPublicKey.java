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
public class ReadPublicKey {
    private String e;
    private String n;

    @Autowired
    private ReadFile readFile;

    @Autowired
    private GenerateKeysRSA generateKeysRSA;

    public String getE() {
        return e;
    }

    public String getN() {
        return n;
    }

    public boolean readPublicKey(){
        List<String> lines1;
        try {
            lines1 = readFile.readFile(Parameters.SavePublicKey1Path, Parameters.Encode);
            if (new HashAlgorithmMD5().isHashEquals(lines1.get(3), lines1.get(0) + lines1.get(1)
                    + lines1.get(2))) {
                e = new String(Base64.getDecoder().decode(lines1.get(0)), Parameters.Encode);
                //Add check for length
                String date = new String(Base64.getDecoder().decode(lines1.get(2)), Parameters.Encode);

                n = new String(Base64.getDecoder().decode(lines1.get(1)), Parameters.Encode);
                //Add check for length
                if (!isDateFormat(date) || !LocalDate.now().isBefore(LocalDate.parse(date))) {
                    throw new DateTimeException("Срок действия ключа истек");
                }
            } else {
                throw new IllegalStateException("Не соответствуют контрольные суммы.");
            }
        } catch (Exception e1) {
//            return false;
            System.out.println("Exception e1");
            generateKeysRSA.generateKeysRSA(Parameters.keyLength);
            e = generateKeysRSA.getPublicKey()[0];
            n = generateKeysRSA.getPublicKey()[1];
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
