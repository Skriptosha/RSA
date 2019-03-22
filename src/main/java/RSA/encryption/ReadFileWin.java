package RSA.encryption;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFileWin implements ReadFile {
    @Override
    public List<String> readFile(String path, Charset cs){
        List<String> list = null;
        try {
            list = Files.readAllLines(Paths.get(path), cs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public byte[] readFile(String path){
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
