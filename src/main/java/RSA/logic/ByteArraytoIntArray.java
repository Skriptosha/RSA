package RSA.logic;

public class ByteArraytoIntArray {

    public int[] ByteArraytoIntArray(byte[] bytes){

        byte[] tempFour = new byte[4];
        int[] resultInt = new int[(bytes.length + (bytes.length % 4))/4];
        int j = 0;
        int c = 0;
        for (int i = 0; i < bytes.length; i++){
            tempFour[c] = (byte)Math.abs(bytes[i]);
            c++;

            if (c < 3 && i == bytes.length - 1) {
                for (int s = 3 - c; s < 4; s++){
                    tempFour[s] = 0;
                    c++;
                }
            }
            if (c == 4) {
                resultInt[j] = ByteArrayToInt.byteArrayToInt(tempFour);
                j++;
                c = 0;
            }
        }

        return resultInt;
    }
}
