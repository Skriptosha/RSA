package RSA.logic.old;

public class ByteArrayToIntArray {

    public long[] byteArrayToIntArray(byte[] bytes) {

        int bitness = 8;
        byte[] tempFour = new byte[bitness];
        int size, overflow = 0;
        boolean integer;
        if (bytes.length % bitness == 0) {
            size = bytes.length / bitness;
            integer = true;
        } else {
            size = bytes.length / bitness + bytes.length % bitness;
            overflow = bytes.length - bytes.length % bitness;
            integer = false;
        }
        long[] resultInt = new long[size];
        int j = 0, c = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (!integer && i > overflow - 1) {
                resultInt[j] = bytes[i];
                j++;
            } else tempFour[c] = bytes[i];
            if (c == bitness - 1) {
                resultInt[j] = ByteArrayToInt.byteArrayToInt(tempFour);
                j++;
                c = 0;
            } else c++;
        }
        return resultInt;
    }
}
