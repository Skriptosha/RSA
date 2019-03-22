package RSA.encryption;

import RSA.Parameters;
import RSA.generateKeys.GenerateKeysRSA;
import RSA.math.HashAlgorithmMD5;
import tools.GetConfig;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadPrivateKey {
    private String d;
    private String p;
    private String q;

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
        ReadFile readFile = null;
        try {
            readFile = (ReadFile) Class.forName(GetConfig.getProperty("ReadFile")).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            assert readFile != null;
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
