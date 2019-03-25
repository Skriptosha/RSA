package RSA.encryption;

import RSA.Parameters;
import RSA.Timer;
import RSA.generateKeys.SaveFile;
import RSA.generateKeys.SaveFileWin;

import java.math.BigInteger;
import java.util.ArrayList;

public class GetFileForDecWin implements GetFileForDecryption {
    private DecryptionRSA decryptionRSA = new DecryptionRSA();
    private ReadFile readFile = new ReadFileWin();
    private ZipFileInterface zipFile = new ZipFileWin();
    private SaveFile saveFile = new SaveFileWin();
    private ArrayList<String> list;
    private BigInteger[] dec;
    private byte[] bytes;
    private Timer timer = new Timer();

    @Override
    public void getFileForDecryption(String initialFail, String decryptionFile) {
        timer.start();
        list = (ArrayList<String>) zipFile.unZipFile(initialFail, Parameters.Encode);
        dec = new BigInteger[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dec[i] = new BigInteger(list.get(i), 16);
        }
        System.out.println("getFileForDecryption timer: " + timer.stopTimeMillis());
        bytes = decryptionRSA.decryptionRSA(dec);
        timer.start();
        saveFile.saveFile(decryptionFile, bytes);
        System.out.println("getFileForDecryption saveFile timer: " + timer.stopTimeMillis());
    }

    @Override
    public void getFileForDecryption(BigInteger[] encode, String decryptionFile) {
        bytes = decryptionRSA.decryptionRSA(encode);
        timer.start();
        saveFile.saveFile(decryptionFile, bytes);
        System.out.println("getFileForDecryption saveFile timer: " + timer.stopTimeMillis());
    }

    @Override
    public byte[] getFileForDecryption(String initialFail) {
        timer.start();
        list = (ArrayList<String>) zipFile.unZipFile(initialFail, Parameters.Encode);
        System.out.println("getFileForDecryption timer: " + timer.stopTimeMillis());
        dec = new BigInteger[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dec[i] = new BigInteger(list.get(i), 16);
        }
        return decryptionRSA.decryptionRSA(dec);
    }

    @Override
    public byte[] getFileForDecryption(BigInteger[] encode) {
        return decryptionRSA.decryptionRSA(encode);
    }
}
