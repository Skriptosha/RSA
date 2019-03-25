import RSA.logic.ConversionByDependenceOfByte;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

public class TestPackagingInConversionByDependence extends BaseTestClass {

    @Test
    public void testPackagingInConversionByDependence(){
        ConversionByDependenceOfByte conversion = new ConversionByDependenceOfByte();
        int y, x;
        for (int i = 1; i < 99200;i++) {
            y = conversion.SetPackaging(i);
            byte[] bytes = new byte[y];
            do {
                new Random().nextBytes(bytes);
            } while (bytes[0] == 0 || bytes[0] == -1);
            x = conversion.SetUnPackaging(new BigInteger(bytes));
            Assert.assertEquals("BigInteger: " + new BigInteger(bytes).bitLength() + " i: " + i, y, x);
        }
    }
}
