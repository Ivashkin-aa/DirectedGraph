package sbstu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private Graph testGraph = new Graph();

    @BeforeEach
    void init() {
        testGraph = new Graph();

    }

    @Test
    void addVertex() {
        assertTrue(testGraph.addVertex("A"));
        assertTrue(testGraph.addVertex("B"));
        assertFalse(testGraph.addVertex("A"));
    }

    @Test
    void deleteVertex() {
        testGraph.addVertex("ABC");
        assertFalse(testGraph.deleteVertex("BC"));
        assertTrue(testGraph.deleteVertex("ABC"));
    }

    @Test
    void addEdge() {
        testGraph.addVertex("C");
        testGraph.addVertex("D");
        assertTrue(testGraph.addEdge("C", "D", 25));
        assertFalse(testGraph.addEdge("C", "E", 12));
    }

    @Test
    void deleteEdge() {
        testGraph.addVertex("C");
        testGraph.addVertex("D");
        testGraph.addEdge("C", "D", 25);
        assertTrue(testGraph.deleteEdge("C", "D"));
        assertFalse(testGraph.deleteEdge("E","D"));
    }

    @Test
    void renameVertex() {
        testGraph.addVertex("T");
        testGraph.addVertex("E");
        testGraph.addEdge("T", "E", 30);
        assertTrue(testGraph.renameVertex("T", "C"));
        assertFalse(testGraph.renameVertex("T", "E"));
        assertTrue(testGraph.renameVertex("E", "B"));
        assertFalse(testGraph.renameVertex("G", "X"));
    }

    @Test
    void changeWeight() {
        testGraph.addVertex("Q");
        testGraph.addVertex("W");
        testGraph.addEdge("Q", "W", 22);
        assertFalse(testGraph.changeWeight("W", "Q", 10));
        assertTrue(testGraph.changeWeight("Q", "W", 15));
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

        testGraph.addEdge("Москва", "Рязань", 9);
        testGraph.addEdge("Рязань", "Ростов", 4);
        testGraph.addEdge("Ростов", "Крым", 11);
        testGraph.addEdge("Москва", "Крым", 28);
        testGraph.addEdge("Москва", "Санкт-Петербург", 5);
        testGraph.addEdge("Санкт-Петербург", "Казань", 6);
        testGraph.addEdge("Казань", "Крым", 10);
        testGraph.addEdge("Москва", "Лондон", 14);

        testGraph.renameVertex("Лондон", "The capital of great britain");

        testGraph.deleteEdge("Москва", "Крым");

        Set<Vertex> exp = new HashSet<>();
        exp.add(new Vertex("Санкт-Петербург"));
        exp.add(new Vertex("The capital of great britain"));
        exp.add(new Vertex("Рязань"));

        assertEquals(exp, testGraph.outgoing("Москва"));

        try {
            assertEquals(exp, testGraph.incoming("New York"));
        }
        catch (IllegalArgumentException e) {
            System.out.println("Вершина с таким именем не найдена");
        }
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

        Set<Vertex> ex = new HashSet<>();
        ex.add(new Vertex("Камила"));
        ex.add(new Vertex("Женя"));
        ex.add(new Vertex("Варя"));
        ex.add(new Vertex("Макс"));


        assertEquals(ex, testGraph.incoming("Артем"));

        try {
            assertEquals(ex, testGraph.incoming("Боря"));
        }
        catch (IllegalArgumentException e) {
            System.out.println("Вершина с таким именем не найдена");
        }
    }

}