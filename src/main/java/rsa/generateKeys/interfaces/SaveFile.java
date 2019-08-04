package rsa.generateKeys.interfaces;

import java.nio.charset.Charset;
import java.util.ArrayList;

public interface SaveFile {

    boolean saveFile(String path, ArrayList<String> mass, Charset cs);

    boolean saveFile(String path, byte[] mass);
}
