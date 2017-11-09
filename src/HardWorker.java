/**
 * HardWorker
implements Runnable */
public class HardWorker implements Runnable {

    String threadID;

    public HardWorker(String threadID) {
        this.threadID = threadID;

    }

    public void run() {
        System.out.println("Hello from thread " + this.threadID + "!");
    }
}