package rsa.logic;

import rsa.Timer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class GetUniqueFromArray {
    private static HashMap<BigInteger, BigInteger> unique_bb;
    private static ArrayList<BigInteger> uniqueList;

    public static ArrayList<BigInteger> getUniqueFromArray(BigInteger[] dec){
        Timer timer = new Timer();
        timer.start();
        unique_bb = new HashMap<>();
        System.out.println("dec.size(): " + dec.length);
        for (BigInteger i : dec) {
            unique_bb.put(i, null);
        }
        System.out.println("unique_bi.size(): " + unique_bb.size());
        System.out.println("getUniqueFromArray timer: " + timer.stopTimeMillis());
        return uniqueList = new ArrayList<>(unique_bb.keySet());
    }

    public static ArrayList<BigInteger> fillArrayFromUniqueBigInteger(BigInteger[] mixBigIntegers
            , ArrayList<BigInteger> resultList){
        unique_bb = new HashMap<>(uniqueList.size());
        for (int i = 0; i < uniqueList.size(); i++){
            unique_bb.put(uniqueList.get(i), resultList.get(i));
        }
        resultList = new ArrayList<>(mixBigIntegers.length);
        for (BigInteger anInt : mixBigIntegers) {
            resultList.add(unique_bb.get(anInt));
        }
        return resultList;
    }
}
