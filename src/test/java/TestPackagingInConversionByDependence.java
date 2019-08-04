import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rsa.logic.ConversionByDependenceOfByte;
import rsa.logic.MixByte;

import java.math.BigInteger;
import java.util.Random;

public class TestPackagingInConversionByDependence extends BaseTestClass {

    @Autowired
    private MixByte mixByte;

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
