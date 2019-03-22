package RSA.math;

import java.math.BigInteger;

public interface ExponentiationByModule {

    BigInteger getExponentiationByModule(BigInteger basic, BigInteger degree, BigInteger module);

    BigInteger getExponentiationByModule (BigInteger basic, BigInteger degree, BigInteger moduleP
            , BigInteger moduleQ, BigInteger module);

}
