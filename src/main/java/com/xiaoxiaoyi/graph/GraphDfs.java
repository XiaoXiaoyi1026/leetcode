package com.xiaoxiaoyi.graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author xiaoxiaoyi
 * 图的深度优先遍历
 */
public class GraphDfs {

    /**
     * 深度优先遍历
     * @param node 起始节点
     */
    public static void dfs(Graph.Node node) {
        // 特殊处理
        if (node == null) {
            return;
        }
        // 1. 使用栈实现深度优先遍历
        Stack<Graph.Node> stack = new Stack<>();
        // 记录节点有没有访问过
        HashSet<Graph.Node> set = new HashSet<>();
        // 初始化
        stack.push(node);
        set.add(node);
        System.out.println(node.value);
        // 2. 当栈不为空时，遍历
        while (!stack.isEmpty()) {
            // 弹出栈顶结点
            Graph.Node curNode = stack.pop();
            // 当前节点的后继节点入栈
            for (Graph.Node nextNode : curNode.nextNodes) {
                // 判断是否访问过
                if (!set.contains(nextNode)) {
                    // 如果后继结点有没访问过的则和后继结点一起入栈
                    stack.push(curNode);
                    stack.push(nextNode);
                    // 标记为访问过
                    set.add(nextNode);
                    // 输出后继结点的值(处理)
                    System.out.println(nextNode.value);
                    // 跳出循环
                    break;
                }
            }
        }
    }
}
