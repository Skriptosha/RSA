package RSA.encryption;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;

public interface ReadFile {

    List<String> readFile(String path, Charset cs) throws IOException;
}
