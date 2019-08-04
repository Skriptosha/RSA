package rsa.logic;

import java.math.BigInteger;

public class BigIntegerArrayToByteArray {

    /**
     * Распаковка BigInteger[] в byte[]
     *
     * @param bigIntegers массив BigInteger[]
     * @param numberOfBits количество byte, которое содержится в одном BigInteger
     * @return массив byte[]
     */
    public byte[] bigIntegerArrayToByteArray(BigInteger[] bigIntegers, int numberOfBits) {
        byte[] bytes;
        byte[] temp;
        if (bigIntegers[bigIntegers.length - 1].toByteArray().length == numberOfBits)
            bytes = new byte[bigIntegers.length * numberOfBits];
        else bytes = new byte[(bigIntegers.length - 1) * numberOfBits
                + bigIntegers[bigIntegers.length - 1].toByteArray().length];
        int c = 0;
        for (BigInteger bigInteger : bigIntegers) {
            temp = bigInteger.toByteArray();
            for (int i = 0; i < temp.length; i++) {
                if (i < numberOfBits) {
                    bytes[c] = temp[i];
                    c++;
                }
                if (i == 0 && temp.length > numberOfBits) {
                    bytes[c - 1] = (byte) (temp[i] - 2);
                }
            }
        }
        System.out.println("c: " + c);
        return bytes;
    }
}
