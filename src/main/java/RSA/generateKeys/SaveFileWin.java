package RSA.generateKeys;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class SaveFileWin implements SaveFile {
    @Override
    public boolean saveFile(String path, ArrayList<String> mass, Charset cs) {
        try {
            Files.deleteIfExists(Paths.get(path));
            Files.write(Paths.get(path), mass, cs, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFile(String path, byte[] mass) {
        try {
            Files.deleteIfExists(Paths.get(path));
            Files.write(Paths.get(path), mass, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
