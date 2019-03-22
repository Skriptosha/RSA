package RSA.encryption;

import RSA.Parameters;
import RSA.Timer;
import RSA.logic.ConversionByDependenceOfByte;
import RSA.logic.GetUniqueFromArray;
import RSA.logic.Parallelization;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class DecryptionRSA {
    private BigInteger[] dec;
    private BigInteger keyD;
    private BigInteger keyP;
    private BigInteger keyQ;
    private Timer timer = new Timer();

    public byte[] decryptionRSA(BigInteger[] dec) {
        ReadPrivateKey readPrivateKey = new ReadPrivateKey();
        this.dec = dec;
        timer.start();
        if (readPrivateKey.readPrivateKey()) {
            keyD = new BigInteger(readPrivateKey.getD(), Parameters.RadixForKeys);
            keyP = new BigInteger(readPrivateKey.getP(), Parameters.RadixForKeys);
            keyQ = new BigInteger(readPrivateKey.getQ(), Parameters.RadixForKeys);
            return run();
        } else return null;
    }

    private byte[] run() {
        int countOfThreads = Runtime.getRuntime().availableProcessors() + Parameters.availableProcessorsactor;
        ConversionByDependenceOfByte byDependenceOfByte = new ConversionByDependenceOfByte();
        GetUniqueFromArray getUniqueFromArray = new GetUniqueFromArray();
        ArrayList<BigInteger> uniqueList = getUniqueFromArray.getUniqueFromArray(dec);
        HashMap<Integer, BigInteger[]> map = subListForThreads(uniqueList, countOfThreads);
        Thread[] threads = new Thread[map.size()];
        Parallelization[] parallelizations = new Parallelization[map.size()];
        int j = 0;
        for (HashMap.Entry<Integer, BigInteger[]> i : map.entrySet()) {
            parallelizations[j] = new Parallelization(i.getValue(), keyD, keyP, keyQ);
            threads[j] = new Thread(parallelizations[j]);
            threads[j].start();
            j++;
        }
        for (Thread t : threads) {
            if (t.isAlive()) {
                synchronized (t) {
                    try {
                        t.wait();
                    } catch (InterruptedException e) {
                        // Продолжаем ожидать потоки
                    }
                }
            }
        }
        ArrayList<BigInteger> resultList = new ArrayList<>(dec.length);
        for (Parallelization c : parallelizations) {
            resultList.addAll(c.getResultList());
        }
        System.out.println("decryptionRSA общий таймер: " + timer.stopTimeMillis());
        return byDependenceOfByte.unMixByte(getUniqueFromArray.fillArrayFromUniqueBigInteger(dec, resultList)
                .toArray(new BigInteger[0]), keyP.multiply(keyQ));
//        return byDependenceOfByte.unMixByte(resultList.toArray(new BigInteger[0]), keyP.multiply(keyQ));
    }

    private HashMap<Integer, BigInteger[]> subListForThreads(ArrayList<BigInteger> uniqueList, int countOfThreads) {
        int subListSize = uniqueList.size() / countOfThreads;
        int remainder = uniqueList.size() % countOfThreads;
        int a = 0, j = 0;
        HashMap<Integer, BigInteger[]> map = new HashMap<>(countOfThreads);
        for (int i = 0; i < countOfThreads; i++) {
            if (remainder > 0) {
                a = 1;
                remainder--;
            }
            map.put(i, (uniqueList.subList(j, subListSize + j + a)).toArray(new BigInteger[0]));
            j = j + subListSize + a;
            a = 0;
        }
        return map;
    }
}
