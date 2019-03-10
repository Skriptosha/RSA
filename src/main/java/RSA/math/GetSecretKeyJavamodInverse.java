package RSA.math;

import java.math.BigInteger;

public class GetSecretKeyJavamodInverse implements GetSecretKey {
    @Override
    public BigInteger getSecretKey(BigInteger e, BigInteger euler) {
        return e.modInverse(euler);
    }
}
