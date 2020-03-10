package sbstu;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private Map<Vertex, Set<Vertex>> neighbors = new HashMap<Vertex, Set<Vertex>>();
    private Map<Pair<Vertex, Vertex>, Integer> edge = new HashMap<Pair<Vertex, Vertex>, Integer>();

    public boolean hasVertex(String vertexName) {
        Vertex ver = new Vertex(vertexName);
        return neighbors.get(ver) == null;
    }

    public boolean addVertex(String vertexName) {
        if (hasVertex(vertexName)) {
            neighbors.put(new Vertex(vertexName), new HashSet<>());
            System.out.println("Вершина " + vertexName + " успешно создана");
            return true;
        } else {
            System.out.println("Имя вершины " + vertexName + " занято");
            return false;
        }
    }

    public boolean deleteVertex(String vertexName) {
        Vertex ver = new Vertex(vertexName);
        if (!hasVertex(vertexName)) {
            neighbors.values().stream().map(e -> e.remove(ver)).collect(Collectors.toSet());
            neighbors.remove(ver);
            System.out.println("Вершина " + vertexName + " успешно удалена");
            return true;
        } else {
            System.out.println("Вершина с именем " + vertexName + " не найдена");
            return false;
        }
    }

    public boolean addEdge(String start, String end, int weight) {
        Vertex ver1 = new Vertex(start);
        Vertex ver2 = new Vertex(end);
        if (neighbors.get(ver1) != null && neighbors.get(ver2) != null) {
            neighbors.get(ver1).add(ver2);
            Pair ed = new Pair(ver1, ver2);
            edge.put(ed, weight);
            System.out.println("Дуга " + start + " - " + end + " успешно создана");
            return true;
        } else {
            System.out.println("Имена вершин не найдены");
            return false;
        }
    }

    public boolean deleteEdge(String start, String end) {
        Vertex ver1 = new Vertex(start);
        Vertex ver2 = new Vertex(end);
        Pair verEd;
        Set<Vertex> ed = neighbors.get(ver1);
        if (ed != null) {
            ed.remove(ver2);
            verEd = new Pair(ver1, ver2);
            edge.remove(verEd);
            System.out.println("Дуга " + start + " - " + end + " успешно удалена");
            return true;
        } else {
            System.out.println("Дуга " + start + " - " + end + " не найдена");
            return false;
        }
    }

    public boolean renameVertex(String vertexName1, String vertexName2) {
        Vertex ver1 = new Vertex(vertexName1);
        Vertex ver2 = new Vertex(vertexName2);
        if (hasVertex(vertexName1)) {
            System.out.println("Вершина с именем "+ vertexName1 + " не найдена");
            return false;
        }
        else {
            Set<Vertex> v = neighbors.get(ver1);
            neighbors.remove(ver1);
            neighbors.put(ver2, v);
            for (Set<Vertex> val : neighbors.values()) {
                if (val.contains(ver1)) {
                    val.remove(ver1);
                    val.add(ver2);
                }
            }
            for (Pair key : edge.keySet()) {
                if (key.getStart() == ver1) {
                    key.setStart(ver2);
                }
                if (key.getEnd() == ver1) {
                    key.setEnd(ver2);
                }
            }
            System.out.println("Новое имя вершины \"" + vertexName1 + "\" - \"" + vertexName2 + "\"");
            return true;
        }
    }

    public boolean changeWeight(String start, String end, int newWeight) {
        Vertex ver1 = new Vertex(start);
        Vertex ver2 = new Vertex(end);
        Pair verEd = new Pair(ver1, ver2);
        if (edge.get(verEd) != null) {
            edge.put(verEd, newWeight);
            System.out.println("Новый вес дуги \"" + start + "-" + end + "\" = " + newWeight);
            return true;
        } else {
            System.out.println("Дуга не найдена");
            return false;
        }
    }

    public Set<Vertex> outgoing(String vertexName) {
        if (!hasVertex(vertexName)) {
            Set<Vertex> vertices = neighbors.get(new Vertex(vertexName));
            return vertices;
        } else throw new IllegalArgumentException();
    }

    public Set<Vertex> incoming(String vertexName) {
        Vertex ver = new Vertex(vertexName);
        Set<Vertex> in = new HashSet<>();
        if (!hasVertex(vertexName)) {
            for (Vertex key : neighbors.keySet())
                if (neighbors.get(key).contains(ver))
                    in.add(key);
            return in;
        } else throw new IllegalArgumentException();
    }

}
