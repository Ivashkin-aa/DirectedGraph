package sbstu;

public class Pair<S, E> {
    private final S start;
    private final E end;

    public Pair(S start, E end) {
        this.start = start;
        this.end = end;
    }

    public void setStart(S newStart) {
        newStart = start;
    }

    public void setEnd(E newEnd) {
        newEnd = end;
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
        Pair newpair = (Pair) obj;
        return this.start.equals(newpair.getStart()) && this.end.equals(newpair.getEnd());
    }

}
