package com.xiaoxiaoyi.random;

import junit.framework.TestCase;

import java.util.Arrays;

public class RandomRangeTest extends TestCase {

    public void testRandomRange() {
        int testTimes = 10000;
        int[] ans = new int[7];
        for (int i = 0; i < testTimes; i++) {
            ans[RandomRange.g() - 1]++;
        }
        System.out.println(Arrays.toString(ans));
    }

}
