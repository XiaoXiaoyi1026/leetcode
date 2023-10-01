package com.xiaoxiaoyi.leetcode;

/**
 * 图中的最长环
 * 给定 n 个节点的 有向图, 节点编号范围 [0, n - 1]
 * 其中每个节点的出度至多为 1, 即最多有一条出去的边
 * 图中的边用一个大小为 n, 下标从 0 开始的数组 edges 表示
 * 节点 i 到 节点 edges[i] 有一条边
 * 如果节点 i 没有出边, 那么 edges[i] = -1
 * 算法要求返回图中最长环上的长度(环上的节点个数), 如果不存在环, 返回 -1
 * 一个环是指起始节点和终点是同一个节点的路径
 */
public class LongestCycleInGraph {

    /**
     * @param edges 图的有向边
     * @return 图中最长环
     */
    public static int longestCycle(int[] edges) {
        if (edges == null || edges.length == 0) {
            return -1;
        }
        int[] visited = new int[edges.length];
        int count = 0;
        int currentNode;
        int ans = -1;
        for (int startNode = 0; startNode < edges.length; startNode++) {
            if (visited[startNode] == 0) {
                // 记录起始节点
                currentNode = startNode;
                // 如果当前节点有效且节点未被访问过
                while (currentNode != -1 && visited[currentNode] == 0) {
                    // 给当前节点标号
                    visited[currentNode] = ++count;
                    // 当前节点继续指向下一个节点
                    currentNode = edges[currentNode];
                }
                // 如果当前节点不是无效节点且退出的节点的访问值大于起始节点的访问值, 那么说明这是一个新找到的环
                if (currentNode != -1 && visited[currentNode] >= visited[startNode]) {
                    // 只统计新找到的环的结果
                    ans = Math.max(ans, count - visited[currentNode] + 1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] edges = {1, 2, 3, 0, 4};
        System.out.println(longestCycle(edges));
        edges = new int[]{-1, 2, 3, 0, 4};
        System.out.println(longestCycle(edges));
        edges = new int[]{-1, 2, 3, 0, -1};
        System.out.println(longestCycle(edges));
    }

}
