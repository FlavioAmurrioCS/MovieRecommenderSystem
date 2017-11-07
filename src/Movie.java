import java.util.HashMap;
import java.util.HashSet;

/**
 * Movie
 */
public class Movie {

    int movieId;
    String directorId; // Director director;
    HashSet<String> genreSet; // List of Genre(genre)
    HashSet<Integer> tagSet; // List of Movie Tags(tagID)
    HashMap<String, Double> actorRank; // List of Actors (actor_id, rank) normalize the rank
    HashMap<Integer, Double> userRating; // List of user and their ratings
    HashMap<Integer, Double> userTag; // (tagID, weight) count and then normalize

    public Movie(int movieId) {
        this.movieId = movieId;
    }

    public void setDirector(String directorId) {
        this.directorId = directorId;
    }

    public void addGenre(String genre) {
        if (genreSet == null)
            genreSet = new HashSet<>();
        genreSet.add(genre);
    }

    public void addTag(int tagId) {
        if (tagSet == null)
            tagSet = new HashSet<>();
        tagSet.add(tagId);
    }

    public void addActor(String actorId, double ranking) {
        if (actorRank == null)
            actorRank = new HashMap<>();
        actorRank.put(actorId, ranking);
    }

    public void addUserRating(int userId, double rating) {
        if (userRating == null)
            userRating = new HashMap<>();
        userRating.put(userId, rating);
    }

}