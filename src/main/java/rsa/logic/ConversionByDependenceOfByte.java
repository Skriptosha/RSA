package rsa.logic;

import org.springframework.stereotype.Component;
import rsa.Timer;
import rsa.config.Parameters;

import java.math.BigInteger;
import java.util.Random;

@Component
public class ConversionByDependenceOfByte implements MixByte {

    /**
     * Замешивание массива byte. Производится по формуле - bn = (an + a(n-1) + ... + a(n-4) + randomized) % N,
     * где bn - элемент в новом массиве на месте элемента an старого массива.
     * Перед замешиванием происходит упаковка байт в BigInteger, фактор упаковки определяется методом SetPackaging
     *
     * @param message массив байт, для смешивания
     * @param N Часть открытого и закрытого ключа N
     * @return массив BigInteger
     */
    @Override
    public BigInteger[] mixByte(byte[] message, BigInteger N) {
        Random random = new Random();
        Timer timer = new Timer();
        ByteArrayToBigIntegerArray byteArrayToBigIntegerArray = new ByteArrayToBigIntegerArray();
        BigInteger randomized;
        int capacity = SetPackaging(message.length);
        BigInteger newN = N.setBit(capacity*8);
        randomized = new BigInteger(String.valueOf((message[random.nextInt(message.length - 1)]
                * message[random.nextInt(message.length - 1)]
                * message[random.nextInt(message.length - 1)])
                * (message[random.nextInt(message.length - 1)]
                * message[random.nextInt(message.length - 1)])));
        System.out.println("newN.bitLength(): " + newN.bitLength());
        BigInteger[] temp = byteArrayToBigIntegerArray.byteArrayToBigIntegerArray(message, capacity);
        BigInteger[] result = new BigInteger[temp.length + 1];
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

    /**
     * Размешивание массива BigInteger, обратен методу mixByte.
     * Производится по формуле - an = (bn - a(n-1) - ... - a(n-4) - randomized) % N,
     * где bn - элемент в новом массиве на месте элемента an старого массива.
     * После размешивания происходит разупаковка BigInteger в байт, фактор разупаковки определяется методом
     * SetUnPackaging
     *
     * @param cipherText массив BigInteger
     * @param N Часть открытого и закрытого ключа N
     * @return массив байт
     */
    @Override
    public byte[] unMixByte(BigInteger[] cipherText, BigInteger N) {

        BigInteger randomized;
        Timer timer = new Timer();
        int capacity = SetUnPackaging(cipherText[2]);
        BigInteger newN = N.clearBit(capacity*8);
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
        return bigIntegerArrayToByteArray.bigIntegerArrayToByteArray(tempInt, capacity);
    }

    /**
     * Устанавливает колличество байт, которое будет помощено в один BigInteger
     *
     * @param messageLength длина исходного сообщения
     * @return коэффициент упаковки
     */
    public int SetPackaging(int messageLength) {
        int y = messageLength / Parameters.PackagingFactor - messageLength / Parameters.PackagingFactor % 8;
        if (y < 4) return 1;
        if (y > 255) return 255;
        return y;
    }

    /**
     * Определяет колличество байт, которое было помощено в один BigInteger. Обратно методу BigInteger
     *
     * @param cipherTextElement элемент массива BigInteger[2... max - 1]
     * @return коэффициент разупаковки
     */
    public int SetUnPackaging(BigInteger cipherTextElement) {
        int y = (cipherTextElement.bitLength() + (8 - (cipherTextElement.bitLength() % 8)))/8;
        if (y < 4) return 1;
        if (y > 255) return 255;
        return y;
    }
}
