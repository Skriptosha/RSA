import org.junit.Assert;
import org.junit.Test;
import rsa.logic.GetUniqueFromArray;
import tools.GetConfig;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class TestGetUniqueFromArray extends BaseTestClass {

    @Test
    public void testGetUniqueFromArray(){
        BigInteger[] dec = new BigInteger[Integer.parseInt(GetConfig.getProperty("Count"))];

        GetUniqueFromArray uniqueFromArray = new GetUniqueFromArray();
        Random random = new Random();
        for (int i = 0; i < dec.length; i++) {
            dec[i] = new BigInteger(8, random);
        }
        ArrayList<BigInteger> uniqueList = uniqueFromArray.getUniqueFromArray(dec);
        ArrayList<BigInteger> after = new ArrayList<>(uniqueList.size());
        for (BigInteger anUniqueList : uniqueList) {
            after.add(anUniqueList.multiply(BigInteger.ZERO));
        }

        uniqueList = uniqueFromArray.fillArrayFromUniqueBigInteger(dec,after);
        Assert.assertEquals(dec.length, uniqueList.size());
        for (BigInteger a : uniqueList) {
            Assert.assertNotNull(a);
        }
    }

}
