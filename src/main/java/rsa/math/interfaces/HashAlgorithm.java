package rsa.math.interfaces;

/**
 * Алгоритм хеширования, который будет применен для хранения ключей.
 */
public interface HashAlgorithm {

    String getHashString(String message);

    boolean isHashEquals(String hash, String message);
}
