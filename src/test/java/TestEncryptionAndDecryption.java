import org.junit.Assert;
import org.junit.Test;
import rsa.encryption.GetFileForDecWin;
import rsa.encryption.GetFileForEncWin;
import rsa.encryption.interfaces.GetFileForDecryption;
import rsa.encryption.interfaces.GetFileForEncryption;
import tools.GetConfig;

import java.math.BigInteger;
import java.util.Random;

public class TestEncryptionAndDecryption extends BaseTestClass {
    private BigInteger[] enc;

    @Test
    public void testEncryptionAndDecryption(){
        byte[] bytes = new byte[Integer.parseInt(GetConfig.getProperty("ArraySize"))];
        byte[] result;
        new Random().nextBytes(bytes);
        GetFileForDecryption getFileForDecryption = new GetFileForDecWin();
        GetFileForEncryption getFileForEncryption = new GetFileForEncWin();
        enc = getFileForEncryption.getFileForEncryption(bytes);
        result = getFileForDecryption.getFileForDecryption(enc);
        Assert.assertEquals(bytes.length, result.length);
        for (int i = 0; i < bytes.length; i++) {
            Assert.assertEquals(bytes[i], result[i]);
        }
    }
}
