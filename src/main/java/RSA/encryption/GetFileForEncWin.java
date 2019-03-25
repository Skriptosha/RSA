package RSA.encryption;

import RSA.Parameters;
import RSA.Timer;
import RSA.generateKeys.SaveFile;
import RSA.generateKeys.SaveFileWin;

import java.math.BigInteger;
import java.util.ArrayList;

public class GetFileForEncWin implements GetFileForEncryption {
    private EncryptionRSA encryptionRSA = new EncryptionRSA();
    private ReadFile readFile = new ReadFileWin();
    private SaveFile saveFile = new SaveFileWin();
    private ZipFileInterface zipFile = new ZipFileWin();
    private ArrayList<String> list;
    private byte[] bytes;
    private BigInteger[] ints;
    private Timer timer = new Timer();

    @Override
    public void getFileForEncryption(String initialFail, String encryptionFile) {

        timer.start();
        bytes = readFile.readFile(initialFail);
        System.out.println("getFileForEncryption readFile timer: " + timer.stopTimeMillis());
        ints = encryptionRSA.encryptionRSA(bytes);
        list = new ArrayList<>(ints.length);
        timer.start();
        for (BigInteger i : ints) {
            list.add((i.toString(16)));
        }
        zipFile.zipFile(encryptionFile, list, Parameters.Encode);
        System.out.println("getFileForEncryption timer: " + timer.stopTimeMillis());
    }

    @Override
    public void getFileForEncryption(byte[] message, String encryptionFile) {
        ints = encryptionRSA.encryptionRSA(message);
        list = new ArrayList<>(ints.length);
        timer.start();
        for (BigInteger i : ints) {
            list.add((i.toString(16)));
        }
        zipFile.zipFile(encryptionFile, list, Parameters.Encode);
        System.out.println("getFileForEncryption timer: " + timer.stopTimeMillis());
    }

    @Override
    public BigInteger[] getFileForEncryption(String initialFail) {
        timer.start();
        bytes = readFile.readFile(initialFail);
        System.out.println("getFileForEncryption readFile timer: " + timer.stopTimeMillis());
        return encryptionRSA.encryptionRSA(bytes);
    }

    @Override
    public BigInteger[] getFileForEncryption(byte[] message) {
        return encryptionRSA.encryptionRSA(message);
    }


}
