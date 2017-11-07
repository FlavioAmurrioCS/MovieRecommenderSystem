/**
 * Miner
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

public class Miner {

    public static final String TESTFILE = "./res/additional_files/test.dat"; //userID,movieID  75,653
    public static final String TRAINFILE = "./res/additional_files/train.dat"; //userID,movieID,rating 75,3,1

    public static final String MOVIE_DIRECTORS_FILE = "./res/additional_files/movie_directors.dat"; //movieID,directorID,directorName  1,john_lasseter,John Lasseter
    public static final String MOVIE_GENRES_FILE = "./res/additional_files/movie_genres.dat"; //movieID,genre  1,Adventure
    public static final String MOVIE_ACTORS_FILE = "./res/additional_files/movie_actors.dat"; //movieID,actorID,actorName,ranking  1,annie_potts,Annie Potts, 10
    public static final String MOVIE_TAGS_FILE = "./res/additional_files/movie_tags.dat"; //movieID,tagID,tagWeight   1,7,1

    public static final String USER_TAGGED_MOVIES_FILE = "./res/additional_files/user_taggedmovies.dat"; //userID,movieID,tagID    75,353,5290

    public static HashMap<Integer, Movie> movieMap = new HashMap<>();
    public static HashMap<String, Director> directorMap = new HashMap<>();
    public static HashMap<String, Actor> actorMap = new HashMap<>();
    public static HashMap<String, Genre> genreMap = new HashMap<>();
    


    public static void main(String[] args) {
        Timer timer = new Timer();
        step1();
        step2();
        step3();

        System.out.println("Read Files");
        timer.time();
        ArrayList<Integer> intList = new ArrayList<>(movieMap.keySet());
        Collections.sort(intList);
        System.out.println(intList.toString());
        System.out.println(intList.size());
        int count = 0;
        for(Integer mid : movieMap.keySet())
        {
            if(movieMap.get(mid).directorId == null)
                count++;
        }
        System.out.println(count);
        printMovieInfo();
    }

    ////movieID,directorID,directorName  1,john_lasseter,John Lasseter
    //Read Movie_Directors_File
    public static void step1(){
        Scanner sc = Tools.fileReader(MOVIE_DIRECTORS_FILE);
        sc.nextLine();
        while(sc.hasNextLine()){
            Scanner sc2 = new Scanner(sc.nextLine());
            int mid = sc2.nextInt();
            String did =sc2.next();
            sc2.close();
            Movie movie = addMovieMap(mid);
            Director director = addDirectorMap(did);
            movie.setDirector(did);
            director.addMovie(mid);
        }
        sc.close();
    }

    //movieID,genre  1,Adventure
    //Read MOVIE_GENRES_FILE
    public static void step2(){
        Scanner sc = Tools.fileReader(MOVIE_GENRES_FILE);
        sc.nextLine();
        while(sc.hasNextLine()){
            Scanner sc2 = new Scanner(sc.nextLine());
            int mid = sc2.nextInt();
            String genreID = sc2.next();
            sc2.close();
            Genre genre = addGenreMap(genreID);
            Movie movie = addMovieMap(mid);
            genre.addMovie(mid);
            movie.addGenre(genreID);
        }
        sc.close();
    }

    //movieID,actorID,actorName,ranking  1,annie_potts,Annie Potts, 10
    //Read MOVIE_ACTORS_FILE
    public static void step3(){
        Scanner sc = Tools.fileReader(MOVIE_ACTORS_FILE);
        sc.nextLine();
        while(sc.hasNextLine()){
            String[] line = sc.nextLine().split("\t");
            int mid = Integer.parseInt(line[0]);
            String actorID =line[1];
            int rank = Integer.parseInt(line[line.length -1]);

            Movie movie = addMovieMap(mid);
            movie.addActor(actorID, rank);

            Actor actor = addActorMap(actorID);
            actor.addMovie(mid);
        }
        sc.close();
    }

    public static Director addDirectorMap(String directorId){
        Director d = null;
        if(directorMap.containsKey(directorId)){
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

    public static Genre addGenreMap(String genreID){
        Genre genre = null;
        if(genreMap.containsKey(genreID)){
            genre = genreMap.get(genreID);
        }
        else{
            genre = new Genre(genreID);
            genreMap.put(genreID, genre);
        }
        return genre;
    }

    public static Actor addActorMap(String actorID){
        Actor actor = null;
        if(actorMap.containsKey(actorID))
            actor = actorMap.get(actorID);
        else{
            actor = new Actor(actorID);
            actorMap.put(actorID, actor);
        }
        return actor;
    }


    public static void printMovieInfo()
    {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            System.out.print("Enter MovieID: ");
            int mid = sc.nextInt();
            System.out.println(movieMap.get(mid).toString());
        }
    }

}