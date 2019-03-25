package RSA.logic.old;

public class ByteArrayToInt {

    static long byteArrayToInt(byte[] bytes) {

        int bitness = 8;
        if (bytes == null) return 0x0;
        if (bytes.length < bitness) {
            byte[] temp = new byte[bitness];
            for (int i = 0; i < temp.length; i++) {
                if (i < bytes.length) temp[i] = bytes[i];
                else temp[i] = 0;
            }
            bytes = temp;
        }
        return (((long) bytes[7] << 56)
                | ((long) bytes[6] & 0xff) << 48
                | ((long) bytes[5] & 0xff) << 40
                | ((long) bytes[4] & 0xff) << 32
                | ((long) bytes[3] & 0xff) << 24
                | ((long) bytes[2] & 0xff) << 16
                | ((long) bytes[1] & 0xff) << 8
                | ((long) bytes[0] & 0xff)
        );
    }
}
