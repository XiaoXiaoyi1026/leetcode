package com.xiaoxiaoyi.graph;

/**
 * @author xiaoxiaoyi
 * 将给定数据模型转换为我的数据模型
 */
public class GraphGenerator {

    /**
     * 根据n*3矩阵构造图
     *
     * @param matrix n*3数组，每一行代表[a, b, c] a: from b: to c: 权重
     * @return 图
     */
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (Integer[] row : matrix) {
            Integer from = row[0];
            Integer to = row[1];
            Integer weight = row[2];
            // 查询点是否已在图的点集中
            if (!graph.nodes.containsKey(from)) {
                // 如果点没在图的点集中出现过则new出来加进去
                graph.nodes.put(from, new Node(from));
            }
            // 同理
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            // 从点集拿出两个点
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            // 创建新边 fromNode ->  toNode
            Edge newEdge = new Edge(weight, fromNode, toNode);
            // 出发点的发散结点集添加toNode
            fromNode.nextNodes.add(toNode);
            // 出发点出度+1
            fromNode.out++;
            // 终点入度+1
            toNode.in++;
            // 出发点发散边集添加上newEdge
            fromNode.edges.add(newEdge);
            // 图的边集添加newEdge
            graph.edges.add(newEdge);
        }
        return graph;
    }

}
