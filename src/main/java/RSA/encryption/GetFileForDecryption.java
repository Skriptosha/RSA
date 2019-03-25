package RSA.encryption;

import java.math.BigInteger;

public interface GetFileForDecryption {

    void getFileForDecryption(String initialFail, String decryptionFile);

    void getFileForDecryption(BigInteger[] encode,  String decryptionFile);

    byte[] getFileForDecryption(String initialFail);

    byte[] getFileForDecryption(BigInteger[] encode);
}
