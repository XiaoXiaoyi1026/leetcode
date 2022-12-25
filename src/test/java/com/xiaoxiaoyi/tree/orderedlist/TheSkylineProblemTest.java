package com.xiaoxiaoyi.tree.orderedlist;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

public class TheSkylineProblemTest extends TestCase {

    public void testGetContourLine() {
        int[][] input = {
                {1, 4, 5},
                {2, 6, 7},
                {5, 5, 2},
                {7, 10, 3},
                {9, 12, 4}
        };
        List<int[]> res = TheSkylineProblem.getContourLine(input);
        for (int[] re : res) {
            System.out.println(Arrays.toString(re));
        }
    }

}
