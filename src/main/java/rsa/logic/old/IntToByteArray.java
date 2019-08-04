package rsa.logic.old;

public class IntToByteArray {

    static byte[] intToByteArray(long i) {
        return new byte[] {
                (byte)((i) & 0xff),
                (byte)((i >> 8) & 0xff),
                (byte)((i >> 16) & 0xff),
                (byte)((i >> 24) & 0xff),
                (byte)((i >> 32) & 0xff),
                (byte)((i >> 40) & 0xff),
                (byte)((i >> 48) & 0xff),
                (byte)((i >> 56) & 0xff),
        };
    }
}
