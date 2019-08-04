package rsa.encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rsa.Timer;
import rsa.encryption.interfaces.GetFileForDecryption;
import rsa.encryption.interfaces.ReadFile;
import rsa.encryption.interfaces.ZipFileInterface;
import rsa.encryption.realization.DecryptionRSA;
import rsa.encryption.realization.ReadFileWin;
import rsa.encryption.realization.ZipFileWin;
import rsa.generateKeys.interfaces.SaveFile;
import rsa.generateKeys.realization.SaveFileWin;

import java.math.BigInteger;

/**
 * Реализация интерефейса GetFileForDecryption для Windows
 */
@Component
public class GetFileForDecWin implements GetFileForDecryption {

    @Autowired
    private DecryptionRSA decryptionRSA;

    private ReadFile readFile = new ReadFileWin();
    private ZipFileInterface zipFile = new ZipFileWin();
    private SaveFile saveFile = new SaveFileWin();
    private BigInteger[] dec;
    private byte[] bytes;
    private Timer timer = new Timer();

    /**
     * Чтение и сохранение через файловые операции
     *
     * @param initialFail путь к шифротексту
     * @param decryptionFile путь для сохранения расшифрованного файла
     */
    @Override
    public void getFileForDecryption(String initialFail, String decryptionFile) {
        timer.start();
        bytes = zipFile.unZipFile(initialFail);
        dec = zipFile.convertByte(bytes);
        System.out.println("getFileForDecryption timer: " + timer.stopTimeMillis());
        bytes = decryptionRSA.decryptionRSA(dec);
        timer.start();
        saveFile.saveFile(decryptionFile, bytes);
        System.out.println("getFileForDecryption saveFile timer: " + timer.stopTimeMillis());
    }

    /**
     * Чтение массива, сохранение через файловую операцию
     *
     * @param encode массив шифротекста BigInteger
     * @param decryptionFile уть для сохранения расшифрованного файла
     */
    @Override
    public void getFileForDecryption(BigInteger[] encode, String decryptionFile) {
        bytes = decryptionRSA.decryptionRSA(encode);
        timer.start();
        saveFile.saveFile(decryptionFile, bytes);
        System.out.println("getFileForDecryption saveFile timer: " + timer.stopTimeMillis());
    }

    /**
     * Чтение через файловую операцию, сохранение в массив байт
     *
     * @param initialFail путь к шифротексту
     * @return массив байт
     */
    @Override
    public byte[] getFileForDecryption(String initialFail) {
        timer.start();
        bytes =  zipFile.unZipFile(initialFail);
        System.out.println("getFileForDecryption timer: " + timer.stopTimeMillis());
        dec = zipFile.convertByte(bytes);
        return decryptionRSA.decryptionRSA(dec);
    }

    /**
     * Чтение и сохранение через массивы байт и BigInteger
     *
     * @param encode массив шифротекста BigInteger
     * @return массив байт
     */
    @Override
    public byte[] getFileForDecryption(BigInteger[] encode) {
        return decryptionRSA.decryptionRSA(encode);
    }
}
