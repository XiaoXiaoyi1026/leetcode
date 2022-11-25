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

    public void testRandP() {
        int testTimes = 10000;
        int[] ans = new int[2];
        for (int i = 0; i < testTimes; i++) {
            ans[RandomRange.rand01p()]++;
        }
        System.out.println(Arrays.toString(ans));
    }

    public void testRand() {
        int testTimes = 10000;
        int[] ans = new int[2];
        for (int i = 0; i < testTimes; i++) {
            ans[RandomRange.rand01()]++;
        }
        System.out.println(Arrays.toString(ans));
    }

}
