package RSA.math;

import java.math.BigInteger;

public interface IsNumberPrime {

    boolean isNumberPrime(BigInteger primeNumber, long marsenneDegree);

    String getNameAlgorithmToFind();
}
