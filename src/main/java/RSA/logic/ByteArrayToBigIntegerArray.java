package RSA.logic;

import java.math.BigInteger;

public class ByteArrayToBigIntegerArray {
    public BigInteger[] byteArrayToBigIntegerArray(byte[] bytes, int bitness) {
        byte[] temp;
        byte[] tempNormal = new byte[bitness];
        byte[] tempIncreased = new byte[bitness + 1];
        int size;
        boolean isInteger;
        int overflow = bytes.length - (bytes.length % bitness);
        if (bytes.length % bitness == 0) {
            size = bytes.length / bitness;
            isInteger = true;
        } else {
            size = bytes.length / bitness + 1;
            isInteger = false;
        }
//        System.out.println(bytes.length);
        BigInteger[] resultBigInt = new BigInteger[size];
        int j = 0, c = 0;
        temp = tempNormal;
        for (int i = 0; i < overflow; i++) {
            if (c == 0 && (bytes[i] == 0 || bytes[i] == -1)) {
                temp = tempIncreased;
                temp[bitness] = 1;
                temp[c] = (byte)(bytes[i] + 2);
            }
            else temp[c] = bytes[i];
                if (c == bitness - 1) {
                resultBigInt[j] = new BigInteger(temp);
//                if (resultBigInt[j].toByteArray().length < bitness) {
////                    System.out.println("resultBigInt[j].bitLength(): " + resultBigInt[j].bitLength());
//                    System.out.println("resultBigInt[" + j + "].toByteArray().length != CoefficientBit");
//                    for (byte b : temp) {
//                        System.out.print(b + ";");
//                    }
//                    System.out.println();
//                }
                j++;
                c = 0;
                if (temp.length == bitness + 1) temp = tempNormal;
            }
            else c++;
        }
//        System.out.println("resultBigInt[j].bitLength(): " + resultBigInt[0].bitLength());
        if (!isInteger) {
            temp = new byte[bytes.length % bitness];
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
