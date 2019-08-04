package rsa.encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rsa.Timer;
import rsa.encryption.interfaces.GetFileForEncryption;
import rsa.encryption.interfaces.ReadFile;
import rsa.encryption.interfaces.ZipFileInterface;
import rsa.encryption.realization.EncryptionRSA;
import rsa.encryption.realization.ReadFileWin;
import rsa.encryption.realization.ZipFileWin;
import rsa.generateKeys.interfaces.SaveFile;
import rsa.generateKeys.realization.SaveFileWin;

import java.math.BigInteger;

/**
 * Реализация интерефейса GetFileForEncryption для Windows
 */
@Component
public class GetFileForEncWin implements GetFileForEncryption {

    @Autowired
    private EncryptionRSA encryptionRSA;

    private ReadFile readFile = new ReadFileWin();
    private SaveFile saveFile = new SaveFileWin();
    private ZipFileInterface zipFile = new ZipFileWin();
    private byte[] bytes;
    private BigInteger[] ints;
    private Timer timer = new Timer();

    /**
     *
     * @param initialFail путь к файлу для шифрования
     * @param encryptionFile путь к шифротексту
     */
    @Override
    public void getFileForEncryption(String initialFail, String encryptionFile) {

        timer.start();
        bytes = readFile.readFile(initialFail);
        System.out.println("getFileForEncryption readFile timer: " + timer.stopTimeMillis());
        ints = encryptionRSA.encryptionRSA(bytes);
        timer.start();
        zipFile.zipFile(encryptionFile, zipFile.convertBigInteger(ints));
        System.out.println("getFileForEncryption timer: " + timer.stopTimeMillis());
    }

    /**
     *
     * @param message массив байт для шифрования
     * @param encryptionFile путь к шифротексту
     */
    @Override
    public void getFileForEncryption(byte[] message, String encryptionFile) {
        ints = encryptionRSA.encryptionRSA(message);
        timer.start();
        zipFile.zipFile(encryptionFile, zipFile.convertBigInteger(ints));
        System.out.println("getFileForEncryption timer: " + timer.stopTimeMillis());
    }

    /**
     *
     * @param initialFail путь к файлу для шифрования
     * @return массив шифротекста
     */
    @Override
    public BigInteger[] getFileForEncryption(String initialFail) {
        timer.start();
        bytes = readFile.readFile(initialFail);
        System.out.println("getFileForEncryption readFile timer: " + timer.stopTimeMillis());
        return encryptionRSA.encryptionRSA(bytes);
    }

    /**
     *
     * @param message массив байт для шифрования
     * @return массив шифротекста
     */
    @Override
    public BigInteger[] getFileForEncryption(byte[] message) {
        return encryptionRSA.encryptionRSA(message);
    }


}
