package com.xiaoxiaoyi.graph;

import junit.framework.TestCase;

import java.util.Map;

public class GraphTest extends TestCase {

    /**
     * test for graphBfs
     */
    public void testGraphBfs() {
        Integer[][] input = new Integer[][]{
                {1, 2, 1},
                {1, 3, 1},
                {2, 3, 1},
                {3, 4, 1},
                {3, 5, 1},
                {2, 4, 1},
                {4, 6, 1}
        };
        Graph graph = GraphGenerator.createGraph(input);
        GraphBfs.bfs(graph.nodes.get(1));
    }

    /**
     * test for graphDfs
     */
    public void testGraphDfs() {
        Integer[][] input = new Integer[][]{
                {1, 2, 1},
                {1, 3, 1},
                {2, 3, 1},
                {3, 4, 1},
                {3, 5, 1},
                {2, 4, 1},
                {4, 6, 1}
        };
        Graph graph = GraphGenerator.createGraph(input);
        GraphDfs.dfs(graph.nodes.get(1));
    }

    /**
     * test for topologySort
     */
    public void testTopologySort() {
        Integer[][] input = new Integer[][]{
                {1, 2, 1},
                {1, 5, 1},
                {2, 3, 1},
                {3, 5, 1},
                {3, 6, 1},
                {4, 5, 1},
                {6, 5, 1}
        };
        Graph graph = GraphGenerator.createGraph(input);
        for (Graph.Node node : TopologySort.topologySort(graph)) {
            System.out.println(node.value);
        }
    }

    /**
     * test for dijkstra
     */
    public void testDijkstra() {
        Integer[][] input = new Integer[][]{
                {1, 2, 1},
                {1, 5, 2},
                {2, 3, 3},
                {2, 5, 8},
                {3, 5, 4},
                {3, 1, 6},
                {5, 4, 2},
                {6, 5, 7},
                {4, 6, 5},
                {3, 6, 5}
        };
        Graph graph = GraphGenerator.createGraph(input);
        Map<Graph.Node, Integer> distanceMap = Dijkstra.dijkstra(graph.nodes.get(1));
        for (Map.Entry<Graph.Node, Integer> entry : distanceMap.entrySet()) {
            System.out.println("1 -> " + entry.getKey().value + " : " + entry.getValue());
        }
    }

    public void testImprovedDijkstra() {
        Integer[][] input = new Integer[][]{
                {1, 2, 1},
                {1, 5, 2},
                {2, 3, 3},
                {2, 5, 8},
                {3, 5, 4},
                {3, 1, 6},
                {5, 4, 2},
                {6, 5, 7},
                {4, 6, 5},
                {3, 6, 5}
        };
        Graph graph = GraphGenerator.createGraph(input);
        Map<Graph.Node, Integer> distanceMap = Dijkstra.improvedDijkstra(graph.nodes.get(1), graph.nodes.size());
        for (Map.Entry<Graph.Node, Integer> entry : distanceMap.entrySet()) {
            System.out.println("1 -> " + entry.getKey().value + " : " + entry.getValue());
        }
    }
}