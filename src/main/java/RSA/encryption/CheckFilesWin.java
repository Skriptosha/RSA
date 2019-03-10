package RSA.encryption;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class CheckFilesWin implements CheckFiles {
    @Override
    public boolean checkFiles(Path path) {
        return Files.isReadable(path) && Files.exists(path, LinkOption.NOFOLLOW_LINKS);
    }
}
