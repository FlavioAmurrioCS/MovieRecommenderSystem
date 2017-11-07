/**
 * Miner
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

public class Miner {

    public static final String TESTFILE = "./res/additional_files/./res/additional_files/test.dat"; //userID,movieID  75,653
    public static final String TRAINFILE = "./res/additional_files/train.dat"; //userID,movieID,rating 75,3,1

    public static final String MOVIE_ACTORS_FILE = "./res/additional_files/movie_actors.dat"; //movieID,actorID,actorName,ranking  1,annie_potts,Annie Potts, 10
    public static final String MOVIE_DIRECTORS_FILE = "./res/additional_files/movie_directors.dat"; //movieID,directorID,directorName  1,john_lasseter,John Lasseter
    public static final String MOVIE_GENRES_FILE = "./res/additional_files/movie_genres.dat"; //movieID,genre  1,Adventure
    public static final String MOVIE_TAGS_FILE = "./res/additional_files/movie_tags.dat"; //movieID,tagID,tagWeight   1,7,1

    public static final String USER_TAGGED_MOVIES_FILE = "./res/additional_files/user_taggedmovies.dat"; //userID,movieID,tagID    75,353,5290

    public static HashMap<Integer, Movie> movieMap = new HashMap<>();
    public static HashMap<String, Director> directorMap = new HashMap<>();


    public static void main(String[] args) {
        step1();
        //Comment
    }

    //Read Movie_Directors_File
    public static void step1()
    {
        Scanner sc = Tools.fileReader(MOVIE_DIRECTORS_FILE);
        sc.nextLine();
        while(sc.hasNextLine())
        {
            int mid = sc.nextInt();
            String did =sc.next();
            sc.nextLine();
            Movie movie = addMovieMap(mid);
            Director director = addDirectorMap(did);
            movie.setDirector(did);
            director.addMovie(mid);

        }
    }

    public static Director addDirectorMap(String directorId){
        Director d = null;
        if(directorMap.containsKey(directorId))
        {
            d = directorMap.get(directorId);
        }
        else{
            d = new Director(directorId);
            directorMap.put(directorId, d);
        }
        return d;

    }

    public static Movie addMovieMap(int movieId){
        Movie m = null;
        if(movieMap.containsKey(movieId)){
            m = movieMap.get(movieId);
        }
        else{
            m = new Movie(movieId);
            movieMap.put(movieId, m);
        }
        return m;
    }

}