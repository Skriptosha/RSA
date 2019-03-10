package RSA.math;

import java.math.BigInteger;

public class ExponentiationByModuleModPow implements  ExponentiationByModule{
    private int count = 0;
    private BigInteger basic;
    private BigInteger degree;
    private BigInteger module;
    private BigInteger result;

    @Override
    public BigInteger getExponentiationByModule() {
        if (result != null) return result;
        else throw new NullPointerException("Значение ExponentiationByModule null! Необходимо предварительно вызвать" +
                " setParameters(BigInteger basic, BigInteger degree, BigInteger module) и далее метод run()");
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
        if (basic.max(BigInteger.ZERO).equals(BigInteger.ZERO)){
            t = basic.abs();
            minus = true;
        } else t = basic;
        result = t.modPow(degree, module);
        if (minus) result = result.negate();
    }
}
