package function.java.syn;

/**
 * 同步的 synchronized 实现
 */
public class Testsynchronized {

    public int inc = 0;

    public synchronized void increase() {
        inc++;
    }

}