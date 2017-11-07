/**
 * Genre
 */

import java.util.*;


public class Genre {

    String genreID;
    HashSet<Integer> movieSet;  //List of Movies

    public Genre(String genreID) {
        this.genreID = genreID;
    }

    public void addMovie(int movieID) {
        if (movieSet == null) {
            movieSet = new HashSet<>();
        }
        movieSet.add(movieID);
    }

}