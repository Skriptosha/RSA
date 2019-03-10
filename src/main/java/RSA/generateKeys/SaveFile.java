package RSA.generateKeys;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;

public interface SaveFile {

    boolean saveFile(Path path, ArrayList<String> mass, Charset cs);
}
