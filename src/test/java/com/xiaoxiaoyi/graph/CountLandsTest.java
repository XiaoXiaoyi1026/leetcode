package com.xiaoxiaoyi.graph;

import junit.framework.TestCase;

public class CountLandsTest extends TestCase {

    public void testCountLands() {
        int[][] input = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 1, 0, 1, 0, 0},
                {0, 1, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 1, 1, 1, 0, 1, 1, 0},
                {1, 1, 0, 0, 0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1, 1},
                {0, 1, 0, 0, 1, 1, 1, 1, 0},
        };
        System.out.println(CountLands.countLands(input));
    }

}
