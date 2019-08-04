package rsa.math.old;

import rsa.math.interfaces.ExponentiationByModule;

import java.math.BigInteger;

public class ExponentiationByModuleFast implements ExponentiationByModule {


    @Override
    public BigInteger getExponentiationByModule(BigInteger basic, BigInteger degree, BigInteger module) {
        BigInteger t, result;
        boolean minus = false;
        result = new BigInteger("1");
        if (basic.max(BigInteger.ZERO).equals(BigInteger.ZERO)) {
            t = basic.abs();
            minus = true;
        } else t = basic;
        char[] chars = degree.toString(2).toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == 48) result = result.multiply(result).mod(module);
            else
                result = t.multiply((result.multiply(result))).mod(module);
        }
        if (minus) result = result.negate();
        return result;
    }

    @Override
    public BigInteger getExponentiationByModule(BigInteger basic, BigInteger degree, BigInteger moduleP
            , BigInteger moduleQ, BigInteger module) {
        return null;
    }

    public BigInteger multiply(BigInteger one, BigInteger two) {
        BigInteger result = BigInteger.ZERO;
        char[] chars = two.toString(2).toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] != 48) {
                result = one.shiftLeft(chars.length - i - 1).add(result);
            }
        }
        return result;
    }
}
