package RSA.generateKeys;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class SaveFileWin implements SaveFile {
    @Override
    public boolean saveFile(Path path, ArrayList<String> mass, Charset cs) {
        try {
            Files.deleteIfExists(path);
            Files.write(path, mass, cs, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
