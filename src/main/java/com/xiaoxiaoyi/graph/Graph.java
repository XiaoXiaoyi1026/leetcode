package com.xiaoxiaoyi.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 * @author xiaoxiaoyi
 */
public class Graph {
    /**
     * 点集
     * key: 点的编号
     * value: 实际的点
     */
    public HashMap<Integer, Node> nodes;
    /**
     * 边集
     */
    public HashSet<Edge> edges;

    /**
     * 初始化图
     */
    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
