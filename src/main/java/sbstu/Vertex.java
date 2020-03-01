package sbstu;


public class Vertex {
    public String name;

    public Vertex(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj instanceof Vertex) {
            Vertex other = (Vertex)obj;
            return name.equals(other.name);
        }
        return false;
    }
}
