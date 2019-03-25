package RSA.logic;

import RSA.math.ExponentiationByModule;
import tools.GetConfig;

import java.math.BigInteger;
import java.util.ArrayList;

public class Parallelization implements Runnable {
    private BigInteger[] decList;
    private ArrayList<BigInteger> resultList = new ArrayList<>();
    private BigInteger keyD;
    private BigInteger moduleP;
    private BigInteger moduleQ;
    private BigInteger module;

    public Parallelization(BigInteger[] decList, BigInteger keyD, BigInteger moduleP, BigInteger moduleQ) {
        this.decList = decList;
        this.keyD = keyD;
        this.moduleP = moduleP;
        this.moduleQ = moduleQ;
        module = moduleP.multiply(moduleQ);
    }

    @Override
    public void run() {
        ExponentiationByModule exp = null;
        GetConfig.setNameProperties("TestProp");
        try {
            exp = (ExponentiationByModule) Class.forName(GetConfig.getProperty("ExponentiationByModuleClass"))
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert exp != null;
        for (BigInteger a : decList) {
            resultList.add(exp.getExponentiationByModule(a, keyD, moduleP, moduleQ, module));
        }
        synchronized (this) {
            notifyAll();
        }
    }

    public ArrayList<BigInteger> getResultList() {
        return resultList;
    }
}
