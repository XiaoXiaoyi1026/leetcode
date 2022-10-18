package com.xiaoxiaoyi.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author xiaoxiaoyi
 * dijkstra
 */
public class Dijkstra {

    /**
     * 最短路径算法
     *
     * @param fromNode 出发节点
     * @return 出发节点到每个节点的最小值
     */
    public static Map<Node, Integer> dijkstra(Node fromNode) {
        // 1. 初始化distanceMap
        // key: to的节点
        // value: 最短距离
        // 如果distanceMap中不包含to节点，则意味着到to节点的距离为正无穷
        Map<Node, Integer> distanceMap = new HashMap<>(1);
        // 初始节点到自己的距离为0
        distanceMap.put(fromNode, 0);
        // 记录已经使用过的节点
        Set<Node> selectedNodes = new HashSet<>();
        // 选出最小距离的节点
        Node minNode = getMinDistanceAndUnSelectedNodes(distanceMap, selectedNodes);
        // 当最小节点不为空，即还有点未被选择过
        while (minNode != null) {
            // 取出最小距离
            Integer distance = distanceMap.get(minNode);
            // 遍历其所有发散边
            for (Edge edge : minNode.edges) {
                // 获取到当前边的to节点
                Node toNode = edge.to;
                // 判断距离表中是否包含to节点
                if (!distanceMap.containsKey(toNode)) {
                    // 如果不包含则直接更新to节点的最小距离为当前节点的距离 + 当前这条边的权重
                    distanceMap.put(toNode, distance + edge.weight);
                }
                // 距离表更新to节点的距离为自己的距离和当前节点的距离+这条边其中的最小值
                distanceMap.put(toNode, Math.min(distanceMap.get(toNode),
                        distance + edge.weight));
            }
            // 标记该节点已经选中过
            selectedNodes.add(minNode);
            // 更新最小距离节点
            minNode = getMinDistanceAndUnSelectedNodes(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    /**
     * 从距离表中选出距离最小的而且未被选择过的点
     *
     * @param distanceMap   距离表
     * @param selectedNodes 被选择过的点集
     * @return 符合条件的节点
     */
    public static Node getMinDistanceAndUnSelectedNodes(Map<Node, Integer> distanceMap, Set<Node> selectedNodes) {
        // 要返回的节点
        Node minNode = null;
        // 记录map中的最短距离
        Integer minDistance = Integer.MAX_VALUE;
        // 从map中取出节点
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            Integer distance = entry.getValue();
            // 如果节点未被访问过且距离小于当前的最小距离
            if (!selectedNodes.contains(node) && distance < minDistance) {
                // 更新最小节点为当前节点
                minNode = node;
                // 更新最小距离
                minDistance = distance;
            }
        }
        return minNode;
    }

}
