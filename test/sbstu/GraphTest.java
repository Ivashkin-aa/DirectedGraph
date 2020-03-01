package sbstu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    Graph testGraph = new Graph();
    private Map<Vertex, List<Vertex>> neighbors = new HashMap<Vertex, List<Vertex>>();
    private Map<List<Vertex>, Integer> edge = new HashMap<List<Vertex>, Integer>();

    @BeforeEach
    void init() {
        testGraph = new Graph();

    }

    @Test
    void outgoing() {
        testGraph.addVertex("Москва");
        testGraph.addVertex("Рязань");
        testGraph.addVertex("Ростов");
        testGraph.addVertex("Крым");
        testGraph.addVertex("Санкт-Петербург");
        testGraph.addVertex("Казань");
        testGraph.addVertex("Лондон");

        testGraph.addEdge("Москва","Рязань", 9);
        testGraph.addEdge("Рязань","Ростов", 4);
        testGraph.addEdge("Ростов","Крым", 11);
        testGraph.addEdge("Москва","Крым", 28);
        testGraph.addEdge("Москва","Санкт-Петербург", 5);
        testGraph.addEdge("Санкт-Петербург","Казань", 6);
        testGraph.addEdge("Казань","Крым", 10);
        testGraph.addEdge("Москва","Лондон", 14);

        testGraph.renameVertex("Лондон", "The capital of great britain");

        testGraph.deleteEdge("Москва", "Крым");

        List<Vertex> exp = new ArrayList<>();
        exp.add(new Vertex("Рязань"));
        exp.add(new Vertex("Санкт-Петербург"));
        exp.add(new Vertex("The capital of great britain"));

        assertEquals(exp, testGraph.outgoing("Москва"));
    }

    @Test
    void incoming() {
        testGraph.addVertex("Артем");
        testGraph.addVertex("Камила");
        testGraph.addVertex("Женя");
        testGraph.addVertex("Варя");
        testGraph.addVertex("Макс");
        testGraph.addVertex("Даша");
        testGraph.addVertex("Олег");

        testGraph.addEdge("Варя", "Камила", 6);
        testGraph.addEdge("Камила", "Артем", 2);
        testGraph.addEdge("Варя", "Артем", 10);
        testGraph.addEdge("Варя", "Женя", 2);
        testGraph.addEdge("Женя", "Артем", 5);
        testGraph.addEdge("Даша", "Макс", 8);
        testGraph.addEdge("Макс", "Артем", 4);
        testGraph.addEdge("Олег", "Артем", 50);

        testGraph.changeWeight("Олег", "Артем", 25);

        testGraph.deleteVertex("Олег");

        List<Vertex> ex = new ArrayList<>();
        ex.add(new Vertex("Варя"));
        ex.add(new Vertex("Макс"));
        ex.add(new Vertex("Камила"));
        ex.add(new Vertex("Женя"));


        assertEquals(ex,testGraph.incoming("Артем"));
    }

}