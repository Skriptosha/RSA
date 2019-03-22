package RSA;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Parameters {
    public static final Charset Encode = StandardCharsets.UTF_8;
    public static final String[] Fermat = new String[]{"17", "257", "65537"};
    public static final String SavePrivateKey1Path = "key1.txt";
    public static final String SavePrivateKey2Path = "key2.txt";
    public static final String SavePublicKey1Path = "publicKey.txt";
    public static final int PrivateKeyValidTime = 30;
    public static final int RadixForKeys = 16;
    public static final int availableProcessorsactor = 2;
    public static final int keyLength = 1024;
    public static final String HashAlgorithm = "MD5";
    public static final int PackagingFactor = 5;
}
