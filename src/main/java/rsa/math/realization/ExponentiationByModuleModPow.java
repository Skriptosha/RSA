package rsa.math.realization;

import org.springframework.stereotype.Component;
import rsa.math.interfaces.ExponentiationByModule;

import java.math.BigInteger;

@Component
public class ExponentiationByModuleModPow implements ExponentiationByModule {

    /**
     * Возведение в степень по модулю используя BigInteger.modPow
     * @param basic Основание для возведения в степень
     * @param degree Степень
     * @param module Модуль
     * @return то что получилось, тип BigInteger
     */
    @Override
    public BigInteger getExponentiationByModule(BigInteger basic, BigInteger degree, BigInteger module) {
        BigInteger t, result;
        boolean minus = false;
        if (basic.max(BigInteger.ZERO).equals(BigInteger.ZERO)){
            t = basic.abs();
            minus = true;
        } else t = basic;
        result = t.modPow(degree, module);
        if (minus) result = result.negate();
        return result;
    }

    /**
     * Возведение в степень по модулю используя BigInteger.modPow и Китайскую теорему об остатках (можно использовать
     * зная разложение модуля на простые множители. (актуально для закрытого ключа).
     *
     * @param basic Основание для возведения в степень
     * @param degree Степень
     * @param moduleP Простой множитель P модуля
     * @param moduleQ Простой множитель Q модуля
     * @param module Модуль
     * @return то что получилось, тип BigInteger
     */
    @Override
    public BigInteger getExponentiationByModule(BigInteger basic, BigInteger degree, BigInteger moduleP
            , BigInteger moduleQ, BigInteger module) {
        BigInteger t, result, r1, r2, M1, M2, Mx1, Mx2;
        boolean minus = false;
        if (basic.max(BigInteger.ZERO).equals(BigInteger.ZERO)){
            t = basic.abs();
            minus = true;
        } else t = basic;
        r1 = (t.mod(moduleP)).modPow((degree.mod(moduleP.subtract(BigInteger.ONE))), moduleP);
        r2 = (t.mod(moduleQ)).modPow((degree.mod(moduleQ.subtract(BigInteger.ONE))), moduleQ);
        M1 = module.divide(moduleP);
        M2 = module.divide(moduleQ);
        Mx1 = M1.modInverse(moduleP);
        Mx2 = M2.modInverse(moduleQ);
        result = ((r1.multiply(M1).multiply(Mx1)).add(r2.multiply(M2).multiply(Mx2))).mod(module);
        if (minus) result = result.negate();
        return result;
    }
}
