package RSA.math;

import java.math.BigInteger;

public class ExponentiationLeftToRight extends ExponentiationOfTwo implements Exponentiation {

    /**
     * Cхема возведения в степень «слева направо»
     *
     * @param basic Основание степени, тип BigInteger
     * @param degree Показатель степени,тип BigInteger
     * @return То что получилось, тип BigInteger
     */
    @Override
    public BigInteger exponentiationNumber(BigInteger basic, BigInteger degree) {
        BigInteger x = new BigInteger("1");
        char[] chars = degree.toString(2).toCharArray();
        for (char aChar : chars) {
            if (aChar == 48) x = x.multiply(x);
            else x = x.multiply(x).multiply(basic);
        }
//        System.out.println("exponentiationNumber: " + x);
        return x;
    }
}
