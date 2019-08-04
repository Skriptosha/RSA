package rsa.encryption.interfaces;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.List;

public interface ZipFileInterface {

    boolean zipFile(String path, List<String> list, Charset cs);

    boolean zipFile(String path, byte[] mass);

    List<String> unZipFile(String path, Charset cs);

    byte[] unZipFile(String path);

    byte[] convertBigInteger(BigInteger[] mass);

    BigInteger[] convertByte(byte[] mass);
}
