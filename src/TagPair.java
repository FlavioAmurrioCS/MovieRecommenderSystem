/**
 * ActorPair
 */
public class TagPair implements Comparable<TagPair> {

    int tagID;
    int weight;

    public TagPair(int tagID, int weight) {
        this.tagID = tagID;
        this.weight = weight;
    }

    public int compareTo(TagPair tp) {
        return tp.weight - this.weight;
    }

    public boolean equals(TagPair tp) {
        return this.tagID == tp.tagID;
    }

    public String toString() {
        return Miner.tagList.get(this.tagID);
    }
}