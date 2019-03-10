package RSA.logic;

import java.nio.ByteBuffer;

public class ByteArrayToInt {

    public static int byteArrayToInt(byte[] bytes){
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return byteBuffer.getInt();
    }
}
