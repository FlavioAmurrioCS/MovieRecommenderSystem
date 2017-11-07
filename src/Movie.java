import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Movie
 */
public class Movie {

    int movieId;
    String directorId; // Director director;
    HashSet<String> genreSet; // List of Genre(genre)
    HashSet<Integer> tagSet; // List of Movie Tags(tagID)
    // HashMap<String, Double> actorRank; // List of Actors (actor_id, rank) normalize the rank
    // ArrayList<ActorPair> actorList;
    TreeSet<ActorPair> actorTree;
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

    public void addActor(String actorID, int rank) {
        if (actorTree == null)
            actorTree = new TreeSet<>();
        actorTree.add(new ActorPair(actorID, rank));
    }

    public void addUserRating(int userId, double rating) {
        if (userRating == null)
            userRating = new HashMap<>();
        userRating.put(userId, rating);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MovieID: " + this.movieId + "\n");
        sb.append("DirectorID: " + this.directorId + "\n");
        sb.append("Genres: " + genreSet.toString()+ "\n");
        sb.append("Actor List: " + actorTree.toString() + "\n");
        return sb.toString();
    }

}