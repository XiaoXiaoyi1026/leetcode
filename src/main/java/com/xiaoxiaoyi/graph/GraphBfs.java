package com.xiaoxiaoyi.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xiaoxiaoyi
 * 图的广度优先遍历
 */
public class GraphBfs {
    /**
     * 广度优先
     *
     * @param node 遍历的起始节点
     */
    public static void bfs(Graph.Node node) {
        // 特殊情况
        if (node == null) {
            return;
        }
        // 1. 使用队列完成
        Queue<Graph.Node> queue = new LinkedList<>();
        // 存储已访问过的节点
        HashSet<Graph.Node> set = new HashSet<>();
        // 初始化
        queue.add(node);
        set.add(node);
        // 当队列不为空时，遍历
        while (!queue.isEmpty()) {
            // 当前节点等于队列出来的第一个
            Graph.Node curNode = queue.poll();
            // 打印当前节点的值
            System.out.println(curNode.value);
            // 将当前节点的后继节点都加入queue
            for (Graph.Node nextNode : curNode.nextNodes) {
                // 判断是否访问过
                if (!set.contains(nextNode)) {
                    // 没访问过，添加进queue
                    queue.add(nextNode);
                    // 标记已访问
                    set.add(nextNode);
                }
            }
        }
    }
}
