package com.xiaoxiaoyi.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author xiaoxiaoyi
 * 你在一个城市里，城市由 n 个路口组成，路口编号为 0 到 n - 1 ，某些路口之间有 双向 道路。
 * 输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。
 * 给你一个整数 n 和二维整数数组 roads ，其中 roads[i] = [ui, vi, timei] 表示在路口 ui 和 vi 之间有一条需要花费 timei 时间才能通过的道路。
 * 你想知道花费 最少时间 从路口 0 出发到达路口 n - 1 的方案数。
 * 请返回花费 最少时间 到达目的地的 路径数目 。由于答案可能很大，将结果对 109 + 7 取余 后返回。
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/number-of-ways-to-arrive-at-destination">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code1976 {

    /**
     * 节点的个数
     */
    private static int n;
    /**
     * 描述图
     */
    private static int[][] graph;
    /**
     * 记录0到其余所有节点的最短路径
     */
    private static int[] distance;

    private final static int N = 201;
    private final static boolean[] VISITED = new boolean[N];

    public static int countPaths(int n, int[][] roads) {
        Code1976.n = n;
        graph = new int[N][N];
        distance = new int[n];
        // 记录每个节点的入度
        int[] in = new int[n];
        for (int[] road : roads) {
            int from = road[0], to = road[1], time = road[2];
            // 无向图
            graph[from][to] = graph[to][from] = time;
        }
        // 计算节点0到后续每个节点的最小距离
        dijkstra();
        // 根据最短路径重构图
        for (int[] road : roads) {
            int from = road[0], to = road[1], time = road[2];
            // 删除图中所有边
            graph[from][to] = graph[to][from] = 0;
            if (distance[from] + time == distance[to]) {
                // 如果当前边是构成0到to的最小距离路径的一部分，则构建这条边
                graph[from][to] = time;
                // to的入度+1
                in[to]++;

            } else if (distance[to] + time == distance[from]) {
                graph[to][from] = time;
                // from的入度+1
                in[from]++;
            }
        }
        // 使用拓扑排序统计方案数
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                deque.addLast(i);
            }
        }
        // f[i]代表0到i的最短路径条数
        int[] f = new int[n];
        // 0到0的最小路径有1条
        f[0] = 1;
        while (!deque.isEmpty()) {
            // 拿出一个入度为0的点
            int x = deque.pollFirst();
            // 遍历所有节点
            for (int i = 0; i < n; i++) {
                // 如果节点i到x存在边
                if (graph[x][i] != 0) {
                    // 0到i的最短路径条数 = 所有拥有最短路径的和
                    f[i] += f[x];
                    f[i] %= (int) 1e9 + 7;
                    if (--in[i] == 0) {
                        deque.addLast(i);
                    }
                }
            }
        }
        return f[n - 1];
    }

    private static void dijkstra() {
        // 初始情况每个节点的距离都为整型最大值，代表无法到达该节点
        Arrays.fill(distance, Integer.MAX_VALUE);
        // 0到自己的最短距离为0
        distance[0] = 0;
        for (int i = 0; i < n; i++) {
            // 当前节点指向-1
            int cur = -1;
            // 遍历每个节点
            for (int j = 0; j < n; j++) {
                if (!VISITED[j] && (cur == -1 || distance[j] < distance[cur])) {
                    cur = j;
                }
            }
            // 标记当前节点已被访问过
            VISITED[cur] = true;
            for (int j = 0; j < n; j++) {
                if (graph[cur][j] != 0) {
                    distance[j] = Math.min(distance[j], distance[cur] + graph[cur][j]);
                }
            }
        }
    }

}
