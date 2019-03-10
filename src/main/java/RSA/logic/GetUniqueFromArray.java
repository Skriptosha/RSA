package RSA.logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class GetUniqueFromArray {
    private HashMap<BigInteger, Integer> unique_bi = new HashMap<>();
    private HashMap<BigInteger, BigInteger> unique_bb = new HashMap<>();
    private ArrayList<BigInteger> uniqueList;

    public ArrayList<BigInteger> getUniqueFromArray(BigInteger[] dec){
        for (BigInteger i : dec) {
            unique_bi.put(i, null);
        }
        System.out.println("unique_bi.size(): " + unique_bi.size());
        return uniqueList = new ArrayList<>(unique_bi.keySet());
    }

    public ArrayList<BigInteger> getUniqueFromArray(int[] dec){
        for (Integer i : dec) {
            unique_bb.put(new BigInteger(String.valueOf(i)), null);
        }
        System.out.println("unique_bb.size(): " + unique_bb.size());
        return uniqueList = new ArrayList<>(unique_bb.keySet());
    }

    public ArrayList<Integer> fillArrayFromUniqueInteger(BigInteger[] dec, ArrayList<Integer> resultList){
        unique_bi.clear();
        for (int i = 0; i < uniqueList.size(); i++){
            unique_bi.put(uniqueList.get(i), resultList.get(i));
        }
        resultList.clear();
        for (int i = 0; i < dec.length;i++){
            resultList.add(i, unique_bi.get(dec[i]));
        }
       return resultList;
    }

    public ArrayList<BigInteger> fillArrayFromUniqueBigInteger(int[] ints, ArrayList<BigInteger> resultList){
        unique_bb.clear();
        for (int i = 0; i < uniqueList.size(); i++){
            unique_bb.put(uniqueList.get(i), resultList.get(i));
        }
        resultList.clear();
        for (int i = 0; i < ints.length;i++){
            resultList.add(i, unique_bb.get(new BigInteger(String.valueOf(ints[i]))));
        }
        return resultList;
    }
}
