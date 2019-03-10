package RSA.math;

import java.math.BigInteger;

public interface ExponentiationByModule {

    void exponentiationByModule();

    BigInteger getExponentiationByModule();

    void setParameters(BigInteger basic, BigInteger degree, BigInteger module);
}
