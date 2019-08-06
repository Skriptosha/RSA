package rsa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rsa.config.ConfigSpring;
import rsa.encryption.interfaces.GetFileForDecryption;
import rsa.encryption.interfaces.GetFileForEncryption;
import tools.GetConfig;

@Component
public class DoThis {

    @Autowired
    private GetFileForEncryption encryption;

    @Autowired
    private GetFileForDecryption decryption;

    @Autowired
    private ConfigSpring configSpring;

    public static void main(String[] args) {
        GetConfig.setNameProperties("rsa");
        new DoThis().run();
    }

    private void run(){
        Timer timer = new Timer(), timer2 = new Timer();

        String EncText = "test/EncText.txt";
        String Text = "test/Text.txt";
        String DecText = "test/TextDec.txt";
        for (int i = 0; i < 1; i++) {
            timer.start();
            encryption.getFileForEncryption(Text, EncText);
            System.out.println("DoThis getFileForEncryption timer: " + timer.stopTimeMillis());
            timer2.start();
            decryption.getFileForDecryption(EncText, DecText);
            System.out.println("DoThis getFileForDecryption timer: " + timer2.stopTimeMillis());
        }
    }
}
