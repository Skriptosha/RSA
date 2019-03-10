package RSA.logic;

import RSA.Parameters;
import RSA.Timer;

import java.math.BigInteger;
import java.util.Random;

public class ConversionByDependenceOfByte implements ConversionStringAndByte {
    @Override
    public int[] translatesStringIntoByte(String message, BigInteger n) {
        Random random = new Random();
        Timer timer = new Timer();
        int randomized;
        int newN = n.and(new BigInteger(Parameters.CoefficientForAndOperation, 16)).intValue();
        if (newN > 0) newN *= -1;
//        System.out.println("newN: " + newN);
        byte[] tempByte = message.getBytes(Parameters.Encode);
        randomized = (tempByte[random.nextInt(tempByte.length - 1)]
                * tempByte[random.nextInt(tempByte.length - 1)])
//                * tempByte[random.nextInt(tempByte.length - 1)])
                * 2/3 - 1;
        if (randomized < 0) randomized *= -1;
//        System.out.println("randomized: " + randomized);
//        int[] tempInt = new int[tempByte.length];
        int[] resultInt = new int[tempByte.length + 1];
        for (byte i : tempByte) {
            System.out.print(i + ";");
        }
//        for (int i = 0; i < tempByte.length; i++) {
//            tempInt[i] = tempByte[i];
//        }
        timer.start();
        System.out.println();
        resultInt[0] = randomized;
        for (int i = 1; i < resultInt.length; i++) {
            if (i == 1) resultInt[i] = (tempByte[i - 1] + randomized + randomized/2) % newN;
            else if (i == 2) resultInt[i] = (tempByte[i - 1] + tempByte[i - 2] + randomized) % newN;
            else if (i == 3)resultInt[i] = (tempByte[i - 1] + tempByte[i - 2] + tempByte[i - 3] + randomized) % newN;
            else resultInt[i] = (tempByte[i - 1] + tempByte[i - 2] + tempByte[i - 3] + tempByte[i - 4]
                        + randomized) % newN;
        }
//        System.out.println("translatesStringIntoByte общий таймер: " + timer.stopTimeMillis());
        for (int i : resultInt) {
            System.out.print(i + ";");
        }
//        System.out.println("resultInt.length: " + resultInt.length);
        return resultInt;
    }

    @Override
    public String translatesBytesIntoString(Integer[] ciphertext, BigInteger n) {
        int newN = n.and(new BigInteger(Parameters.CoefficientForAndOperation, 16)).intValue();
        if (newN > 0) newN *= -1;
        int randomized;
        Timer timer = new Timer();
//        System.out.println();
//        System.out.println("newN: " + newN);
        for (int i : ciphertext) {
            System.out.print(i + ";");
        }
        System.out.println();
        timer.start();
        randomized = ciphertext[0];
        byte[] tempByte = new byte[ciphertext.length - 1];
        for (int i = 1; i < ciphertext.length; i++) {
            if (i == 1) tempByte[i - 1] = (byte) ((ciphertext[i] - randomized - randomized/2) % newN);
            else if (i == 2) tempByte[i - 1] = (byte)((ciphertext[i] - tempByte[i - 2] - randomized) % newN);
            else if (i == 3) tempByte[i - 1] = (byte)((ciphertext[i] - tempByte[i - 2] - tempByte[i - 3]
                    - randomized) % newN);
            else tempByte[i - 1] = (byte)((ciphertext[i] - tempByte[i - 2] - tempByte[i - 3]  - tempByte[i - 4]
                        - randomized) % newN);
        }
//        System.out.println("translatesBytesIntoString общий таймер: " + timer.stopTimeMillis());
        for (int i : tempByte) {
            System.out.print(i + ";");
        }
        System.out.println();
        return new String(tempByte, Parameters.Encode);
    }
}
