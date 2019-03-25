package RSA.logic;

import java.math.BigInteger;

public interface MixByte {

    BigInteger[] mixByte(byte[] message, BigInteger n);

    byte[] unMixByte(BigInteger[] cipherText, BigInteger n);
}
