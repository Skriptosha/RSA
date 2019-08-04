package rsa.encryption.interfaces;

import java.nio.charset.Charset;
import java.util.List;

public interface ReadFile {

    List<String> readFile(String path, Charset cs);

    byte[] readFile(String path);
}
