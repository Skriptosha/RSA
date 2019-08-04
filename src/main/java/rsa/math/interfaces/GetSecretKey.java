package rsa.math.interfaces;

import java.math.BigInteger;

/**
 * Вычисление секретного ключа D. Необходимо произвести возведение в степень по модулю
 * с отрицательным показателем степени
 */
public interface GetSecretKey {

    BigInteger getSecretKey(BigInteger e, BigInteger euler);
}
