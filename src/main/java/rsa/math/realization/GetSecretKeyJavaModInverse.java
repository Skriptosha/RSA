package rsa.math.realization;

import rsa.math.interfaces.GetSecretKey;

import java.math.BigInteger;

/**
 * Вычисление секретного ключа D используя BigInteger.modInverse
 */
public class GetSecretKeyJavaModInverse implements GetSecretKey {
    @Override
    public BigInteger getSecretKey(BigInteger e, BigInteger euler) {
        return e.modInverse(euler);
    }
}
