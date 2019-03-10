package RSA.encryption;

import RSA.Parameters;
import RSA.generateKeys.GenerateKeysRSA;
import RSA.math.HashAlgorithmMD5;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadPrivateKey {
    private String d;
    private String n;

    public String getD() {
        return d;
    }

    public String getN() {
        return n;
    }

    public boolean readPrivateKey(){
        List<String> lines1;
        List<String> lines2;
        try {
            lines1 = new ReadFileWin().readFile(Parameters.SavePrivateKey1Path, Parameters.Encode);
            lines2 = new ReadFileWin().readFile(Parameters.SavePrivateKey2Path, Parameters.Encode);
            if (new HashAlgorithmMD5().isHashEquals(lines1.get(2), lines1.get(0) + lines1.get(1))
                    && new HashAlgorithmMD5().isHashEquals(lines2.get(1), lines2.get(0))) {
                d = new String(Base64.getDecoder().decode(lines1.get(0)), Parameters.Encode);
                String date = new String(Base64.getDecoder().decode(lines1.get(1)), Parameters.Encode);
                //Add check for length
                n = new String(Base64.getDecoder().decode(lines2.get(0)), Parameters.Encode);
                //Add check for length
                if (!isDateFormat(date) || !LocalDate.now().isBefore(LocalDate.parse(date))) {
                    System.out.println("isDateFormat Exception");
                    throw new DateTimeException("");
                }
            } else{
                System.out.println("HashAlgorithmMD5 Exception");
                throw new Exception();
            }
        } catch (Exception e) {
            GenerateKeysRSA generateKeysRSA = new GenerateKeysRSA();
            generateKeysRSA.generateKeysRSA(Parameters.keyLength);
            System.out.println("Exception e1");
            d = generateKeysRSA.getPrivateKey()[0];
            n = generateKeysRSA.getPrivateKey()[1];
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
