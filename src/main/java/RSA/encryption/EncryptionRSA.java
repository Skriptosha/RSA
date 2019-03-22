package RSA.encryption;

import RSA.Parameters;
import RSA.Timer;
import RSA.logic.GetUniqueFromArray;
import RSA.logic.MixByte;
import RSA.math.ExponentiationByModule;
import tools.GetConfig;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class EncryptionRSA {

    public BigInteger[] encryptionRSA(byte[] message) {
        ReadPublicKey readPublicKey = new ReadPublicKey();
        Timer timer = new Timer();

        if (readPublicKey.readPublicKey()) {
            System.out.println("Длина Сообщения для зашифрования: " + message.length);
            GetUniqueFromArray getUniqueFromArray = new GetUniqueFromArray();
            BigInteger[] mixBigIntegers;
            BigInteger keyE = new BigInteger(readPublicKey.getE(), Parameters.RadixForKeys);
            BigInteger keyN = new BigInteger(readPublicKey.getN(), Parameters.RadixForKeys);
            timer.start();
            MixByte mixByte = null;
            ExponentiationByModule exp = null;
            try {
                exp = (ExponentiationByModule) Class.forName(GetConfig.getProperty("ExponentiationByModuleClass"))
                        .newInstance();
                mixByte = (MixByte) Class.forName(GetConfig.getProperty("MixByte")).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            assert exp != null;
            mixBigIntegers = mixByte.mixByte(message, keyN);

            ArrayList<BigInteger> uniqueList = getUniqueFromArray.getUniqueFromArray(mixBigIntegers);
            BigInteger[] resultList = new BigInteger[uniqueList.size()];



            for (int i = 0; i < uniqueList.size(); i++) {
                resultList[i] = exp.getExponentiationByModule(uniqueList.get(i), keyE, keyN);
            }
            System.out.println("encryptionRSA общий таймер: " + timer.stopTimeMillis());
//            return resultList;
            return getUniqueFromArray.fillArrayFromUniqueBigInteger(mixBigIntegers
                    , new ArrayList<>(Arrays.asList(resultList))).toArray(new BigInteger[0]);
        } else {
            System.out.println("Не удалось прочитать ключи, необходима генерация новых");
        }
        return null;
    }
}
