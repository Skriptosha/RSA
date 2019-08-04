package rsa.generateKeys.interfaces;

import java.math.BigInteger;

public interface GetPrimeNumber {

    BigInteger getPrimeNumber();

    void setBits(int bits);
}
