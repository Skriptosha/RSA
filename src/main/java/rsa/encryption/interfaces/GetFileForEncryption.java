package rsa.encryption.interfaces;

import java.math.BigInteger;

/**
 * Создание шифротекста из файла или набора байт.
 * Файловые операции реализуются через интерфейсы ReadFile и SaveFile, zipFile
 */
public interface GetFileForEncryption {

   void getFileForEncryption(String initialFail, String encryptionFile);

   void getFileForEncryption(byte[] message,  String encryptionFile);

   BigInteger[] getFileForEncryption(String initialFail);

   BigInteger[] getFileForEncryption(byte[] message);



}
