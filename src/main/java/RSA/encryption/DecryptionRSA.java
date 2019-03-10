package RSA.encryption;

import RSA.Parameters;
import RSA.Timer;
import RSA.logic.ConversionByDependenceOfByte;
import RSA.logic.GetUniqueFromArray;
import RSA.logic.Parallelizations;

import java.math.BigInteger;
import java.util.*;

public class DecryptionRSA {
    private HashMap<Integer, BigInteger[]> map = new HashMap<>();
    private BigInteger[] dec;
    private BigInteger keyD;
    private BigInteger keyN;

    String decryptionRSA(String[] privateKey, BigInteger[] dec) {
//        System.out.println("dec: " + dec.length);
        this.dec = dec;
        if (privateKey != null) {
            keyD = new BigInteger(privateKey[0], Parameters.RadixForKeys);
            keyN = new BigInteger(privateKey[1], Parameters.RadixForKeys);
            return run();
        } else return "Не удалось прочитать ключи";
    }

    public String decryptionRSA(BigInteger[] dec) {
        ReadPrivateKey readPrivateKey = new ReadPrivateKey();
        this.dec = dec;
//        System.out.println("dec: " + dec.length);
        if (readPrivateKey.readPrivateKey()) {
            keyD = new BigInteger(readPrivateKey.getD(), Parameters.RadixForKeys);
            keyN = new BigInteger(readPrivateKey.getN(), Parameters.RadixForKeys);
            return run();
        } else return "Не удалось прочитать ключи";
    }

    private String run() {
        int countOfThreads = Runtime.getRuntime().availableProcessors() + 2;
        ConversionByDependenceOfByte byDependenceOfByte = new ConversionByDependenceOfByte();
        GetUniqueFromArray getUniqueFromArray = new GetUniqueFromArray();
        RSA.Timer timer = new Timer();
        timer.start();
        ArrayList<BigInteger> uniqueList = getUniqueFromArray.getUniqueFromArray(dec);
//        System.out.println("uniqueList.size(): " + uniqueList.size());
        int subListSize = uniqueList.size() / countOfThreads;
        int remainder = uniqueList.size() % countOfThreads;
//        System.out.println("remainder: " + remainder);
        int a = 0;
        int j = 0;
        for (int i = 0; i < countOfThreads; i++) {
            if (remainder > 0) {
                a = 1;
                remainder--;
            }
            map.put(i, (uniqueList.subList(j, subListSize + j + a)).toArray(new BigInteger[0]));
            j = j + subListSize + a;
            a = 0;
        }

        Thread[] threads = new Thread[map.size()];
        Parallelizations[] parallelizations = new Parallelizations[map.size()];
        j = 0;
        for (HashMap.Entry<Integer, BigInteger[]> i : map.entrySet()) {
            parallelizations[j] = new Parallelizations(i.getValue(), keyD, keyN);
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
        System.out.println("decryptionRSA общий таймер: " + timer.stopTimeMillis());
        ArrayList<Integer> resultList = new ArrayList<>();
        for (Parallelizations c : parallelizations) {
            resultList.addAll(c.getResultList());
        }
//        System.out.println("resultList.size(): " + resultList.size());
        return byDependenceOfByte.translatesBytesIntoString(getUniqueFromArray
                .fillArrayFromUniqueInteger(dec, resultList).toArray(new Integer[0]), keyN);
    }
}
