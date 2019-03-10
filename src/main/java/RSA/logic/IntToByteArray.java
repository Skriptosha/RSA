package RSA.logic;

import java.nio.ByteBuffer;

public class IntToByteArray {

    byte[] intToByteArray(int i) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(i);
        return bb.array();
    }
}
