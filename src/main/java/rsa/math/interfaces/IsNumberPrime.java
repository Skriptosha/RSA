package rsa.math.interfaces;

import java.math.BigInteger;

/**
 * Проверка простое ли число. параметр marsenneDegree может не использоваться.
 * primeNumber - число которое необходимо проверить.
 */
public interface IsNumberPrime {

    boolean isNumberPrime(BigInteger primeNumber, long marsenneDegree);

    String getNameAlgorithmToFind();
}
