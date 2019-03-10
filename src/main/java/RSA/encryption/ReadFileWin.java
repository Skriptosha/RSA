package RSA.encryption;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFileWin implements ReadFile {
    @Override
    public List<String> readFile(String path, Charset cs) throws IOException {
//        Files.lines(Paths.get(path), Charset.forName("windows-1251"));
        return Files.readAllLines(Paths.get(path), cs);
    }
}
