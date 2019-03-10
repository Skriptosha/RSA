package RSA.math;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabinTest implements IsNumberPrime {
    /**
     * Тест Миллера — Рабина
     *
     * @param primeNumber нечетное число, которое нужно проверить на простоту
     * @return true если число вероятно простое, false если составное
     */
    @Override
    public boolean isNumberPrime(BigInteger primeNumber, long marsenneDegree) {
        long count;
        Random random = new Random();
        BigInteger two = new BigInteger("2");
        BigInteger numberTemp = primeNumber.subtract(BigInteger.ONE);
        long degree = 0;
        if (!(primeNumber.mod(two).equals(BigInteger.ZERO))) {
            while (true) {
                if (numberTemp.and(BigInteger.ONE).equals(BigInteger.ZERO)) {
                    numberTemp = numberTemp.shiftRight(1);
                    degree++;
                } else {
                    break;
                }
            }
            count = primeNumber.bitLength() - 2;
//            System.out.println("degree: " + degree + " numberTemp: " + numberTemp);
            // цикл А
            for (int i = 0; i < count; i++) {
                BigInteger a = new BigInteger(primeNumber.bitLength()
                        - (random.nextInt(primeNumber.bitLength()) - 4) - 2, random);
                if (a.max(two).equals(two)) a = a.add(two);
                BigInteger x = a.modPow(new BigInteger(String.valueOf(numberTemp)), primeNumber);
                if (x.equals(BigInteger.ONE) || x.equals(primeNumber.subtract(BigInteger.ONE))) {
                    continue;
                }
                //Цикл Б
                for (int j = 0; j < degree - 1; j++) {
                    x = x.modPow(two, primeNumber);
                    if (x.equals(BigInteger.ONE)) {
                        return false;
                    }
                    if (x.equals(primeNumber.subtract(BigInteger.ONE))) {
                        break;
                    }
                }
                if (!x.equals(primeNumber.subtract(BigInteger.ONE))) return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String getNameAlgorithmToFind() {
        return getClass().getName();
    }
}
