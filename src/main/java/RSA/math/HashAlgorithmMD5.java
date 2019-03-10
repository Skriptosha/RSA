package RSA.math;

import RSA.Parameters;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashAlgorithmMD5 implements HashAlgorithm {

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

    @Override
    public boolean isHashEquals(String hash, String message) {
        String hash2 = getHashString(message);
        return hash.equals(hash2);
    }
}
