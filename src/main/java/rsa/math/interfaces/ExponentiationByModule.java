package rsa.math.interfaces;

import java.math.BigInteger;

/**
 * Основная операция в RSA - Возведение в степень по модулю.
 */
public interface ExponentiationByModule {

    BigInteger getExponentiationByModule(BigInteger basic, BigInteger degree, BigInteger module);

    BigInteger getExponentiationByModule (BigInteger basic, BigInteger degree, BigInteger moduleP
            , BigInteger moduleQ, BigInteger module);

}
