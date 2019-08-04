package rsa.logic;

import java.math.BigInteger;


/**
 * Так как функция Возведение в степень по модулю является детерминированной, то для практической реализации необходимо
 * применение различных способов замешивания элементов массива байт
 */
public interface MixByte {

    BigInteger[] mixByte(byte[] message, BigInteger n);

    byte[] unMixByte(BigInteger[] cipherText, BigInteger n);
}
