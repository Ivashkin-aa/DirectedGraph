package sbstu;

import java.util.*;

public class Graph {
    private Set<Vertex> vertices = new HashSet<>();
    private Map<Pair<Vertex, Vertex>, Integer> edge = new HashMap<Pair<Vertex, Vertex>, Integer>();

    private boolean hasVertex(String vertexName) {
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
            Set<Pair<Vertex, Vertex>> newEdge = new HashSet<>(edge.keySet());
            for (Pair<Vertex, Vertex> pr : newEdge) {
                if (ver.equals(pr.getStart()))
                    edge.remove(pr);
                if (ver.equals(pr.getEnd()))
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
        if (!hasVertex(vertexName1))
            return false;
        else {
            vertices.remove(ver1);
            vertices.add(ver2);
            Map<Pair<Vertex, Vertex>, Integer> ne = new HashMap<>(edge);
            for (Pair<Vertex, Vertex> key : ne.keySet()) {
                if (ver1.equals(key.getStart())) {
                    int nw = ne.get(key);
                    Pair<Vertex, Vertex> np = new Pair<>(ver2, key.getEnd());
                    edge.remove(key);
                    edge.put(np, nw);
                }
                if (ver1.equals(key.getEnd())) {
                    int nw = ne.get(key);
                    Pair<Vertex, Vertex> np = new Pair<>(key.getStart(), ver2);
                    edge.remove(key);
                    edge.put(np, nw);
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
