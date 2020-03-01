package sbstu;

import java.util.*;
import java.util.stream.Collectors;


public class Graph {
    private Map<Vertex, List<Vertex>> neighbors = new HashMap<Vertex, List<Vertex>>();
    private Map<List<Vertex>, Integer> edge = new HashMap<List<Vertex>, Integer>();

    private boolean hasVertex(String vertexName) {
        Vertex ver = new Vertex(vertexName);
        for (List<Vertex> v : neighbors.values())
            if (v.contains(ver) || neighbors.get(ver) != null)
                return false;
        return true;
    }

    public void addVertex(String vertexName) {
        if (hasVertex(vertexName))
            neighbors.put(new Vertex(vertexName), new ArrayList<>());
        else throw new IllegalArgumentException();
    }

    public void deleteVertex(String vertexName) {
        Vertex ver = new Vertex(vertexName);
        neighbors.values().stream().map(e -> e.remove(ver)).collect(Collectors.toList());
        neighbors.remove(ver);
    }

    public void addEdge(String start, String end, int weight) {
        Vertex ver1 = new Vertex(start);
        Vertex ver2 = new Vertex(end);
        List<Vertex> verEd;
        if (neighbors.get(ver1) != null && neighbors.get(ver2) != null) {
            neighbors.get(ver1).add(ver2);
            verEd = new ArrayList<Vertex>();
            verEd.add(ver1);
            verEd.add(ver2);
            edge.put(verEd, weight);
        } else throw new IllegalArgumentException();
    }

    public void deleteEdge(String start, String end) {
        Vertex ver1 = new Vertex(start);
        Vertex ver2 = new Vertex(end);
        List<Vertex> verEd;
        List<Vertex> ed = neighbors.get(ver1);
        if (ed != null) {
            ed.remove(ver2);
            verEd = new ArrayList<Vertex>();
            verEd.add(ver1);
            verEd.add(ver2);
            edge.remove(verEd);
        } else throw new IllegalArgumentException();
    }

    public void renameVertex(String vertexName1, String vertexName2) {
        Vertex ver1 = new Vertex(vertexName1);
        Vertex ver2 = new Vertex(vertexName2);
        //if (!hasVertex(vertexName1)) {
        List<Vertex> v = neighbors.get(ver1);
        neighbors.remove(ver1);
        neighbors.put(ver2, v);
        for (List<Vertex> val : neighbors.values()) {
            if (val.contains(ver1)) {
                int re = val.indexOf(ver1);
                val.set(re, ver2);
            }
        }
        for (List<Vertex> key : edge.keySet()) {
            if (key.contains(ver1)) {
                int re = key.indexOf(ver1);
                key.set(re, ver2);
            }
        }
        //  }
        // throw new IllegalArgumentException();
    }

    public void changeWeight(String start, String end, int newWeight) {
        Vertex ver1 = new Vertex(start);
        Vertex ver2 = new Vertex(end);
        List<Vertex> verEd = new ArrayList<>();
        verEd.add(ver1);
        verEd.add(ver2);
        if (edge.get(verEd) != null) {
            edge.put(verEd, newWeight);
            System.out.println("Новый вес дуги: " + start + "-" + end + "=" + newWeight);
        } else throw new IllegalArgumentException();
    }

    public List<Vertex> outgoing(String vertexName) {
        if (!hasVertex(vertexName))
            return neighbors.get(new Vertex(vertexName));
        else throw new IllegalArgumentException();
    }

    public List<Vertex> incoming(String vertexName) {
        Vertex ver = new Vertex(vertexName);
        List<Vertex> in = new ArrayList<Vertex>();
        for (Vertex key : neighbors.keySet())
            if (neighbors.get(key).contains(ver))
                in.add(key);
        return in;
    }


}
