package rsa.encryption.realization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rsa.Timer;
import rsa.config.Parameters;
import rsa.logic.EncAndDecMultiThread;
import rsa.logic.GetUniqueFromArray;
import rsa.logic.MixByte;
import tools.GetConfig;

import java.math.BigInteger;
import java.util.ArrayList;

@Component
public class EncryptionRSA {

    @Autowired
    private ReadPublicKey readPublicKey;

    @Autowired
    private MixByte mixByte;

    @Autowired
    private EncAndDecMultiThread multiThread;
    /**
     * Преобразование (шифрование) с испольованием открытого ключа
     *
     * @param message массив байт, который необходимо преобразовать
     * @return преобразованный массив BigInteger
     */
    public BigInteger[] encryptionRSA(byte[] message) {
        Timer timer = new Timer();

        if (readPublicKey.readPublicKey()) {

            BigInteger keyE = new BigInteger(readPublicKey.getE(), Parameters.RadixForKeys);
            BigInteger keyN = new BigInteger(readPublicKey.getN(), Parameters.RadixForKeys);

            timer.start();

            BigInteger[] mixBigIntegers = mixByte.mixByte(message, keyN);

            ArrayList<BigInteger> uniqueList = GetUniqueFromArray.getUniqueFromArray(mixBigIntegers);

            int countOfThreads;
            if (Boolean.parseBoolean(GetConfig.getProperty("EncMultiThread"))) {
                countOfThreads = Runtime.getRuntime().availableProcessors() + Parameters.availableProcessorsFactor;
            }
            else
                countOfThreads = 1;

            multiThread.setEncAndDecMultiThread(uniqueList, keyE, keyN, countOfThreads);

            ArrayList<BigInteger> resultList = multiThread.run();

            System.out.println("encryptionRSA общий таймер: " + timer.stopTimeMillis());
            return GetUniqueFromArray.fillArrayFromUniqueBigInteger(mixBigIntegers
                    , resultList).toArray(new BigInteger[0]);
        } else {
            System.out.println("Не удалось прочитать ключи, необходима генерация новых");
        }
        return null;
    }
}
