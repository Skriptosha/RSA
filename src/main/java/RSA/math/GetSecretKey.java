package RSA.math;

import java.math.BigInteger;

public interface GetSecretKey {

    BigInteger getSecretKey(BigInteger e, BigInteger euler);
}
