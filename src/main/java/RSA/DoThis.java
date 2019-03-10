package RSA;

import RSA.encryption.DecryptionRSA;
import RSA.encryption.EncryptionRSA;
import RSA.encryption.ReadFile;
import RSA.encryption.ReadFileWin;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DoThis {
    public static void main(String[] args) {
        ReadFile readFile = new ReadFileWin();
        List<String> list = new ArrayList<>();
        Timer timer = new Timer(), timer2 = new Timer();
        timer.start();
        for (int i = 0; i < 1; i++) {
            System.out.println("generateKeysRSA timer: " + timer.stopTimeSeconds());
            EncryptionRSA encryptionRSA = new EncryptionRSA();
            DecryptionRSA decryptionRSA = new DecryptionRSA();
            try {
                list = readFile.readFile("Text.txt", Parameters.Encode);
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (String j : list) {
                stringBuilder.append(j);
            }
            BigInteger[] ints = encryptionRSA.encryptionRSA(stringBuilder.toString());
            System.out.println();
            System.out.println("Расшифрованое сообщение: " + decryptionRSA.decryptionRSA(ints));
        }
    }
}
