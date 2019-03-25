import RSA.Parameters;
import RSA.encryption.ZipFileInterface;
import RSA.encryption.ZipFileWin;
import org.junit.Assert;
import org.junit.Test;
import tools.GetConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestZipFile extends BaseTestClass {
    private String path = "test.zip";

    @Test
    public void testZipFile(){
        int count = Integer.parseInt(GetConfig.getProperty("Count"));
        List<String> list = new ArrayList<>(count);
        List<String> list2;
        byte[] bytes = new byte[Integer.parseInt(GetConfig.getProperty("ArrayForZipSize"))];
        for (int i = 0; i < count; i++) {
            new Random().nextBytes(bytes);
            list.add(new String(bytes));
        }
        ZipFileInterface zipFileInterface = new ZipFileWin();
        zipFileInterface.zipFile(path, list, Parameters.Encode);
        list2 = zipFileInterface.unZipFile(path, Parameters.Encode);
        Assert.assertEquals(list.size(), list2.size());
        for (int i1 = 0; i1 < list.size(); i1++) {
            String b = list.get(i1);
            String c = list2.get(i1);
            Assert.assertEquals(b, c);
        }
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testZipFile2(){
        byte[] bytes = new byte[Integer.parseInt(GetConfig.getProperty("ArraySize"))];
        byte[] result;
        ZipFileInterface zipFileInterface = new ZipFileWin();
        zipFileInterface.zipFile(path, bytes);
        result = zipFileInterface.unZipFile(path);
        Assert.assertEquals(bytes.length, result.length);
        for (int i = 0; i < bytes.length; i++) {
            Assert.assertEquals("Элементы массива № " + i + " двух массивов не равны", bytes[i], result[i]);
        }
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
