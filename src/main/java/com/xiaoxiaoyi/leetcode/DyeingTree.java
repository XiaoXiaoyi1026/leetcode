package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.ArrayUtils;
import com.xiaoxiaoyi.utils.RandomGenerate;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给一棵树染色
 */
public class DyeingTree {

    private DyeingTree() {

    }

    /**
     * 染色规则1
     */
    private static final int[] DYE_RULE1;

    /**
     * 染色规则2
     */
    private static final int[] DYE_RULE2;

    static {
        DYE_RULE1 = new int[]{1, 2, 3};
        DYE_RULE2 = new int[]{1, 3, 2};
    }

    /**
     * @param edges 无向边
     * @param n     节点个数
     * @return 邻接表
     */
    @NotNull
    @Contract(pure = true)
    public static List<List<Integer>> adjacencyList(@NotNull int[][] edges, int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    /**
     * @param edges 图中所有的无向边
     * @return 给每个节点染色, 要求除叶节点外的每一个节点包括自身在内和其所有的相邻节点的颜色都涵盖3种
     */
    @NotNull
    public static int[] dyeingTree(int[][] edges, int n) {
        if (edges == null || edges.length == 0) {
            return new int[0];
        }
        // 每个节点要染的颜色
        int[] colors = new int[n];
        // 生成邻接表
        List<List<Integer>> graph = adjacencyList(edges, n);
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() > 1) {
                root = i;
                break;
            }
        }
        if (root == -1) {
            Arrays.fill(colors, 1);
            return colors;
        }
        colors[root] = 1;
        List<Integer> nextNodes = graph.get(root);
        dyeProcess(nextNodes.get(0), colors, graph, DYE_RULE1, 1);
        for (int i = 1; i < nextNodes.size(); i++) {
            dyeProcess(nextNodes.get(i), colors, graph, DYE_RULE2, 1);
        }
        return colors;
    }

    /**
     * @param curNode 处理的节点
     * @param colors  染色后的节点颜色
     * @param graph   图
     * @param dyeRule 染色规则
     * @param level   处理的层数
     */
    private static void dyeProcess(@NotNull Integer curNode, @NotNull int[] colors, @NotNull List<List<Integer>> graph, @NotNull int[] dyeRule, int level) {
        colors[curNode] = dyeRule[level % 3];
        for (int nextNode : graph.get(curNode)) {
            if (colors[nextNode] == 0) {
                dyeProcess(nextNode, colors, graph, dyeRule, level + 1);
            }
        }
    }

    /**
     * @param edges  无向边
     * @param n      节点个数
     * @param colors 每个节点的颜色
     * @return 图中每个除叶节点外的节点及其相邻节点是否至少包含3种颜色
     */
    @Contract(pure = true)
    private static boolean checkAnswer(int[][] edges, int n, int[] colors) {
        if (edges == null || edges.length == 0) {
            return true;
        }
        // 生成邻接表
        List<List<Integer>> graph = adjacencyList(edges, n);
        boolean[] colorTypes = new boolean[4];
        int colorCount;
        for (int curNode = 0; curNode < n; curNode++) {
            if (colors[curNode] == 0) {
                return false;
            }
            colorTypes[colors[curNode]] = true;
            colorCount = 1;
            if (graph.get(curNode).size() > 1) {
                for (int nextNode : graph.get(curNode)) {
                    if (!colorTypes[colors[nextNode]]) {
                        colorTypes[colors[nextNode]] = true;
                        colorCount++;
                    }
                }
                if (colorCount < 3) {
                    return false;
                }
            }
            Arrays.fill(colorTypes, false);
        }
        return true;
    }

    /**
     * @param n 节点个数
     * @return 无环图的无向边集合
     */
    @NotNull
    private static int[][] randomEdges(int n) {
        int[] nodes = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = i;
        }
        ArrayUtils.shuffle(nodes);
        int[][] edges = new int[n - 1][2];
        for (int i = 1; i < n; i++) {
            edges[i - 1][0] = nodes[i];
            edges[i - 1][1] = nodes[RandomGenerate.nextInt(i)];
        }
        return edges;
    }

    public static void main(String[] args) {
        int testTimes = 1000;
        int maxLength = 20;
        int n;
        int[][] edges;
        int[] colors;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            n = RandomGenerate.nextInt(maxLength) + 1;
            edges = randomEdges(n);
            colors = dyeingTree(edges, n);
            if (!checkAnswer(edges, n, colors)) {
                System.out.println("检验失败!!!");
                System.out.println(Arrays.toString(colors));
                break;
            }
        }
        System.out.println("测试结束!!!");
    }

}
