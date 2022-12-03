package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class CatchRainwaterTest extends TestCase {

    public void testCatchRainwater() {
        int[] heights = {10, 5, 2, 7, 3, 6};
        System.out.println(CatchRainwater.catchRainwater1(heights));
        System.out.println(CatchRainwater.catchRainwater2(heights));
    }

}
