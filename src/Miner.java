
/**
 * Miner
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Miner {

    public static final String TESTFILE = "./res/additional_files/test.dat"; //userID,movieID  75,653
    public static final String TRAINFILE = "./res/additional_files/train.dat"; //userID,movieID,rating 75,3,1

    public static final String MOVIE_DIRECTORS_FILE = "./res/additional_files/movie_directors.dat"; //movieID,directorID,directorName  1,john_lasseter,John Lasseter
    public static final String MOVIE_GENRES_FILE = "./res/additional_files/movie_genres.dat"; //movieID,genre  1,Adventure
    public static final String MOVIE_ACTORS_FILE = "./res/additional_files/movie_actors.dat"; //movieID,actorID,actorName,ranking  1,annie_potts,Annie Potts, 10
    public static final String MOVIE_TAGS_FILE = "./res/additional_files/movie_tags.dat"; //movieID,tagID,tagWeight   1,7,1
    public static final String TAGS_FILE = "./res/additional_files/tags.dat"; //id,value   1,earth

    public static final String USER_TAGGED_MOVIES_FILE = "./res/additional_files/user_taggedmovies.dat"; //userID,movieID,tagID    75,353,5290

    public static HashMap<Integer, Movie> movieMap = new HashMap<>(13600);
    public static HashMap<String, Director> directorMap = new HashMap<>(5450);
    public static HashMap<String, Actor> actorMap = new HashMap<>(127100);
    public static HashMap<String, Genre> genreMap = new HashMap<>(30);
    public static HashMap<Integer, Tag> tagMap = new HashMap<>(7100);
    public static HashMap<Integer, String> tagList = new HashMap<>(17650);
    public static HashMap<Integer, User> userMap = new HashMap<>(2850);

    public static ArrayList<User> testList = new ArrayList<>();

    public static String TOP = "\n---------------------------------------------------------------------\n";
    public static String BOTTOM = "---------------------------------------------------------------------\n";

    public static void main(String[] args){

        // stepping();


        HardWorker hw1 = new HardWorker("1");
        HardWorker hw2 = new HardWorker("2");
        HardWorker hw3 = new HardWorker("3");
        HardWorker hw4 = new HardWorker("4");
        Thread t1 = new Thread(hw1);
        Thread t2 = new Thread(hw2);
        Thread t3 = new Thread(hw3);
        Thread t4 = new Thread(hw4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    public static void stepping(){
        Timer timer = new Timer();
        step1();
        timer.lap("Step 1");
        step2();
        timer.lap("Step 2");
        step3();
        timer.lap("Step 3");
        step4();
        timer.lap("Step 4");
        step5();
        timer.lap("Step 5");
        step6();
        timer.lap("Step 6");
        timer.time();
        // menu();
    }

    public static void step1() {
        // Read Movie_Directors_File
        // movieID,directorID,directorName  1,john_lasseter,John Lasseter        
        Scanner sc = Tools.fileReader(MOVIE_DIRECTORS_FILE);
        sc.nextLine();
        while (sc.hasNextLine()) {
            Scanner sc2 = new Scanner(sc.nextLine());
            int mid = sc2.nextInt();
            String did = sc2.next();
            sc2.close();
            Movie movie = addMovieMap(mid);
            Director director = addDirectorMap(did);
            movie.setDirector(did);
            director.addMovie(mid);
        }
        sc.close();
    }

    public static void step2() {
        // movieID,genre  1,Adventure
        // Read MOVIE_GENRES_FILE   
        Scanner sc = Tools.fileReader(MOVIE_GENRES_FILE);
        sc.nextLine();
        while (sc.hasNextLine()) {
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

    public static void step3() {
        // movieID,actorID,actorName,ranking  1,annie_potts,Annie Potts, 10
        // Read MOVIE_ACTORS_FILE
        Scanner sc = Tools.fileReader(MOVIE_ACTORS_FILE);
        sc.nextLine();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split("\t");
            int mid = Integer.parseInt(line[0]);
            String actorID = line[1];
            int rank = Integer.parseInt(line[line.length - 1]);

            Movie movie = addMovieMap(mid);
            movie.addActor(actorID, rank);

            Actor actor = addActorMap(actorID);
            actor.addMovie(mid);
        }
        sc.close();
    }

    public static void step4() {
        // Read MOVIE_TAGS_FILE
        // movieID,tagID,tagWeight   1,7,1
        Scanner sc = Tools.fileReader(MOVIE_TAGS_FILE);
        sc.nextLine();
        while (sc.hasNextLine()) {
            Scanner sc2 = new Scanner(sc.nextLine());
            int mid = sc2.nextInt();
            int tid = sc2.nextInt();
            int weight = sc2.nextInt();
            sc2.close();

            Movie movie = addMovieMap(mid);
            movie.addTag(tid, weight);
            Tag tag = addTagMap(tid);
            tag.addMovie(mid);
        }
        sc.close();
    }

    public static void step5() {
        //id,value   1,earth
        Scanner sc = Tools.fileReader(TAGS_FILE);
        sc.nextLine();
        while (sc.hasNextLine()) {
            Scanner sc2 = new Scanner(sc.nextLine());
            int id = sc2.nextInt();
            String value = sc2.nextLine().trim();
            sc2.close();

            tagList.put(id, value);
        }
        sc.close();
    }

    public static void step6() {
        // Read TRAINFILE
        // userID,movieID,rating 75,3,1
        Scanner sc = Tools.fileReader(TRAINFILE);
        sc.nextLine();
        int count = 0;
        ProgressBar pb = new ProgressBar(641699);
        while (sc.hasNextLine()) {
            Scanner sc2 = new Scanner(sc.nextLine());
            int userID = sc2.nextInt();
            int movieID = sc2.nextInt();
            double rating = sc2.nextDouble();

            sc2.close();
            Movie movie = addMovieMap(movieID);
            movie.addUserRating(userID, rating);
            
            User user = addUserMap(userID);
            user.addMovie(movieID, rating);
            count++;
            pb.update(count);
        }
        sc.close();
    }

    public static void mine()
    {
        Scanner sc = Tools.fileReader(TESTFILE);
        sc.nextLine();
        String 
    }

    public static Director addDirectorMap(String directorId) {
        Director d = null;
        if (directorMap.containsKey(directorId)) {
            d = directorMap.get(directorId);
        } else {
            d = new Director(directorId);
            directorMap.put(directorId, d);
        }
        return d;
    }

    public static Movie addMovieMap(int movieId) {
        Movie m = null;
        if (movieMap.containsKey(movieId)) {
            m = movieMap.get(movieId);
        } else {
            m = new Movie(movieId);
            movieMap.put(movieId, m);
        }
        return m;
    }

    public static Genre addGenreMap(String genreID) {
        Genre genre = null;
        if (genreMap.containsKey(genreID)) {
            genre = genreMap.get(genreID);
        } else {
            genre = new Genre(genreID);
            genreMap.put(genreID, genre);
        }
        return genre;
    }

    public static Actor addActorMap(String actorID) {
        Actor actor = null;
        if (actorMap.containsKey(actorID))
            actor = actorMap.get(actorID);
        else {
            actor = new Actor(actorID);
            actorMap.put(actorID, actor);
        }
        return actor;
    }

    public static Tag addTagMap(int tagID) {
        Tag tag = null;
        if (tagMap.containsKey(tagID))
            tag = tagMap.get(tagID);
        else {
            tag = new Tag(tagID);
            tagMap.put(tagID, tag);
        }
        return tag;
    }

    public static User addUserMap(int userID) {
        User user = null;
        if (userMap.containsKey(userID))
            user = userMap.get(userID);
        else {
            user = new User(userID);
            userMap.put(userID, user);
        }
        return user;
    }

    public static void printCollectionInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(TOP);
        sb.append("movieMap:    " + initialCap(movieMap.size()) + "\n");
        sb.append("directorMap: " + initialCap(directorMap.size()) + "\n");
        sb.append("actorMap:    " + initialCap(actorMap.size()) + "\n");
        sb.append("genreMap:    " + initialCap(genreMap.size()) + "\n");
        sb.append("tagMap:      " + initialCap(tagMap.size()) + "\n");
        sb.append("tagList:     " + initialCap(tagList.size()) + "\n");
        sb.append("userMap:     " + initialCap(userMap.size()) + "\n");
        sb.append(BOTTOM);
        System.out.println(sb.toString());
    }

    public static int initialCap(int size) {
        return size;
        // int initial = (int)((double)size/0.75)+1;
        // return initial;
        // int exp = (Math.log(initial)/Math.log(2)) + .5;
        // return Math.pow(2, exp);
    }

    public static void printMovieInfo() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter MovieID: ");
            int mid = sc.nextInt();
            System.out.println(movieMap.get(mid).toString());
        }
    }

    public static void menu() {
        StringBuilder sb = new StringBuilder();
        sb.append("1) Movie         2) Director         3) Actor\n");
        sb.append("4) Genre         5) Tag              6) User\n");
        sb.append("Enter Choice: ");
        String menu = sb.toString();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(menu);
            int choice = sc.nextInt();
            switch (choice) {
            case 1:
                System.out.print("Enter MovieID: ");
                int mid = sc.nextInt();
                System.out.println(movieMap.get(mid).toString());
                break;
            case 2:
                System.out.print("Enter DirectorID: ");
                String did = sc.next();
                System.out.println(directorMap.get(did).toString());
                break;
            case 3:
                System.out.print("Enter ActorID: ");
                String aid = sc.next();
                // System.out.println(aid);
                System.out.println(actorMap.get(aid).toString());
                break;
            case 4:
                System.out.print("Enter Genre: ");
                String gid = sc.next();
                System.out.println(genreMap.get(gid).toString());
                break;
            case 5:
                System.out.print("Enter TagID: ");
                int tid = sc.nextInt();
                System.out.println(tagMap.get(tid).toString());
                break;
            case 6:
                System.out.print("Enter UserID: ");
                int uid = sc.nextInt();
                System.out.println(userMap.get(uid).toString());
                break;
            default:
                printCollectionInfo();
                System.exit(0);
                break;
            }

        }
    }

}