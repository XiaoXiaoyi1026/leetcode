package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class BurstBalloonsTest extends TestCase {

    public void testTopScore() {
        for (int i = 0; i < 20; i++) {
            int[] arr = generate((int) (Math.random() * 20), (int) (Math.random() * 50));
            int ans1 = BurstBalloons.topScore(arr);
            int ans2 = BurstBalloons.dp(arr);
            if (ans1 != ans2) {
                System.out.println("ooops!");
                System.out.println(Arrays.toString(arr));
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
    }

    public int[] generate(int length, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

}
