package RSA.logic;

import RSA.Parameters;
import RSA.Timer;

import java.math.BigInteger;
import java.util.Random;

public class ConversionByDependenceOfByte implements MixByte {

    @Override
    public BigInteger[] mixByte(byte[] message, BigInteger n) {
        Random random = new Random();
        Timer timer = new Timer();
        ByteArrayToBigIntegerArray byteArrayToBigIntegerArray = new ByteArrayToBigIntegerArray();
        BigInteger randomized;
        int bitness = SetPackaging(message.length);
        BigInteger newN = n.setBit(bitness*8);
        randomized = new BigInteger(String.valueOf((message[random.nextInt(message.length - 1)]
                * message[random.nextInt(message.length - 1)]
                * message[random.nextInt(message.length - 1)])
                * (message[random.nextInt(message.length - 1)]
                * message[random.nextInt(message.length - 1)])));
        System.out.println("newN.bitLength(): " + newN.bitLength());
//        for (byte i : message) {
//            System.out.print(i + ";");
//        }
        BigInteger[] temp = byteArrayToBigIntegerArray.byteArrayToBigIntegerArray(message, bitness);
        BigInteger[] result = new BigInteger[temp.length + 1];
//        for (BigInteger i : temp) {
//            System.out.print(i + ";");
//        }
        timer.start();
        System.out.println("temp[2].bitLength(): " + temp[2].bitLength());
        result[0] = new BigInteger(String.valueOf(randomized));
        for (int i = 1; i < result.length; i++) {
            if (i == 1)
                result[i] = (temp[i - 1].add(randomized).add(randomized.divide(BigInteger.TEN))).remainder(newN);
            else if (i == 2) result[i] = (temp[i - 1].add(temp[i - 2]).add(randomized)).remainder(newN);
            else if (i == 3)
                result[i] = (temp[i - 1].add(temp[i - 2]).add(temp[i - 3]).add(randomized)).remainder(newN);
            else result[i] = (temp[i - 1].add(temp[i - 2]).add(temp[i - 3]).add(temp[i - 4]).add(randomized))
                        .remainder(newN);
        }
        System.out.println();
        System.out.println("mixByte общий таймер: " + timer.stopTimeMillis());
        System.out.println("result.length: " + result.length);
        return result;
    }

    @Override
    public byte[] unMixByte(BigInteger[] cipherText, BigInteger n) {

        BigInteger randomized;
        Timer timer = new Timer();
        int bitness = SetUnPackaging(cipherText[2]);
        BigInteger newN = n.clearBit(bitness*8);
        BigIntegerArrayToByteArray bigIntegerArrayToByteArray = new BigIntegerArrayToByteArray();
        System.out.println();
        timer.start();
        randomized = cipherText[0];
        BigInteger[] tempInt = new BigInteger[cipherText.length - 1];

        for (int i = 1; i < cipherText.length; i++) {
            if (i == 1)
                tempInt[i - 1] = (cipherText[i].subtract(randomized).subtract(randomized.divide(BigInteger.TEN)))
                        .remainder(newN);
            else if (i == 2) tempInt[i - 1] = (cipherText[i].subtract(tempInt[i - 2]).subtract(randomized))
                    .remainder(newN);
            else if (i == 3) tempInt[i - 1] = (cipherText[i].subtract(tempInt[i - 2]).subtract(tempInt[i - 3])
                    .subtract(randomized)).remainder(newN);
            else tempInt[i - 1] = (cipherText[i].subtract(tempInt[i - 2]).subtract(tempInt[i - 3])
                        .subtract(tempInt[i - 4]).subtract(randomized)).remainder(newN);
        }
        System.out.println("unMixByte общий таймер: " + timer.stopTimeMillis());
//        for (BigInteger i : tempInt) {
//            System.out.print(i + ";");
//        }
        System.out.println();
        return bigIntegerArrayToByteArray.bigIntegerArrayToByteArray(tempInt, bitness);
    }

    public int SetPackaging(int messageLength) {
        int y = messageLength / Parameters.PackagingFactor - messageLength / Parameters.PackagingFactor % 8;
        System.out.println("y: " + y);
        if (y < 4) return 1;
        if (y > 248) return 248;
        return y;
    }

    public int SetUnPackaging(BigInteger cipherTextElement) {
        int y = (cipherTextElement.bitLength() + (8 - (cipherTextElement.bitLength() % 8)))/8;
        if (y < 4) return 1;
        if (y > 248) return 248;
        return y;
    }
}
