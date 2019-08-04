package rsa.math.realization;

import java.math.BigInteger;

public class TrialDivision {

    /**
     * Проверяем число primeNumber делением на простые числа до 257
     *
     * @param primeNumber нечетное число, которое нужно проверить на простоту
     * @return true если число прошло проверку на деление, false если составное
     */
    public boolean trialDivision(BigInteger primeNumber){
        String[] primeNumbers = new String[]{"2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37",
        "41", "43", "47", "53", "59", "61", "67", "71", "73", "79", "83", "89", "97", "101", "103", "107", "109", "113",
        "127", "131", "137", "139", "149", "151", "157", "163", "167", "173", "179", "181", "191", "193", "197", "199",
        "211", "223", "239", "241", "251", "257", "65537"};
        if (primeNumber.min(new BigInteger(primeNumbers[primeNumbers.length - 1])).equals(primeNumber))
            throw new ArithmeticException("Число должно быть больше " + primeNumbers[primeNumbers.length - 1] + "!");
        for (String x : primeNumbers) {
            if (primeNumber.mod(new BigInteger(x)).equals(BigInteger.ZERO)) return false;
        }
        return true;
    }
}
