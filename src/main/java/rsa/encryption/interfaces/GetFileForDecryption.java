package rsa.encryption.interfaces;

import java.math.BigInteger;

/**
 * Расшифровка шифротекста из файла или массива BigInteger.
 * Файловые операции реализуются через интерфейсы ReadFile и SaveFile
 */
public interface GetFileForDecryption {

    void getFileForDecryption(String initialFail, String decryptionFile);

    void getFileForDecryption(BigInteger[] encode,  String decryptionFile);

    byte[] getFileForDecryption(String initialFail);

    byte[] getFileForDecryption(BigInteger[] encode);
}
