import java.util.ArrayList;

/**
 * GroupMiner
 */
public class GroupMiner implements Runnable {

    int start;
    int end;
    ArrayList<String> strList;

    public GroupMiner(int start, int end, ArrayList<String> strList) {
        this.start = start;
        this.end = end;
        this.strList = strList;
    }

    public void run() {
        System.out.println("Thread Started");
        for (int i = start; i < end; i++) {
            HardWorker hw = new HardWorker(i, strList.get(i));
            hw.work();
        }
    }

}