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
}