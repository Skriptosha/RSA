package RSA.generateKeys;

import RSA.Parameters;
import RSA.math.EulerFunction;
import RSA.math.GetSecretKeyJavamodInverse;

import java.math.BigInteger;
import java.util.Random;

public class GenerateKeysRSA {
    private BigInteger p;
    private BigInteger q;
    private String[] publicKey;
    private String[] privateKey;
    private GetPrimeNumber getPrimeNumberP, getPrimeNumberQ;

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
        d = new GetSecretKeyJavamodInverse().getSecretKey(e, euler);
        n = q.multiply(p);
        publicKey = new String[]{e.toString(Parameters.RadixForKeys), n.toString(Parameters.RadixForKeys)};
        privateKey = new String[]{d.toString(Parameters.RadixForKeys), p.toString(Parameters.RadixForKeys)
                , q.toString(Parameters.RadixForKeys)};
        new SavePrivateAndPublicKey().savePrivateKey(this);
        new SavePrivateAndPublicKey().savePublicKey(this);
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
