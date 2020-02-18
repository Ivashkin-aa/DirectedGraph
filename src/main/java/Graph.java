import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    private List<String> vertex = new ArrayList<String>();
    private HashMap<HashMap<String, String>, Integer> edge = new HashMap<HashMap<String, String>, Integer>();
    private HashMap<String, List<String>> neighbors = new HashMap<String, List<String>>();

    public boolean hasVertex(String vertexName) {
        for (String v : vertex)
            if (vertexName.equals(v))
                return true;
    }

    public void addVertex(String vertexName) {
        if (!hasVertex(vertexName))
            vertex.add(vertexName);
        else throw new IllegalArgumentException();
    }

    public boolean hasEdge(String start, String end) {
        if (edge.containsKey(HashMap < start, end >))
            return true;
    }

    public void addEdge(String start, String end, int weight) {
        if (hasVertex(start) && hasVertex(end) && !hasEdge(start, end)) {
            edge.put(HashMap < start, end >, weight);
            if(neighbors.containsKey(start))
                neighbors.get(start).add(end);
            else neighbors.put(start, new ArrayList<String>().add(end));
        }
        else throw new IllegalArgumentException();
    }

    public void deleteVertex(String vertexName) {
        if (hasVertex(vertexName))
            vertex.remove(vertexName);
        else throw new IllegalArgumentException();
    }

    public void deleteEdge(String start, String end) {
        if(hasEdge(start, end)) {
            edge.remove(HashMap < start, end >);
            String[] now = new neighbors.get(start).remove(end);
            neighbors.put(start, now);
        }
        else throw new IllegalArgumentException();
    }

    public void renameVertex(String vertexName1, String vertexName2) {
        if(hasVertex(vertexName1)) {
            int dex = vertex.indexOf(vertexName1);
            vertex.set(dex,vertexName2);
        }
    }

    public void changeWeight(String start, String end,int newWeight) {
        if(hasEdge(start, end))
            edge.put(HashMap<start, end>, newWeight);
        else throw new IllegalArgumentException();
    }

    public List<String> outneighbors(String vertexName) {
        if(neighbors.containsKey(vertexName))
            return neighbors.get(vertexName);
        else throw new IllegalArgumentException();
    }

    public List<String> inneighbors(String name){
        List<String> in = new ArrayList<>();
        for(int i=0; i<neighbors.size(); i++) {
            String key = neighbors.get(i);
            if(neighbors.get(key).contains(name))
                in.add(name);
        }
        return in;
    }


}
