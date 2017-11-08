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
    TreeSet<TagPair> tagTree;
    HashMap<Integer, Double> userRating; // List of user and their ratings
    HashMap<Integer, Double> userTag; // (tagID, weight) count and then normalize

    double ratingSum = 0;
    double averageRating = 0;

    public Movie(int movieId) {
        this.movieId = movieId;
        genreSet = new HashSet<>();
        actorTree = new TreeSet<>();
        tagTree = new TreeSet<>();
        userRating = new HashMap<>();
    }

    public void setDirector(String directorId) {
        this.directorId = directorId;
    }

    public void addGenre(String genre) {
        genreSet.add(genre);
    }

    public void addTag(int tagId) {
        tagSet.add(tagId);
    }

    public void addActor(String actorID, int rank) {
        actorTree.add(new ActorPair(actorID, rank));
    }

    public void addTag(int tagID, int weight) {
        tagTree.add(new TagPair(tagID, weight));
    }

    public void addUserRating(int userId, double rating) {
        this.ratingSum += rating;
        userRating.put(userId, rating);
        this.averageRating = this.ratingSum / (double) this.userRating.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Miner.TOP);
        sb.append("MovieID: " + this.movieId + "\n");
        sb.append("DirectorID: " + this.directorId + "\n");
        sb.append("Genres: " + genreSet.toString() + "\n");
        sb.append("Actor List: " + actorTree.toString() + "\n");
        sb.append("Views: " + this.userRating.size() + "\n");
        sb.append("Average Rating: " + this.averageRating + "\n");
        sb.append("Tag List: " + tagTree.toString() + "\n");
        sb.append(Miner.BOTTOM);
        return sb.toString();
    }

}