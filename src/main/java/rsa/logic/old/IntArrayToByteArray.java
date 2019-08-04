package rsa.logic.old;

public class IntArrayToByteArray {

    public byte[] intArrayToByteArray(long[] ints){

        int bitness = 8;
        byte[] bytes = new byte[ints.length*bitness];
        byte[] temp;
        int c = 0;
        for (int i = 0; i < ints.length; i++) {
            if (i > ints.length - bitness && ints[i] > -128 && ints[i] < 128){
                bytes[c] = (byte) ints[i];
                c++;
            }
            else {
                temp = IntToByteArray.intToByteArray(ints[i]);
                for (int j = 0; j < bitness; j++) {
                    bytes[c] = temp[j];
                    c++;
                }
            }
        }
//        if (c < bytes.length){
//            byte[] bytes1 = new byte[c];
//            for (int i = 0; i < bytes.length; i++) {
//                if (i < c) bytes1[i] = bytes[i];
//                else break;
//            }
//            bytes = bytes1;
//        }
//        for (byte i : bytes) {
//            System.out.print(i + ";");
//        }
        return bytes;
    }
}
