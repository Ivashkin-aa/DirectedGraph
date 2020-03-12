package sbstu;

import java.util.*;

public class Graph {
    private Set<Vertex> vertices = new HashSet<>();
    private Map<Pair<Vertex, Vertex>, Integer> edge = new HashMap<Pair<Vertex, Vertex>, Integer>();

    public boolean hasVertex(String vertexName) {
        Vertex ver = new Vertex(vertexName);
        return vertices.contains(ver);
    }

    public boolean addVertex(String vertexName) {
        if (!hasVertex(vertexName)) {
            vertices.add(new Vertex(vertexName));
            return true;
        } else return false;
    }

    public boolean deleteVertex(String vertexName) {
        Vertex ver = new Vertex(vertexName);
        if (hasVertex(vertexName)) {
            vertices.remove(ver);
            //edge.keySet().stream().map(e->e.);
            for (Pair<Vertex, Vertex> pr : edge.keySet()) {
                Vertex start = pr.getStart();
                Vertex end = pr.getEnd();
                if (start == ver)
                    edge.remove(pr);
                if (end == ver);
                    edge.remove(pr);
            }
            return true;
        } else return false;
    }

    public boolean addEdge(String start, String end, int weight) {
        Vertex ver1 = new Vertex(start);
        Vertex ver2 = new Vertex(end);
        if (hasVertex(start) && hasVertex(end)) {
            Pair<Vertex, Vertex> ed = new Pair<>(ver1, ver2);
            edge.put(ed, weight);
            return true;
        } else return false;
    }

    public boolean deleteEdge(String start, String end) {
        Vertex ver1 = new Vertex(start);
        Vertex ver2 = new Vertex(end);
        Pair<Vertex, Vertex> verEd = new Pair<>(ver1, ver2);
        if (edge.get(verEd) != null) {
            edge.remove(verEd);
            return true;
        } else return false;
    }

    public boolean renameVertex(String vertexName1, String vertexName2) {
        Vertex ver1 = new Vertex(vertexName1);
        Vertex ver2 = new Vertex(vertexName2);
        if (!hasVertex(vertexName1)) {
            return false;
        } else {
            vertices.remove(ver1);
            vertices.add(ver2);
            for (Pair<Vertex, Vertex> key : edge.keySet()) {
                if (key.getStart() == ver1) {
                    key.setStart(ver2);
                }
                if (key.getEnd() == ver1) {
                    key.setEnd(ver2);
                }
            }
            return true;
        }
    }

    public boolean changeWeight(String start, String end, int newWeight) {
        Vertex ver1 = new Vertex(start);
        Vertex ver2 = new Vertex(end);
        Pair<Vertex, Vertex> verEd = new Pair<>(ver1, ver2);
        if (edge.get(verEd) != null) {
            edge.put(verEd, newWeight);
            return true;
        } else return false;
    }

    public Set<Vertex> outgoing(String vertexName) {
        Vertex vr = new Vertex(vertexName);
        if (hasVertex(vertexName)) {
            Set<Vertex> out = new HashSet<>();
            for (Pair<Vertex, Vertex> key : edge.keySet()) {
                if (key.getStart().equals(vr))
                    out.add(key.getEnd());
            }
            return out;
        } else throw new IllegalArgumentException();
    }

    public Set<Vertex> incoming(String vertexName) {
        Vertex ver = new Vertex(vertexName);
        Set<Vertex> in = new HashSet<>();
        if (hasVertex(vertexName)) {
            for (Pair<Vertex, Vertex> key : edge.keySet()) {
                if (key.getEnd().equals(ver))
                    in.add(key.getStart());
            }
            return in;
        } else throw new IllegalArgumentException();
    }
}
