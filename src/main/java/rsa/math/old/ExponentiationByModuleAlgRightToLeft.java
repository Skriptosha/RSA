package rsa.math.old;

import rsa.Timer;
import rsa.math.interfaces.ExponentiationByModule;

import java.math.BigInteger;

public class ExponentiationByModuleAlgRightToLeft implements ExponentiationByModule {


    @Override
    public BigInteger getExponentiationByModule(BigInteger basic, BigInteger degree, BigInteger module) {
        BigInteger t, result;
        boolean minus = false;
        Timer timer2 = new Timer();
        timer2.start();
        result = new BigInteger("1");
        if (basic.max(BigInteger.ZERO).equals(BigInteger.ZERO)) {
            t = basic.abs();
            minus = true;
        } else t = basic;

        char[] chars = degree.toString(2).toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == 48) t = t.multiply(t).mod(module);
            else {
                result = result.multiply(t).mod(module);
                t = t.multiply(t).mod(module);
            }
        }
        if (minus) result = result.negate();
        return  result;
    }

    @Override
    public BigInteger getExponentiationByModule(BigInteger basic, BigInteger degree, BigInteger moduleP
            , BigInteger moduleQ, BigInteger module) {
        return null;
    }
}
