package com.xiaoxiaoyi.dynamic;

import junit.framework.TestCase;

public class WeightedShortestPathTest extends TestCase {

    public void testWeightedShortestPath() {
        int[][]  matrix = {
                {1, 2, 9, 6, 7},
                {3, 4, 10, 2, 2},
                {9, 7, 11, 7, 6},
                {2, 5, 8, 4, 5},
        };
        // 1->2->4->10->2->2->6->5
        System.out.println(WeightedShortestPath.weightedShortestPath(matrix));
    }

}
