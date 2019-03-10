package RSA;

import RSA.math.EulerFunction;
import RSA.math.ExponentiationByModule;
import RSA.math.ExponentiationByModuleModPow;
import RSA.math.GetSecretKeyJavamodInverse;

import java.math.BigInteger;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        Timer timer = new Timer(), timer2 = new Timer();
//        int a = -48;
//        int b = -35;
//        int c = 164;
//        int d = -144;
//        c = ((c + a + b) * d) % 49247;
//        System.out.println(c);
//        c = ((c - a - b) / d) % 49247;
//        System.out.println(c);
//        System.out.println(LocalDate.now());
        BigInteger e = new BigInteger("3");
        BigInteger p = new BigInteger("3557");
        BigInteger q = new BigInteger("2639");
        BigInteger d;
        BigInteger n;
        ExponentiationByModule byModule = new ExponentiationByModuleModPow();
        ExponentiationByModule byModule2 = new ExponentiationByModuleModPow();
        BigInteger euler = EulerFunction.eulerFunction(p, q);
        d = new GetSecretKeyJavamodInverse().getSecretKey(e, euler);
        n = q.multiply(p);
        byModule.setParameters(new BigInteger("111111"), e, new BigInteger("9173503"));
        byModule.exponentiationByModule();
        byModule2.setParameters(byModule.getExponentiationByModule(), d, n);
        byModule2.exponentiationByModule();
//        System.out.println(byModule.getExponentiationByModule());
        System.out.println(byModule2.getExponentiationByModule());
//        RandomBitsGeneration generation = new RandomBitsGeneration();
//        generation.getRandomNumber(1024);
//        TranslatesStringIntoByte stringIntoByte = new TranslatesStringIntoByte();
//        TranslatesBytesIntoString bytesIntoString = new TranslatesBytesIntoString();
//        int[] temp = stringIntoByte.translatesStringIntoByte("Старый", new BigInteger("3547"));
//        System.out.println(bytesIntoString.translatesBytesIntoString(temp, new BigInteger("3547")));
//        ExponentiationByModule byModule = new ExponentiationByModuleAlgRightToLeft();
//        byModule.setParameters(new BigInteger("-4051753"), new BigInteger("6111579"), new BigInteger("9173503"), Test.class);
//        timer.start();
//        ((ExponentiationByModuleAlgRightToLeft) byModule).run();
//        System.out.println("ExponentiationByModuleAlgRightToLeft timer: " + timer.stopTimeMillis());
//        System.out.println(byModule.getExponentiationByModule());
//        timer2.start();
//        System.out.println(new BigInteger("-4051753").modPow(new BigInteger("6111579"), new BigInteger("9173503")));
//        System.out.println("modPow timer: " + timer2.stopTimeMillis());
//        translatesStringIntoByte.translatesStringIntoByte("Hello World", new BigInteger(""));
    }
}
