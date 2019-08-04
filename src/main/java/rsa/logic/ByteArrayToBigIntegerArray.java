package rsa.logic;

import java.math.BigInteger;

public class ByteArrayToBigIntegerArray {

    /**
     * Упаковка byte[] в BigInteger[]
     *
     * @param bytes массив byte[] для упаковки
     * @param numberOfBits количество byte, которое будет в одном BigInteger
     * @return получившийся BigInteger[]
     */
    public BigInteger[] byteArrayToBigIntegerArray(byte[] bytes, int numberOfBits) {
        byte[] temp;
        byte[] tempNormal = new byte[numberOfBits];
        byte[] tempIncreased = new byte[numberOfBits + 1];
        int size;
        boolean isInteger;
        int overflow = bytes.length - (bytes.length % numberOfBits);
        if (bytes.length % numberOfBits == 0) {
            size = bytes.length / numberOfBits;
            isInteger = true;
        } else {
            size = bytes.length / numberOfBits + 1;
            isInteger = false;
        }
        BigInteger[] resultBigInt = new BigInteger[size];
        int j = 0, c = 0;
        temp = tempNormal;
        for (int i = 0; i < overflow; i++) {
            if (c == 0 && (bytes[i] == 0 || bytes[i] == -1)) {
                temp = tempIncreased;
                temp[numberOfBits] = 1;
                temp[c] = (byte)(bytes[i] + 2);
            }
            else temp[c] = bytes[i];
                if (c == numberOfBits - 1) {
                resultBigInt[j] = new BigInteger(temp);
                j++;
                c = 0;
                if (temp.length == numberOfBits + 1) temp = tempNormal;
            }
            else c++;
        }
        if (!isInteger) {
            temp = new byte[bytes.length % numberOfBits];
            for (int i = overflow; i < bytes.length; i++) {
                temp[c] = bytes[i];
                if (c == bytes.length - overflow - 1) {
                    resultBigInt[j] = new BigInteger(temp);
                    c = 0;
                } else c++;
            }
        }
        return resultBigInt;
    }
}
