package com.xiaoxiaoyi.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

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

    /**
     * 有向边
     *
     * @author xiaoxiaoyi
     */
    public static class Edge {
        /**
         * 权值
         */
        public Integer weight;
        /**
         * 起始点
         */
        public Node from;
        /**
         * 指向的点 from -> to
         */
        public Node to;

        @Override
        public String toString() {
            return "Edge{" +
                    "weight=" + weight +
                    ", from=" + from +
                    ", to=" + to +
                    '}';
        }

        /**
         * 构造边
         *
         * @param weight 权值
         * @param from   出发点
         * @param to     终点
         */
        public Edge(Integer weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }

        /**
         * equals
         *
         * @param o 目标对象
         * @return 是否一样
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Edge edge = (Edge) o;
            return weight.equals(edge.weight) && from.equals(edge.from) && to.equals(edge.to);
        }

        @Override
        public int hashCode() {
            return this.weight.hashCode() + this.from.hashCode() + this.to.hashCode();
        }
    }

    /**
     * 点的数据结构
     *
     * @author xiaoxiaoyi
     */
    public static class Node {
        /**
         * 点上的值
         */
        public Integer value;
        /**
         * 入度
         */
        public Integer in;
        /**
         * 出度
         */
        public Integer out;
        /**
         * 当前点的直接邻居(向外发散)
         */
        public ArrayList<Node> nextNodes;
        /**
         * 属于当前节点的边(出度)
         */
        public ArrayList<Edge> edges;

        /**
         * 构造结点
         *
         * @param value 结点值
         */
        public Node(Integer value) {
            this.value = value;
            in = 0;
            out = 0;
            nextNodes = new ArrayList<>();
            edges = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Node)) {
                return false;
            }
            Node node = (Node) o;
            return Objects.equals(value, node.value) && Objects.equals(in, node.in) && Objects.equals(out, node.out) && Objects.equals(nextNodes, node.nextNodes) && Objects.equals(edges, node.edges);
        }

        @Override
        public int hashCode() {
            return this.value.hashCode() + this.in.hashCode() + this.out.hashCode();
        }
    }
}
