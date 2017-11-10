import java.util.ArrayList;
import java.util.Collections;

/**
 * PairTracker
 */
public class PairTracker<K> {

    ArrayList<Pair<K>> pList = new ArrayList<>();

    public PairTracker() {
    }

    public synchronized void add(int index, K item) {
        pList.add(new Pair<K>(index, item));
    }

    public ArrayList<Pair> getOrderList() {
        Collections.sort(pList);
        return new ArrayList<>(pList);
    }
}

private class Pair<K> implements Comparable<Pair<K>> {
    int index;
    K item;

    public Pair(int index, K item) {
        this.index = index;
        this.item = item;
    }

    public int compareTo(Pair p) {
        return this.index - p.index;
    }

    public String toString() {
        return "" + this.index + ": " + item.toString();
    }

}