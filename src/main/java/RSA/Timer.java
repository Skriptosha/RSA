package RSA;

public class Timer {
    private long startTime;

    public void start(){
        startTime = System.currentTimeMillis();
    }

    public long stopTimeMillis(){
        long temp = System.currentTimeMillis() - startTime;
        startTime = 0;
        return temp;
    }

    public long stopTimeSeconds(){
        long temp = (System.currentTimeMillis() - startTime)/1000;
        startTime = 0;
        return temp;
    }
}
