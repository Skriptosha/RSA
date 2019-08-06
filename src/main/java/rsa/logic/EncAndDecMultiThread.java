package rsa.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rsa.config.ConfigSpring;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class EncAndDecMultiThread {
    private BigInteger keyD;
    private BigInteger keyP;
    private BigInteger keyQ;
    private BigInteger keyE;
    private BigInteger keyN;
    private int countOfThreads;
    private ArrayList<BigInteger> list;

    @Autowired
    private EncAndDecRun encAndDecRun;

    @Autowired
    private ConfigSpring configSpring;

    /**
     * Конструктор для работы с закрытым ключом
     *
     * @param list Коллекция BigInteger для проведения преобразования
     * @param keyD Часть закрытого ключа D
     * @param keyP Множитель Р ключа N
     * @param keyQ Множитель Q ключа N
     * @param countOfThreads Колличество потоков для проведения преобразования
     */
    public void setEncAndDecMultiThread(ArrayList<BigInteger> list, BigInteger keyD, BigInteger keyP, BigInteger keyQ,
                                int countOfThreads) {
        this.keyD = keyD;
        this.keyP = keyP;
        this.keyQ = keyQ;
        this.list = list;
        this.countOfThreads = countOfThreads;
    }

    /**
     * Конструктор для работы с открытым ключом
     *
     * @param list Коллекция BigInteger для проведения преобразования
     * @param keyE Часть открытого ключа E
     * @param keyN Часть открытого ключа N
     * @param countOfThreads Колличество потоков для проведения преобразования
     */
    public void setEncAndDecMultiThread(ArrayList<BigInteger> list, BigInteger keyE, BigInteger keyN,
                                        int countOfThreads) {
        this.keyE = keyE;
        this.keyN = keyN;
        this.list = list;
        this.countOfThreads = countOfThreads;
    }

    /**
     * После создания обьекта (в конструкторе присходит инициализация всех переменных) метод для преобразования.
     * Если параметр countOfThreads = 1, создания отдельных потоков не происходит
     *
     * @return Преобразованный массив BigInteger
     */
    public ArrayList<BigInteger> run(){

        HashMap<Integer, BigInteger[]> map = subListForThreads(list, countOfThreads);
        EncAndDecRun[] encAndDecRuns = new EncAndDecRun[map.size()];
        System.out.println(encAndDecRuns.length);
        ArrayList<BigInteger> resultList;
        if (map.size() > 1) {
            Thread[] threads = new Thread[map.size()];
            int j = 0;
            for (HashMap.Entry<Integer, BigInteger[]> i : map.entrySet()) {
                if (keyD != null) {
                    encAndDecRuns[j] = (EncAndDecRun) configSpring.getBean(encAndDecRun.getClass().getSimpleName());
                    encAndDecRuns[j].setEncAndDecRun(i.getValue(), keyD, keyP, keyQ);
                }
                else {
                    encAndDecRuns[j] = (EncAndDecRun) configSpring.getBean(encAndDecRun.getClass().getSimpleName());
                    encAndDecRuns[j].setEncAndDecRun(i.getValue(), keyE, keyN);
                }
                threads[j] = new Thread(encAndDecRuns[j]);
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
            resultList = new ArrayList<>(list.size());
            for (EncAndDecRun c : encAndDecRuns) {
                resultList.addAll(c.getResultList());
            }
        } else {
            if (keyD != null)
                encAndDecRuns[0].setEncAndDecRun(map.get(0), keyD, keyP, keyQ);
            else
                encAndDecRuns[0].setEncAndDecRun(map.get(0), keyE, keyN);
            encAndDecRuns[0].run();
            resultList = encAndDecRuns[0].getResultList();
        }
        return resultList;
    }

    /**
     * Разбиение начальной коллекции BigInteger на суб массивы, для потоков
     *
     * @param uniqueList Начальная коллекция BigInteger
     * @param countOfThreads Колличество потоков для проведения преобразования
     * @return ХэшМап с суб массивами
     */
    public static HashMap<Integer, BigInteger[]> subListForThreads(ArrayList<BigInteger> uniqueList
            , int countOfThreads) {
        HashMap<Integer, BigInteger[]> map;
        if (countOfThreads > 1) {
            int subListSize = uniqueList.size() / countOfThreads;
            int remainder = uniqueList.size() % countOfThreads;
            int a = 0, j = 0;
            map = new HashMap<>(countOfThreads);
            for (int i = 0; i < countOfThreads; i++) {
                if (remainder > 0) {
                    a = 1;
                    remainder--;
                }
                map.put(i, (uniqueList.subList(j, subListSize + j + a)).toArray(new BigInteger[0]));
                j = j + subListSize + a;
                a = 0;
            }
        } else {
            map = new HashMap<>(1);
            map.put(0, uniqueList.toArray(new BigInteger[0]));
        }
        return map;
    }

}
