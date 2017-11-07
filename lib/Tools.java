import java.io.FileInputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

/**
 * WorkEnv
 */
public class Tools {

    public static boolean SHOW_LOG = false;

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

    public static <T> void listToFile(ArrayList<T> outList, String filename) {
        PrintWriter pw = fileWriter(filename);
        for (T obj : outList) {
            pw.println(obj.toString());
        }
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
}