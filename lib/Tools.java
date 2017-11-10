import java.io.FileInputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Random;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

/**
 * WorkEnv
 */
public class Tools {

    public static boolean SHOW_LOG = false;

    // public static ArrayList<Pair> syncList = new ArrayList<>();
    public static CopyOnWriteArrayList<Pair> syncList = new CopyOnWriteArrayList<Pair>();

    public static synchronized void syncInsert(int index, double score){
        syncList.add(new Pair(index, score));
    }

    public static void tittleMaker(String str) {
        System.out.println("-----------------------------" + str + "-----------------------------");
    }

    public static PrintWriter fileWriter(String filename) {
        try {
            File file = new File(filename);
            PrintWriter pw = new PrintWriter(file);
            if (SHOW_LOG) {
                System.out.println("Writing " + filename + " ...");
            }
            return pw;
        } catch (Exception e) {
            System.out.println("ERROR: Can't access file " + filename);
            System.exit(1);
        }
        return null;
    }

    public static void slow(int milli){
        try {
            Thread.sleep(milli);
        } catch (Exception e) {
        }
    }

    public static void slowRandom(int milliMax){
        slow((new Random()).nextInt(milliMax));
    }

    public static Scanner fileReader(String filename) {
        try {
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            Scanner sc = new Scanner(fis);
            if (SHOW_LOG) {
                System.out.println("Reading " + "...");
            }
            return sc;

        } catch (Exception e) {
            System.out.println("Error: Can't read " + filename);
            System.exit(1);
        }
        return null;
    }

    public static <T> void listToFile(List<T> outList, String filename) {
        PrintWriter pw = fileWriter(filename);
        for (T obj : outList) {
            pw.println(obj.toString());
        }
        pw.close();
    }

    public static <T> ArrayList<T> fileToObjList(String filename, Class<T> cls) {
        ArrayList<T> retList = new ArrayList<>();
        Constructor<T> constructor = null;
        Scanner sc = fileReader(filename);
        try {
            constructor = cls.getConstructor(String.class);
            retList.add(constructor.newInstance(sc.nextLine()));

        } catch (Exception e) {
            System.out.println("Error: Casting Error");
            System.exit(1);
        }
        return retList;
    }

    public static ArrayList<String> scannerToStrList(Scanner sc){
        ArrayList<String> strList = new ArrayList<>();
        while(sc.hasNextLine())
            strList.add(sc.nextLine());
        sc.close();
        return strList;
    }
}

class Pair implements Comparable<Pair> {
    int index;
    double score;

    public Pair(int index, double score) {
        this.index = index;
        this.score = score;
    }

    public int compareTo(Pair p) {
        return this.index - p.index;
    }

    public String toString() {
        return String.format("%.1f", this.score);
    }
}