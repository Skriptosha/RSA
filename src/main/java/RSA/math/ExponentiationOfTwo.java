package RSA.math;

import java.math.BigInteger;

public class ExponentiationOfTwo {

    public BigInteger exponentiationOfTwo(int degree) {
        BigInteger basic = new BigInteger("2");
        return basic.shiftLeft(degree-1);
    }
}
