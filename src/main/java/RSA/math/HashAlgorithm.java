package RSA.math;

public interface HashAlgorithm {

    String getHashString(String message);

    boolean isHashEquals(String hash, String message);
}
