package RSA.encryption;

import java.math.BigInteger;

public interface GetFileForEncryption {

   void getFileForEncryption(String initialFail, String encryptionFile);

   void getFileForEncryption(byte[] message,  String encryptionFile);

   BigInteger[] getFileForEncryption(String initialFail);

   BigInteger[] getFileForEncryption(byte[] message);



}
