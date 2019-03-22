package RSA;

public class Test {
    public static void main(String[] args) {
        Timer timer = new Timer(), timer2 = new Timer();
        int a = 35699;
        int b = 400;
        System.out.println(a/b);
        System.out.println(a/b % 8);
        System.out.println("result: " + (a/b - a/b % 8));
        a = 31;
        b = 8;
        System.out.println(a/b);
        System.out.println(a/b % 8);
        System.out.println("result: " + (a/b + (8 - (a/b % 8))));
//        int c = 164;
//        int d = -144;
//        c = ((c + a + b) * d) % 49247;
//        System.out.println(c);
//        c = ((c - a - b) / d) % 49247;
//        System.out.println(c);
//        System.out.println(LocalDate.now());

//        BigInteger a = new BigInteger("-48");
//        BigInteger p = new BigInteger("-35");
//        BigInteger q = new BigInteger("164");
//        BigInteger d = new BigInteger("-144");
//        BigInteger s = new BigInteger("1233");
//        BigInteger w;
//        d = (a.add(p).add(q).add(d)).remainder(s);
//        System.out.println(d);
//        d = (d.subtract(q).subtract(p).subtract(a)).remainder(s);
//        System.out.println(d);
//
    }
}
