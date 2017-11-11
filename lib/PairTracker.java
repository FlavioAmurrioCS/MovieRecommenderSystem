import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PairTracker
 */
public class PairTracker<K> {

    List<Pair<K>> pList;

    public PairTracker() {
        this.pList = Collections.synchronizedList(new ArrayList<Pair<K>>());
    }

    public synchronized void add(int index, K item) {
        Pair<K> p = new Pair<>(index, item);
        pList.add(p);
    }

    public List<Pair<K>> getOrderList() {
        Collections.sort(pList);
        return pList;
    }

    public void toFile(String filename) {
        Tools.listToFile(getOrderList(), filename);
    }
}

class Pair<V> implements Comparable<Pair<V>> {
    int index;
    V item;

    public Pair(int index, V item) {
        this.index = index;
        this.item = item;
    }

    public int compareTo(Pair<V> p) {
        return this.index - p.index;
    }

    public String toString() {
        return "" + this.index + ": " + item.toString();
    }

}