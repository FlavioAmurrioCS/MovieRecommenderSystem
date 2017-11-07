/**
 * ActorPair
 */
public class ActorPair implements Comparable<ActorPair> {

    String actorID;
    int rank;

    public ActorPair(String actorID, int rank) {
        this.actorID = actorID;
        this.rank = rank;
    }

    public int compareTo(ActorPair ap) {
        return this.rank - ap.rank;
    }

    public boolean equals(ActorPair ap)
    {
        return this.actorID.equals(ap.actorID);
    }

    public String toString(){
        return this.actorID;
    }
}