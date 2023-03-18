package com.xiaoxiaoyi.graph;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

/**
 * singleton & double check lock
 * @author xiaoxiaoyi
 */

public class EdgeComparator implements Comparator<Graph.Edge> {

    private static volatile EdgeComparator edgeComparator;

    private EdgeComparator() {}

    @Override
    public int compare(@NotNull Graph.Edge edge1, @NotNull Graph.Edge edge2) {
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
