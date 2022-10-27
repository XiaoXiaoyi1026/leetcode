package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class Code1976Test extends TestCase {

    public void testCode1976() {
        int[][] roads = {
                {0,6,7},
                {0,1,2},
                {1,2,3},
                {1,3,3},
                {6,3,3},
                {3,5,1},
                {6,5,1},
                {2,5,1},
                {0,4,5},
                {4,6,2}};
        System.out.println(Code1976.countPaths(7, roads));
    }

}
