package rsa.encryption.realization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rsa.Timer;
import rsa.config.Parameters;
import rsa.logic.EncAndDecMultiThread;
import rsa.logic.GetUniqueFromArray;
import rsa.logic.MixByte;

import java.math.BigInteger;
import java.util.ArrayList;

@Component
public class DecryptionRSA {

    private Timer timer = new Timer();

    @Autowired
    private MixByte mixByte;

    @Autowired
    private ReadPrivateKey readPrivateKey;

    @Autowired
    private EncAndDecMultiThread multiThread;

    /**
     * Преобразование (расшифрование) с испольованием закрытого ключа
     *
     * @param dec Массив BigInteger
     * @return преобразованный массив байт
     */
    public byte[] decryptionRSA(BigInteger[] dec) {

        timer.start();
        if (readPrivateKey.readPrivateKey()) {
            BigInteger keyD = new BigInteger(readPrivateKey.getD(), Parameters.RadixForKeys);
            BigInteger keyP = new BigInteger(readPrivateKey.getP(), Parameters.RadixForKeys);
            BigInteger keyQ = new BigInteger(readPrivateKey.getQ(), Parameters.RadixForKeys);

            ArrayList<BigInteger> uniqueList = GetUniqueFromArray.getUniqueFromArray(dec);

            int countOfThreads = Runtime.getRuntime().availableProcessors() + Parameters.availableProcessorsFactor;

            multiThread.setEncAndDecMultiThread(uniqueList, keyD, keyP, keyQ, countOfThreads);

            ArrayList<BigInteger> resultList = multiThread.run();

            System.out.println("decryptionRSA общий таймер: " + timer.stopTimeMillis());

            assert mixByte != null;

            return mixByte.unMixByte(GetUniqueFromArray.fillArrayFromUniqueBigInteger(dec, resultList)
                    .toArray(new BigInteger[0]), keyP.multiply(keyQ));
//        return byDependenceOfByte.unMixByte(resultList.toArray(new BigInteger[0]), keyP.multiply(keyQ));
        } else return null;
    }
}
