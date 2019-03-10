package RSA.logic;

import java.math.BigInteger;

public interface ConversionStringAndByte {

    int[] translatesStringIntoByte(String message, BigInteger n);

    String translatesBytesIntoString(Integer[] ciphertext, BigInteger n);
}
