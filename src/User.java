/**
 * User
 */
import java.util.*;

public class User {
    int userID;
    HashMap<Integer, Double> movieMap;

    public User(int userID) {
        this.userID = userID;
        movieMap = new HashMap<>();
    }

    public void addMovie(int movieID, double rating) {
        movieMap.put(movieID, rating);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(Miner.TOP);
        sb.append("UserID: " + this.userID + "\n");
        sb.append("Movie List: " + this.movieMap.keySet().toString() + "\n");
        sb.append(Miner.BOTTOM);
        return sb.toString();
    }
}