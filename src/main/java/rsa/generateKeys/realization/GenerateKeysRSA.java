package rsa.generateKeys.realization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rsa.config.ConfigSpring;
import rsa.config.Parameters;
import rsa.generateKeys.interfaces.GetPrimeNumber;
import rsa.math.realization.EulerFunction;
import rsa.math.realization.GetSecretKeyJavaModInverse;

import java.math.BigInteger;
import java.util.Random;

@Component
public class GenerateKeysRSA {
    private BigInteger p;
    private BigInteger q;
    private String[] publicKey;
    private String[] privateKey;
    private GetPrimeNumber getPrimeNumberP, getPrimeNumberQ;

    @Autowired
    private SavePrivateAndPublicKey savePrivateAndPublicKey;

    @Autowired
    private ConfigSpring configSpring;

    public String[] getPublicKey() {
        return publicKey;
    }

    public String[] getPrivateKey() {
        return privateKey;
    }

    public boolean generateKeysRSA(int bits) {
        getPrimeNumberP = new GetPrimeNumberAlg1();
        getPrimeNumberQ = new GetPrimeNumberAlg1();
        BigInteger euler;
        BigInteger e;
        BigInteger d;
        BigInteger n;
        getPrimeNumberP.setBits(bits);
        getPrimeNumberQ.setBits(bits);

        e = new BigInteger(Parameters.Fermat[new Random().nextInt(Parameters.Fermat.length - 1)]);

        findNumber();
        euler = EulerFunction.eulerFunction(p, q);
        while(!euler.gcd(e).equals(BigInteger.ONE)) {
            findNumber();
            euler = EulerFunction.eulerFunction(p, q);
        }
        d = new GetSecretKeyJavaModInverse().getSecretKey(e, euler);
        n = q.multiply(p);
        publicKey = new String[]{e.toString(Parameters.RadixForKeys), n.toString(Parameters.RadixForKeys)};
        privateKey = new String[]{d.toString(Parameters.RadixForKeys), p.toString(Parameters.RadixForKeys)
                , q.toString(Parameters.RadixForKeys)};

        savePrivateAndPublicKey.savePrivateKey(this);
        savePrivateAndPublicKey.savePublicKey(this);
        return true;
    }

    private void findNumber() {
        Thread[] threads = new Thread[]{new Thread((Runnable) getPrimeNumberP), new Thread((Runnable) getPrimeNumberQ)};
        for (Thread t : threads) {
            t.start();
        }
        do {
            for (Thread t : threads) {
                if (t.isAlive()) {
                    try {
                        synchronized (t) {
                            t.wait();
                        }
                    } catch (InterruptedException e1) {
                        //Ожидаем остальные потоки на завершение
                    }
                }
            }
            p = getPrimeNumberP.getPrimeNumber();
            q = getPrimeNumberQ.getPrimeNumber();
        } while (p.equals(q) && q.multiply(p).bitLength() < q.bitLength()*2);
    }
}
