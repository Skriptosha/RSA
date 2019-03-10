package RSA.math;

import RSA.Timer;

import java.math.BigInteger;

public class ExponentiationByModuleAlgRightToLeft implements ExponentiationByModule {
    private int count = 0;
    private BigInteger basic;
    private BigInteger degree;
    private BigInteger module;
    private BigInteger d;

    @Override
    public BigInteger getExponentiationByModule() {
        if (d != null) return d;
        else throw new NullPointerException("Значение ExponentiationByModule null! Необходимо предварительно вызвать" +
                " setParameters(BigInteger basic, BigInteger degree, BigInteger module) " +
                "и далее метод exponentiationByModule()");
    }

    @Override
    public void setParameters(BigInteger basic, BigInteger degree, BigInteger module) {
        this.basic = basic;
        this.degree = degree;
        this.module = module;
        count++;
    }

    @Override
    public void exponentiationByModule(){
        BigInteger t;
        boolean minus = false;
        if (count == 0) throw new NullPointerException("Необходимо предварительно вызвать " +
                "setParameters(BigInteger basic, BigInteger degree, BigInteger module)");
        Timer timer2 = new Timer();
        timer2.start();
        d = new BigInteger("1");
        if (basic.max(BigInteger.ZERO).equals(BigInteger.ZERO)){
            t = basic.abs();
            minus = true;
        } else t = basic;

        char[] chars = degree.toString(2).toCharArray();
        for (int i = chars.length - 1; i >= 0; i--){
            if (chars[i] == 48) t = t.multiply(t).mod(module);
            else {
                d = d.multiply(t).mod(module);
                t = t.multiply(t).mod(module);
            }
        }
        if (minus) d = d.negate();
//        System.out.println("ExponentiationByModuleAlgRightToLeft таймер: " + timer2.stopTimeMillis());
    }
}
