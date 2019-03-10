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

public class ReadPublicKey {
    private String e;
    private String n;

    public String getE() {
        return e;
    }

    public String getN() {
        return n;
    }

    public boolean readPublicKey(){
        List<String> lines1;

        try {
            lines1 = new ReadFileWin().readFile(Parameters.SavePublicKey1Path, Parameters.Encode);
            if (new HashAlgorithmMD5().isHashEquals(lines1.get(3), lines1.get(0) + lines1.get(1)
                    + lines1.get(2))) {
                e = new String(Base64.getDecoder().decode(lines1.get(0)), Parameters.Encode);
                String date = new String(Base64.getDecoder().decode(lines1.get(2)), Parameters.Encode);
                //Add check for length
                n = new String(Base64.getDecoder().decode(lines1.get(1)), Parameters.Encode);
                //Add check for length
                if (!isDateFormat(date) || !LocalDate.now().isBefore(LocalDate.parse(date))) {
                    throw new DateTimeException("");
                }
            } else {
                System.out.println("HashAlgorithmMD5 Exception");
                throw new Exception();
            }
        } catch (Exception e1) {
            GenerateKeysRSA generateKeysRSA = new GenerateKeysRSA();
            generateKeysRSA.generateKeysRSA(Parameters.keyLength);
            System.out.println("Exception e1");
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
