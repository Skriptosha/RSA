package RSA.generateKeys;

import java.math.BigInteger;

public interface GetPrimeNumber {

    BigInteger getPrimeNumber();

    void setBits(int bits);
}
