import RSA.math.ExponentiationByModule;
import org.junit.Assert;
import org.junit.Test;
import tools.GetConfig;

import java.math.BigInteger;

public class TestGetExponentiationByModule extends BaseTestClass{

    @Test
    public void testGetExponentiationByModule() {
        BigInteger result;
        ExponentiationByModule exp = null;
        try {
            exp = (ExponentiationByModule) Class.forName(GetConfig.getProperty("ExponentiationByModuleClass"))
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert exp != null;
        result = exp.getExponentiationByModule(new BigInteger(GetConfig.getProperty("c"))
                , new BigInteger(GetConfig.getProperty("d"))
                , new BigInteger(GetConfig.getProperty("p")).multiply(new BigInteger(GetConfig.getProperty("q"))));
        Assert.assertEquals(result.toString(), GetConfig.getProperty("result"));

        result = exp.getExponentiationByModule(new BigInteger(GetConfig.getProperty("c"))
                , new BigInteger(GetConfig.getProperty("d"))
                , new BigInteger(GetConfig.getProperty("p"))
                , new BigInteger(GetConfig.getProperty("q"))
        , new BigInteger(GetConfig.getProperty("p")).multiply(new BigInteger(GetConfig.getProperty("q"))));
        Assert.assertEquals(result.toString(), GetConfig.getProperty("result"));
    }

}
