package com.xiaoxiaoyi.graph;

import java.util.Comparator;

/**
 * singleton & double check lock
 * @author xiaoxiaoyi
 */

public class EdgeComparator implements Comparator<Edge> {

    private static volatile EdgeComparator edgeComparator;

    private EdgeComparator() {}

    @Override
    public int compare(Edge edge1, Edge edge2) {
        return edge1.weight - edge2.weight;
    }

    /**
     * get comparator
     * @return comparator
     */
    public static EdgeComparator getMyComparator() {
        if (edgeComparator == null) {
            synchronized (EdgeComparator.class) {
                if (edgeComparator == null) {
                    edgeComparator = new EdgeComparator();
                }
            }
        }
        return edgeComparator;
    }
}
