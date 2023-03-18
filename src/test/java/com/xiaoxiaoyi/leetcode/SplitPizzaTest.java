package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 切披萨测试
 * @date 3/10/2023 10:32 AM
 */
public class SplitPizzaTest extends TestCase {

    private static final String[] pizza = {
            ".A",
            "AA",
            "A.",
    };

    private static final int k = 1;

    public void testSplit() {
        System.out.println(SplitPizza.ways(pizza, k));
    }

}
