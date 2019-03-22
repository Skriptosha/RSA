package RSA;

import RSA.encryption.GetFileForDecWin;
import RSA.encryption.GetFileForDecryption;
import RSA.encryption.GetFileForEncWin;
import RSA.encryption.GetFileForEncryption;
import tools.GetConfig;

import java.math.BigInteger;
import java.util.Random;

public class DoThis {
    public static void main(String[] args) {
        GetConfig.setNameProperties("TestProp");
        Timer timer = new Timer(), timer2 = new Timer();

        String EncText = "test/EncText.txt";
        String Text = "test/OperaSetup.exe";
        String DecText = "test/OperaSetupDec.exe";
        for (int i = 0; i < 1; i++) {
            GetFileForDecryption decryption = new GetFileForDecWin();
            GetFileForEncryption encryption = new GetFileForEncWin();
            byte[] message = new byte[240];
            new Random().nextBytes(message);
            timer.start();
            BigInteger[] dec = encryption.getFileForEncryption(message);
            System.out.println("DoThis getFileForEncryption timer: " + timer.stopTimeMillis());
            timer2.start();
            decryption.getFileForDecryption(dec, DecText);
            System.out.println("DoThis getFileForDecryption timer: " + timer2.stopTimeMillis());
        }
    }
}
