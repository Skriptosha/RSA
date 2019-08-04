package rsa.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import rsa.math.interfaces.ExponentiationByModule;
import tools.GetConfig;

import java.math.BigInteger;
import java.util.ArrayList;

@Component("EncAndDecRun")
@Scope("prototype")
public class EncAndDecRun implements Runnable {
    private BigInteger[] decList;
    private ArrayList<BigInteger> resultList = new ArrayList<>();
    private BigInteger key;
    private BigInteger moduleP;
    private BigInteger moduleQ;
    private BigInteger module;

    @Autowired
    private ExponentiationByModule exponentiationByModule;

    /**
     * Конструктор для работы с закрытым ключом
     *
     * @param decList Массив BigInteger для проведения преобразования
     * @param keyD Часть закрытого ключа D
     * @param moduleP Множитель Р ключа N
     * @param moduleQ Множитель Q ключа N
     */
    public void setEncAndDecRun(BigInteger[] decList, BigInteger keyD, BigInteger moduleP, BigInteger moduleQ) {
        this.decList = decList;
        this.key = keyD;
        this.moduleP = moduleP;
        this.moduleQ = moduleQ;
        module = moduleP.multiply(moduleQ);
    }

    /**
     * Конструктор для работы с открытым ключом
     *
     * @param decList Массив BigInteger для проведения преобразования
     * @param keyE Часть открытого ключа E
     * @param module Часть открытого ключа N
     */
    public void setEncAndDecRun(BigInteger[] decList, BigInteger keyE, BigInteger module) {
        this.decList = decList;
        this.key = keyE;
        this.module = module;
    }

    /**
     * Возведение в степень по модулю в режиме многопоточности
     */
    @Override
    public void run() {
        GetConfig.setNameProperties("rsa");

        if (moduleP != null && moduleQ != null) {
            for (BigInteger a : decList) {
                resultList.add(exponentiationByModule.getExponentiationByModule(a, key, moduleP, moduleQ, module));
            }
        } else {
            for (BigInteger a : decList) {
                resultList.add(exponentiationByModule.getExponentiationByModule(a, key, module));
            }
        }
        synchronized (this) {
            notifyAll();
        }
    }

    /**
     * Получение результата от работы метода run
     *
     * @return Коллекция преобразованного массива decList
     */
    public ArrayList<BigInteger> getResultList() {
        return resultList;
    }
}
