package rsa.math.realization;

import rsa.config.Parameters;
import rsa.math.interfaces.HashAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Алгоритм хеширования MD5
 */
public class HashAlgorithmMD5 implements HashAlgorithm {

    /**
     * Получить хеш строки типа String
     *
     * @param message стока String
     * @return хеш строки
     */
    @Override
    public String getHashString(String message) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(Parameters.HashAlgorithm);
            byte[] hash = md.digest(message.getBytes(Parameters.Encode));
            result = Base64.getEncoder().encodeToString(hash);
            md.reset();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Проверить, одинаковые ли хеши
     * @param hash хеш, с которым надо будет сравнить
     * @param message строка, по которой надо будет вычислить хеш для сравнения
     * @return true - если хеши равны, false - если нет.
     */
    @Override
    public boolean isHashEquals(String hash, String message) {
        String hash2 = getHashString(message);
        return hash.equals(hash2);
    }
}
