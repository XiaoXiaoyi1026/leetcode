package com.xiaoxiaoyi.graph;

/**
 * 有向边
 *
 * @author xiaoxiaoyi
 */
public class Edge {
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
