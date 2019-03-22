package RSA.generateKeys;

import RSA.Timer;
import RSA.math.*;

import java.math.BigInteger;

public class GetPrimeNumberAlg1 implements GetPrimeNumber, Runnable {
    private int count = 0;
    private BigInteger temp;
    private int bits;

    /**
     * Алгоритм получения вероятно простого числа необходимой битности (bits). Генерация кандидата происходит
     * за счет алгоритма RandomBitsGeneration, следующий кандидат получается путем прибавления к текущему 2
     */

    @Override
    public BigInteger getPrimeNumber() {
        if (temp != null)
            return temp;
        else throw new NullPointerException("Значение PrimeNumber null! Необходимо предварительно вызвать" +
                " setBits(int bits) и далее метод run()");
    }

    @Override
    public void setBits(int bits) {
        this.bits = bits;
        count++;
    }

    @Override
    public void run() {
        if (count == 0) throw new NullPointerException("Необходимо предварительно вызвать " +
                "setBits(int bits)");
        Timer timer = new Timer(), timer2 = new Timer();
        RandomGeneration rand = new RandomBitsGeneration();
        IsNumberPrime algorithm2 = new MillerRabinTest();
        TrialDivision trialDivision = new TrialDivision();
        boolean isPrime = false;
        boolean isTrial;
        int count = 0;
        int count2 = 0;
        timer2.start();
        timer.start();
        temp = rand.getRandomNumber(bits);
//        System.out.println("getRandomNumber timer: " + timer.stopTimeMillis());
        do {
            timer.start();
            isTrial = trialDivision.trialDivision(temp);
//            System.out.println("trialDivision timer: " + timer.stopTimeMillis());
            if (isTrial) {
                timer.start();
                isPrime = algorithm2.isNumberPrime(temp, 0);
//                System.out.println("MillerRabinTest timer: " + timer.stopTimeMillis());
                count2++;
            }
            count++;
            if (!isPrime) temp = temp.add(new BigInteger("2"));
        } while (!isPrime);
        System.out.println("general timer: " + timer2.stopTimeSeconds() + " поток: " + Thread.currentThread().getName());
//        System.out.println(temp);
        synchronized (this) {
            notifyAll();
        }
//        System.out.println("Длина: " + temp.bitLength());
//        System.out.println("Колличество итераций цикла trialDivision: " + count);
//        System.out.println("Колличество итераций цикла MillerRabinTest: " + count2);
    }
}
