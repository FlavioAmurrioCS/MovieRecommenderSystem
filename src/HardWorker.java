import java.util.Scanner;

/**
 * HardWorker
implements Runnable */
public class HardWorker implements Runnable {

    int index;
    int userID;
    int movieID;

    public HardWorker(int i, String str) {
        this.index = i;
        Scanner sc = new Scanner(str);
        this.userID = sc.nextInt();
        this.movieID = sc.nextInt();
        sc.close();
    }

    public void run() {
        work();
    }

    public void work() {
        User user = Miner.userMap.get(userID);
        Movie movie = Miner.movieMap.get(movieID);
        double score = 0;
        // if (user == null) {
        //     if (movie == null) {
        //         score = 3.2;
        //     }
        //     score = movie.averageRating;
        // } else if (movie == null) {
        //     score = user.avgRating;
        // } else {
        //     score = user.predictRating(movieID);
        // }
        score = user.predictRating(movieID);
        Tools.syncInsert(this.index, score);
    }
}