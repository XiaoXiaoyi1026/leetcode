package com.xiaoxiaoyi.graph;

import java.util.*;

/**
 * @author xiaoxiaoyi
 * 最小生成树
 */
public class MinimumSpanningTree {

    /**
     * 自定义set
     */
    public static class MySet {

        /**
         * 存放结点和它对应的set
         */
        public HashMap<Node, Set<Node>> nodeSetHashMap = new HashMap<>();

        /**
         * 初始化函数，将图的每一个节点都单独放入一个set
         *
         * @param nodeHashMap 点集
         */
        public MySet(HashMap<Integer, Node> nodeHashMap) {
            for (Node node : nodeHashMap.values()) {
                Set<Node> nodes = new HashSet<>();
                nodes.add(node);
                nodeSetHashMap.put(node, nodes);
            }
        }

        /**
         * 判断两个节点是否在同一个set中
         *
         * @param node1 节点1
         * @param node2 节点2
         * @return node1与node2是否在同一个set
         */
        public boolean isTwoNodesInTheSameSet(Node node1, Node node2) {
            return nodeSetHashMap.get(node1) == nodeSetHashMap.get(node2);
        }

        /**
         * 将两个节点的set进行合并
         *
         * @param node1 节点1
         * @param node2 节点2
         */
        public void unionTwoSet(Node node1, Node node2) {
            Set<Node> node1Set = nodeSetHashMap.get(node1);
            Set<Node> node2Set = nodeSetHashMap.get(node2);
            for (Node node : node2Set) {
                node1Set.add(node);
                // 更新node2Set中的每个节点所属的set
                nodeSetHashMap.put(node, node1Set);
            }
        }

    }

    /**
     * 使用并查集优化后的kruskal算法
     *
     * @param graph 图
     * @return 边集
     */
    public static Set<Edge> kruskalMinimumSpanningTreeByUnionFind(Graph graph) {

        Set<Edge> result = new HashSet<>();

        List<Node> nodes = new ArrayList<>();

        for (Map.Entry<Integer, Node> entry : graph.nodes.entrySet()) {
            nodes.add(entry.getValue());
        }

        // 初始化并查集，存储图中每个节点
        UnionFind<Node> unionFind = new UnionFind<>(nodes);
        // 获取边的比较器
        EdgeComparator edgeComparator = EdgeComparator.getMyComparator();
        // 构造小根堆
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>(edgeComparator);
        // 将所有边放入小根堆排序
        edgePriorityQueue.addAll(graph.edges);
        // 遍历小根堆
        while (!edgePriorityQueue.isEmpty()) {
            // 选出最小的那条边
            Edge edge = edgePriorityQueue.poll();
            // 判断这条边的from和to节点是否在同一个set中
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                // 如果不在，则将该边加入到result中
                result.add(edge);
                // 合并from和to的set
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }

    /**
     * kruskal 最小生成树
     *
     * @param graph graph
     * @return edges
     */
    public static Set<Edge> kruskalMinimumSpanningTree(Graph graph) {

        // 记录返回的边集
        Set<Edge> result = new HashSet<>();

        // 初始化，将图中所有节点都指向自己的set
        MySet mySet = new MySet(graph.nodes);
        // 获取边的比较器
        EdgeComparator edgeComparator = EdgeComparator.getMyComparator();
        // 构造小根堆
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>(edgeComparator);
        // 将所有边放入小根堆排序
        edgePriorityQueue.addAll(graph.edges);
        // 遍历小根堆
        while (!edgePriorityQueue.isEmpty()) {
            // 选出最小的那条边
            Edge edge = edgePriorityQueue.poll();
            // 判断这条边的from和to节点是否在同一个set中
            if (!mySet.isTwoNodesInTheSameSet(edge.from, edge.to)) {
                // 如果不在，则将该边加入到result中
                result.add(edge);
                // 合并from和to的set
                mySet.unionTwoSet(edge.from, edge.to);
            }
        }
        return result;
    }

    /**
     * prim of minimum spanning tree
     *
     * @param graph 图
     * @return 边集
     */
    public static Set<Edge> primMinimumSpanningTree(Graph graph) {

        Set<Edge> result = new HashSet<>();

        // 创建已访问点集和已解锁的边集
        Stack<Node> goesNodes = new Stack<>();
        PriorityQueue<Edge> unlockEdges = new PriorityQueue<>(EdgeComparator.getMyComparator());
        // 可以针对森林(图非连通)问题，保证能取到所有的点
        for (Node node : graph.nodes.values()) {
            // 节点未被访问时
            if (!goesNodes.contains(node)) {
                // 节点加入到已访问点集中
                goesNodes.add(node);
                // 解锁与该点相关的所有的边
                unlockEdges.addAll(node.edges);
                // 解锁的边集不为空时
                while (!unlockEdges.isEmpty()) {
                    // 弹出其中的最小边
                    Edge poll = unlockEdges.poll();
                    // 当最小边的to未被访问过时才进行添加(跳过重复的边)
                    if (!goesNodes.contains(poll.to)) {
                        // 添加进返回结果中
                        result.add(poll);
                        // 将to节点加进goes中
                        goesNodes.add(poll.to);
                        // 将to节点的所有边解锁(可能会有重复边加入，但是会被跳过)
                        unlockEdges.addAll(poll.to.edges);
                    }
                }
            }
        }
        return result;
    }

}
