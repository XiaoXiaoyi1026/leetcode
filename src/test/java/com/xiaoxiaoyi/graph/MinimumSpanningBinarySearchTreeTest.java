package com.xiaoxiaoyi.graph;

import junit.framework.TestCase;

public class MinimumSpanningBinarySearchTreeTest extends TestCase {

    public void testKruskalMinimumSpanningTreeByUnionFind() {
        Integer[][] input = new Integer[][]{
                {1, 2, 6},
                {1, 3, 1},
                {1, 4, 5},
                {2, 3, 5},
                {2, 5, 3},
                {3, 4, 5},
                {3, 5, 6},
                {3, 6, 4},
                {4, 6, 2},
                {5, 6, 6}
        };
        Graph graph = GraphGenerator.createGraph(input);
        for (Graph.Edge edge : MinimumSpanningTree.kruskalMinimumSpanningTreeByUnionFind(graph)) {
            System.out.println(edge.weight);
        }
    }

    /**
     * test for kruskalMinimumSpanningTree
     */
    public void testKruskalMinimumSpanningTree() {
        Integer[][] input = new Integer[][]{
                {1, 2, 6},
                {1, 3, 1},
                {1, 4, 5},
                {2, 3, 5},
                {2, 5, 3},
                {3, 4, 5},
                {3, 5, 6},
                {3, 6, 4},
                {4, 6, 2},
                {5, 6, 6}
        };
        Graph graph = GraphGenerator.createGraph(input);
        for (Graph.Edge edge : MinimumSpanningTree.kruskalMinimumSpanningTree(graph)) {
            System.out.println(edge.weight);
        }
    }

    /**
     * test for primMinimumSpanningTree
     */
    public void testPrimMinimumSpanningTree() {
        Integer[][] input = new Integer[][]{
                {1, 2, 6},
                {2, 1, 6},
                {1, 3, 1},
                {3, 1, 1},
                {1, 4, 5},
                {4, 1, 5},
                {2, 3, 5},
                {3, 2, 5},
                {2, 5, 3},
                {5, 2, 3},
                {3, 4, 5},
                {4, 3, 5},
                {3, 5, 6},
                {5, 3, 6},
                {3, 6, 4},
                {6, 3, 4},
                {4, 6, 2},
                {6, 4, 2},
                {5, 6, 6},
                {6, 5, 6}
        };
        Graph graph = GraphGenerator.createGraph(input);
        for (Graph.Edge edge : MinimumSpanningTree.primMinimumSpanningTree(graph)) {
            System.out.println(edge.weight);
        }
    }

}
