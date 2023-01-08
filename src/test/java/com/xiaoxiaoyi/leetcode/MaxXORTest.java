package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class MaxXORTest extends TestCase {

    public void testGet() {
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generate((int) (Math.random() * 20), (int) (Math.random() * 100));
            int ans1 = MaxXOR.get1(arr);
            int ans2 = MaxXOR.get2(arr);
            int ans3 = MaxXOR.trieTreeGet(arr);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("ooops!");
                System.out.println(Arrays.toString(arr));
                System.out.println("get1 = " + ans1);
                System.out.println("get2 = " + ans2);
                System.out.println("trieTreeGet = " + ans3);
                break;
            }
        }
    }

    private int[] generate(int length, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

}
