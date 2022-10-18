package com.xiaoxiaoyi.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author xiaoxiaoyi
 * 拓扑排序算法
 */
public class TopologySort {

    /**
     * 算法实现
     * @return 排序后的节点顺序
     */
    public static List<Node> topologySort(Graph graph) {

        List<Node> result = new ArrayList<>();

        // 1. 遍历图寻找入度为0的节点
        Queue<Node> zeroInNodes = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            if (node.in == 0) {
                zeroInNodes.add(node);
            }
        }
        // 2. 当入度节点队列不为空时，循环弹出节点
        while (!zeroInNodes.isEmpty()) {
            Node curNode = zeroInNodes.poll();
            // 3. 将当前节点加入到返回列表
            result.add(curNode);
            // 4. 擦除当前结点的影响，即将其所有next中的节点in - 1
            for (Node node : curNode.nextNodes) {
                // 如果减后入度为0，则加入到入度0的队列中
                if (--node.in == 0) {
                    zeroInNodes.add(node);
                }
            }
        }

        return result;
    }

}
