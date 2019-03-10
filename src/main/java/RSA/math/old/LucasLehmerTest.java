package RSA.math.old;

import RSA.math.IsNumberPrime;

import java.math.BigInteger;

public class LucasLehmerTest implements IsNumberPrime {
    /**
     * Тест простоты Люка — Лемера для чисел Мерсенна
     *
     * @param primeNumber Прстое нечетное число
     * @return true если число простое, false если составное
     */
    @Override
    public boolean isNumberPrime(BigInteger primeNumber, long marsenneDegree) {
        BigInteger s = new BigInteger("4");
        long k = 1;
        BigInteger two = new BigInteger("2");
        if (!(primeNumber.mod(two).equals(BigInteger.ZERO))) {
            while (k != marsenneDegree - 1) {
                s = ((s.multiply(s)).subtract(two)).mod(primeNumber);
                k++;
            }
        }
        return s.intValue() == 0;
    }

    /**
     * Тест простоты Люка — Лемера для чисел Мерсенна
     *
     * @return Имя класса
     */
    @Override
    public String getNameAlgorithmToFind() {
        return getClass().getName();
    }
}
