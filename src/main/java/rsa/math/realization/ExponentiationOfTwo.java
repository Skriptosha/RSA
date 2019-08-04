package rsa.math.realization;

import java.math.BigInteger;

public class ExponentiationOfTwo {

    /**
     * Возведение 2 в степень degree
     */
    public BigInteger exponentiationOfTwo(int degree) {
        BigInteger basic = new BigInteger("2");
        return basic.shiftLeft(degree-1);
    }
}
