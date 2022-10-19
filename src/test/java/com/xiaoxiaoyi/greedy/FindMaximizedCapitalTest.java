package com.xiaoxiaoyi.greedy;

import junit.framework.TestCase;

public class FindMaximizedCapitalTest extends TestCase {

    public void testFindMaximizedCapital() {
        int k = 3;
        int w = 1;
        // 利润
        int[] profits = new int[]{4, 1, 3, 2};
        // 成本
        int[] capital = new int[]{2, 1, 3, 2};
        System.out.println(FindMaximizedCapital.findMaximizedCapital(k, w, profits, capital));
    }

}
