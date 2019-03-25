package RSA.encryption;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFileWin implements ZipFileInterface {

    @Override
    public boolean zipFile(String path, List<String> list, Charset cs) {
        List<byte[]> bytes = new ArrayList<>(list.size());
        for (String s : list) {
            bytes.add(s.getBytes(cs));
        }
        ZipOutputStream zos = null;
        try {
            Files.deleteIfExists(Paths.get(path + ".zip"));
            FileOutputStream fos = new FileOutputStream(path + ".zip");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            zos = new ZipOutputStream(bos, cs);
            int c = 0;
            for (byte[] b : bytes) {
                zos.putNextEntry(new ZipEntry("Encrypt" + c + ".txt"));
                zos.write(b);
                zos.closeEntry();
                c++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                assert zos != null;
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean zipFile(String path, byte[] mass) {
        ZipOutputStream zos = null;
        try {
            Files.deleteIfExists(Paths.get(path + ".zip"));
            FileOutputStream fos = new FileOutputStream(path + ".zip");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            zos = new ZipOutputStream(bos);
            zos.putNextEntry(new ZipEntry("Encrypt.txt"));
            zos.write(mass);
            zos.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                assert zos != null;
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public List<String> unZipFile(String path, Charset cs) {
        List<String> list = new ArrayList<>();
        ZipInputStream zis = null;
        byte[] bytes;
        long size;
        try {
            FileInputStream fis = new FileInputStream(path + ".zip");
            BufferedInputStream bis = new BufferedInputStream(fis);
            zis = new ZipInputStream(bis, cs);
            Enumeration zipEntryEnum = new ZipFile(path + ".zip").entries();
            while (zipEntryEnum.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) zipEntryEnum.nextElement();
                size = entry.getSize();
//                System.out.println(size);
                if (size > Integer.MAX_VALUE) throw new Exception("Слишком большой файл в архиве");
                zis.getNextEntry();
                bytes = new byte[(int) size];
                if (zis.read(bytes) != -1) list.add(new String(bytes, cs));
            }
            zis.closeEntry();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                assert zis != null;
                zis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public byte[] unZipFile(String path) {
        byte[] bytes;
        long size;
        ZipInputStream zis = null;
        try {
            FileInputStream fis = new FileInputStream(path + ".zip");
            BufferedInputStream bis = new BufferedInputStream(fis);
            zis = new ZipInputStream(bis);
            ZipFile zipFile = new ZipFile(path + ".zip");
            if (zipFile.size() > 1) throw new Exception("Архив содержит более одного файла");
            Enumeration zipEntryEnum = zipFile.entries();
            ZipEntry entry = (ZipEntry) zipEntryEnum.nextElement();
            size = entry.getSize();
            if (size > Integer.MAX_VALUE) throw new Exception("Слишком большой файл в архиве");
            zis.getNextEntry();
            bytes = new byte[(int) size];
            zis.read(bytes);
            zis.closeEntry();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                assert zis != null;
                zis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
