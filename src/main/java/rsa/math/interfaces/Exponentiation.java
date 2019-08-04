package rsa.math.interfaces;

import java.math.BigInteger;

/**
 * Возведение в степень degree числа basic
 */
public interface Exponentiation {

    BigInteger exponentiationNumber(BigInteger basic, BigInteger degree);

}
