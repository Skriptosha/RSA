package rsa.math.old;

import rsa.math.interfaces.Exponentiation;
import rsa.math.realization.ExponentiationLeftToRight;

import java.math.BigInteger;

public class GetMarsenneNumber implements GetNumberOfSpecialKind {

    /**
     * Числа Мерсенна — числа вида M_{n}=2^{n}-1 , где n — натуральное число.
     * При этом число Мерсенна может быть простым, только если n — простое число
     * @return Число Мерсенна (long)
     */
    @Override
    public BigInteger getNumber(BigInteger degree) {
        Exponentiation exp = new ExponentiationLeftToRight();
        return exp.exponentiationNumber(new BigInteger("2"), degree)
                .subtract(BigInteger.ONE);
    }

    /**
     * Имя алгоритма ряда чисел специального вида
     *
     * @return Имя класса
     */
    @Override
    public String getNameOfSpecialKind() {
        return getClass().getName();
    }
}

