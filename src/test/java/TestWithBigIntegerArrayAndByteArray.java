import org.junit.Assert;
import org.junit.Test;
import rsa.logic.BigIntegerArrayToByteArray;
import rsa.logic.ByteArrayToBigIntegerArray;
import rsa.logic.ConversionByDependenceOfByte;
import tools.GetConfig;

import java.math.BigInteger;
import java.util.Random;

public class TestWithBigIntegerArrayAndByteArray extends BaseTestClass {

    @Test
    public void testWithBigIntegerArrayAndByteArray(){
        BigIntegerArrayToByteArray toByteArray = new BigIntegerArrayToByteArray();
        ByteArrayToBigIntegerArray toBigIntegerArray = new ByteArrayToBigIntegerArray();
        byte[] bytes = new byte[Integer.parseInt(GetConfig.getProperty("ArraySize"))];
        new Random().nextBytes(bytes);
        byte[] result;
        ConversionByDependenceOfByte conversion = new ConversionByDependenceOfByte();
        BigInteger[] n = toBigIntegerArray.byteArrayToBigIntegerArray(bytes, conversion.SetPackaging(bytes.length));
        result = toByteArray.bigIntegerArrayToByteArray(n, conversion.SetUnPackaging(n[0]));
        Assert.assertEquals(bytes.length, result.length);
        for (int i = 0; i < bytes.length; i++) {
            Assert.assertEquals("Элементы массива № " + i + " двух массивов не равны", bytes[i], result[i]);
        }
    }
}
