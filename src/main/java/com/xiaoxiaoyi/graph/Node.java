package com.xiaoxiaoyi.graph;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 点的数据结构
 *
 * @author xiaoxiaoyi
 */
public class Node {
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
