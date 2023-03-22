package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomGenerate;
import junit.framework.TestCase;

import java.util.Arrays;

public class MaximumSubarraySumTest extends TestCase {

    public void testMaximumSubarraySum() {
        int[] array = {1, 2, -5, 6, 8, 23, -50, -3, 10, 7, 15, 5, -1};
        System.out.println(MaximumSubarraySum.maximumSubarraySum(array));
    }

    public void testAll() {
        System.out.println("测试开始");
        for (int i = 0; i < 10000; i++) {
            int length = (int) (5 + Math.random() * 10);
            int max = (int) (20 + Math.random() * 30);
            int[] array = RandomGenerate.array(length, max);
            int ans1 = MaximumSubarraySum.maximumSubarraySum(array);
            int ans2 = MaximumSubarraySum.maximumSubarraySum2(array);
            if (ans1 != ans2) {
                System.out.println(Arrays.toString(array));
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
