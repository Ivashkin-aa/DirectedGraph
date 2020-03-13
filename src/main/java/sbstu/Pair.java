package sbstu;

public class Pair<S, E> {
    private final S start;
    private final E end;

    public Pair(S start, E end) {
        this.start = start;
        this.end = end;
    }

    public S getStart() {
        return start;
    }

    public E getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return start.toString() + end.toString();
    }

    @Override
    public int hashCode() {
        return start.hashCode() + end.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pair))
            return false;
        Pair newPair = (Pair) obj;
        return this.start.equals(newPair.getStart()) && this.end.equals(newPair.getEnd());
    }

}
