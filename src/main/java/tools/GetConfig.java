package tools;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class GetConfig {
    private static ConcurrentHashMap<Long, Properties> thread_Config = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Long, String> thread_nameFileProperties = new ConcurrentHashMap<>();
    private static final String Path1 = "src/test/resources/";
    private static final String Path2 = "src/main/resources/";

    /**
     * Файл properties должен находиться по пути Path1 или Path2. Для загрузки необходимого файла properties,
     * необходимо вызвать setNameProperties с именем файла.
     * Далее для получения значения вызывается getProperty(key)
     */
    public static void setNameProperties(String nameProperties) {
        readFile(nameProperties);
    }

    private static void readFile(String nameProperties) {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        if (nameProperties != null && !"".equals(nameProperties)) {
            try {
                if (Files.exists(Paths.get(Path1 + nameProperties + ".properties"), LinkOption.NOFOLLOW_LINKS))
                fileInputStream = new FileInputStream(Path1 + nameProperties + ".properties");
                else fileInputStream = new FileInputStream(Path2 + nameProperties + ".properties");
                properties.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null)
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            thread_nameFileProperties.put(Thread.currentThread().getId(), nameProperties);
            thread_Config.put(Thread.currentThread().getId(), properties);
        } else {
            throw new IllegalStateException("Необходимо вызвать setNameProperties!");
        }
    }

    public static String getProperty(String key) {
        String encodeString = thread_Config.get(Thread.currentThread().getId()).getProperty(key);
        if (encodeString == null) {
            throw new IllegalStateException("Не найдено значение по ключу " + key + "! Файл properties: "
                    + thread_nameFileProperties.get(Thread.currentThread().getId()));
        }
        return encodeString;
    }
}