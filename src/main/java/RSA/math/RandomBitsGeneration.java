package RSA.math;

import java.math.BigInteger;
import java.util.Random;

public class RandomBitsGeneration implements RandomGeneration {

    /**
     * Генерация числа при помощи Random.nextBytes()
     *
     * @param bits какой битности должно быть число
     * @return сгенерированное число типа BigInteger
     */
    @Override
    public BigInteger getRandomNumber(int bits) {
        while (true) {
            BigInteger temp;
            BigInteger[] mass = new BigInteger[]{
                new BigInteger(bits / 3, new Random()),
                new BigInteger(bits / 3, new Random()),
                new BigInteger(bits / 3, new Random()),
            };
            temp = (mass[0].shiftLeft(mass[1].bitLength() + mass[2].bitLength()))
                    .or(mass[1].shiftLeft(mass[2].bitLength())).or(mass[2].shiftLeft(0));
//            System.out.println("temp: " + temp);
//            System.out.println("temp.bitLength(): " + temp.bitLength());
            int numBits = bits - temp.bitLength();
            temp = temp.shiftLeft(numBits).or(mass[new Random().nextInt(mass.length - 1)]);
//            System.out.println("temp after shift: " + temp);
            if (!temp.and(BigInteger.ONE).equals(BigInteger.ZERO))
                return temp;
        }
    }
}
