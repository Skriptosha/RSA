package rsa.math.realization;

import java.math.BigInteger;

public class EulerFunction {
    /**
     * Фу́нкция Э́йлера {(n)}
     *
     * @param p простое число типа BigInteger
     * @param q простое число типа BigInteger
     * @return значение функции Эйлера типа BigInteger
     */
    public static BigInteger eulerFunction(BigInteger p, BigInteger q){
        return (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));
    }
}
