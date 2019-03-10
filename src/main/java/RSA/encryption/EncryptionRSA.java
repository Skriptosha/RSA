package RSA.encryption;

import RSA.Parameters;
import RSA.Timer;
import RSA.logic.ConversionByDependenceOfByte;
import RSA.logic.GetUniqueFromArray;
import RSA.math.ExponentiationByModule;
import RSA.math.ExponentiationByModuleModPow;

import java.math.BigInteger;
import java.util.ArrayList;

public class EncryptionRSA {

    public BigInteger[] encryptionRSA(String message) {
        ReadPublicKey readPublicKey = new ReadPublicKey();
        if (readPublicKey.readPublicKey()) {
            System.out.println("Длина Сообщения для зашифрования: " + message.length());
//        System.out.println();
            Timer timer = new Timer();
            int[] ints;
            BigInteger keyE = new BigInteger(readPublicKey.getE(), Parameters.RadixForKeys);
            BigInteger keyN = new BigInteger(readPublicKey.getN(), Parameters.RadixForKeys);
            timer.start();
            ConversionByDependenceOfByte byDependenceOfByte = new ConversionByDependenceOfByte();
            GetUniqueFromArray getUniqueFromArray = new GetUniqueFromArray();
            ints = byDependenceOfByte.translatesStringIntoByte(message, keyN);
            ArrayList<BigInteger> uniqueList = getUniqueFromArray.getUniqueFromArray(ints);

            ArrayList<BigInteger> resultList = new ArrayList<>();

            for (BigInteger a : uniqueList) {
                ExponentiationByModule exp = new ExponentiationByModuleModPow();
                exp.setParameters(a, keyE, keyN);
                exp.exponentiationByModule();
                resultList.add(exp.getExponentiationByModule());
            }
            System.out.println("encryptionRSA общий таймер: " + timer.stopTimeMillis());
            return getUniqueFromArray.fillArrayFromUniqueBigInteger(ints, resultList).toArray(new BigInteger[0]);
        }
        return null;
    }
}
