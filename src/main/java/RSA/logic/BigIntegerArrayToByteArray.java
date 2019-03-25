package RSA.logic;

import java.math.BigInteger;

public class BigIntegerArrayToByteArray {
    public byte[] bigIntegerArrayToByteArray(BigInteger[] bigIntegers, int bitness) {
        byte[] bytes;
        byte[] temp;
        if (bigIntegers[bigIntegers.length - 1].toByteArray().length == bitness)
            bytes = new byte[bigIntegers.length * bitness];
        else bytes = new byte[(bigIntegers.length - 1) * bitness + bigIntegers[bigIntegers.length - 1].toByteArray().length];
//        System.out.println("bytes.length: " + bytes.length);
        int c = 0;
        for (BigInteger bigInteger : bigIntegers) {
            temp = bigInteger.toByteArray();
//            if (temp.length < bitness){
////                System.out.println("temp.length != CoefficientBit");
////                for (byte b : temp) {
////                    System.out.print(b + ";");
////                }
////                System.out.println();
//            }
            for (int i = 0; i < temp.length; i++) {
                if (i < bitness) {
                    bytes[c] = temp[i];
                    c++;
                }
                if (i == 0 && temp.length > bitness) {
                    bytes[c - 1] = (byte) (temp[i] - 2);
                }
            }
        }
        System.out.println("c: " + c);
        return bytes;
    }
}
