import java.util.*;

/**
 * Actor
 */

public class Actor {

    String actorID;
    HashSet<Integer> movieSet;

    public Actor(String actorID) {
        this.actorID = actorID;
    }

    public void addMovie(int movieID) {
        if (movieSet == null) {
            movieSet = new HashSet<>();
        }
        movieSet.add(movieID);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(Miner.TOP);
        sb.append("ActorID: " + this.actorID + "\n");
        sb.append("Movie List: " + this.movieSet.toString() + "\n");
        sb.append(Miner.BOTTOM);
        return sb.toString();
    }
}