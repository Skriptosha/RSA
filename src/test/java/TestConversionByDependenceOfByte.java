import org.junit.Assert;
import org.junit.Test;
import rsa.encryption.realization.ReadPrivateKey;
import rsa.logic.ConversionByDependenceOfByte;
import tools.GetConfig;

import java.math.BigInteger;
import java.util.Random;

public class TestConversionByDependenceOfByte extends BaseTestClass {

    @Test
    public void testConversionByDependenceOfByte(){
        ConversionByDependenceOfByte conversion = new ConversionByDependenceOfByte();
        ReadPrivateKey readPrivateKey = new ReadPrivateKey();
        readPrivateKey.readPrivateKey();
        byte[] bytes = new byte[Integer.parseInt(GetConfig.getProperty("ArraySize"))];
        new Random().nextBytes(bytes);
        byte[] result;
        BigInteger n = new BigInteger(readPrivateKey.getP(), 16)
                .multiply(new BigInteger(readPrivateKey.getQ(), 16));
        BigInteger[] bigs = conversion.mixByte(bytes, n);
        result = conversion.unMixByte(bigs, n);
        Assert.assertEquals(bytes.length, result.length);
        for (int i = 0; i < bytes.length; i++) {
            Assert.assertEquals(bytes[i], result[i]);
        }
    }
}
