package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class CrossTheRiverByBoatTest extends TestCase {

    public void testCrossTheRiverByBoat() {
        for (int i = 0; i < 10000; i++) {
            int limit = 1 + (int) (Math.random() * 20);
            int[] weights = generateWeights(limit, limit);
            int ans1 = CrossTheRiverByBoat.needBoats(weights, limit);
            int ans2 = CrossTheRiverByBoat.needBoats2(weights, limit);
            if (ans1 != ans2) {
                System.out.println(Arrays.toString(weights));
                System.out.println(limit);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
    }

    public int[] generateWeights(int length, int limit) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (1 + Math.random() * limit);
        }
        return arr;
    }

}
