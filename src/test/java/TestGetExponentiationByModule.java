import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rsa.math.interfaces.ExponentiationByModule;
import tools.GetConfig;

import java.math.BigInteger;

@Component
public class TestGetExponentiationByModule extends BaseTestClass{

    @Autowired
    private ExponentiationByModule exponentiationByModule;

    @Test
    public void testGetExponentiationByModule() {
        BigInteger result;
        result = exponentiationByModule.getExponentiationByModule(new BigInteger(GetConfig.getProperty("c"))
                , new BigInteger(GetConfig.getProperty("d"))
                , new BigInteger(GetConfig.getProperty("p")).multiply(new BigInteger(GetConfig.getProperty("q"))));
        Assert.assertEquals(result.toString(), GetConfig.getProperty("result"));

        result = exponentiationByModule.getExponentiationByModule(new BigInteger(GetConfig.getProperty("c"))
                , new BigInteger(GetConfig.getProperty("d"))
                , new BigInteger(GetConfig.getProperty("p"))
                , new BigInteger(GetConfig.getProperty("q"))
        , new BigInteger(GetConfig.getProperty("p")).multiply(new BigInteger(GetConfig.getProperty("q"))));
        Assert.assertEquals(result.toString(), GetConfig.getProperty("result"));
    }

}
