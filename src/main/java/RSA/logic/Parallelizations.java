package RSA.logic;

import RSA.math.ExponentiationByModule;
import RSA.math.ExponentiationByModuleModPow;

import java.math.BigInteger;
import java.util.ArrayList;

public class Parallelizations implements Runnable {
    private BigInteger[] decList;
    private ArrayList<Integer> resultList = new ArrayList<>();
    private BigInteger keyD;
    private BigInteger keyN;

    public Parallelizations(BigInteger[] decList, BigInteger keyD, BigInteger keyN) {
        this.decList = decList;
        this.keyD = keyD;
        this.keyN = keyN;
    }

    @Override
    public void run() {
        for (BigInteger a : decList) {
            ExponentiationByModule exp = new ExponentiationByModuleModPow();
            exp.setParameters(a, keyD, keyN);
            exp.exponentiationByModule();
            resultList.add(exp.getExponentiationByModule().intValue());
        }
        synchronized (this) {
            notifyAll();
        }
    }

    public ArrayList<Integer> getResultList() {
        return resultList;
    }
}
