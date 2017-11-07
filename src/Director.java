
/**
 * Director
 */

import java.util.*;

public class Director {

    String directorId;
    HashSet<Integer> movieSet; // List of movies

    public Director(String directorId) {
        this.directorId = directorId;
    }

    public void addMovie(int movieId) {
        if (movieSet == null) {
            movieSet = new HashSet<>();
        }
        movieSet.add(movieId);
    }
}