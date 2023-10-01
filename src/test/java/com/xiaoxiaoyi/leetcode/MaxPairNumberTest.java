package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomUtils;
import junit.framework.TestCase;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @date 1/25/2023 10:15 AM
 */
public class MaxPairNumberTest extends TestCase {

    public void testMaxPairNumber1() {
        int length = (int) (Math.random() * 10);
        int max = (int) (Math.random() * 30) + 20;
        int[] arr = RandomUtils.array(length, max);
        System.out.println(Arrays.toString(arr));
        int k = (int) (Math.random() * 20);
        System.out.println(k);
        System.out.println(MaxPairNumber.maxPairNum1(arr, k));
    }

    public void testMaxPairNumber2() {
        int length = 5 + (int) (Math.random() * 10);
        int max = (int) (Math.random() * 30) + 20;
        int[] arr = RandomUtils.array(length, max);
        System.out.println(Arrays.toString(arr));
        int k = (int) (Math.random() * 20);
        System.out.println(k);
        System.out.println(MaxPairNumber.maxPairNum2(arr, k));
    }

    public void testAll() {
        int testTimes = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int length = 5 + (int) (Math.random() * 10);
            int max = (int) (Math.random() * 30) + 20;
            int[] arr1 = RandomUtils.array(length, max);
            int[] arr2 = new int[length];
            System.arraycopy(arr1, 0, arr2, 0, length);
            int k = (int) (Math.random() * 20);
            int ans1 = MaxPairNumber.maxPairNum1(arr1, k);
            int ans2 = MaxPairNumber.maxPairNum2(arr2, k);
            if (ans1 != ans2) {
                System.out.println(Arrays.toString(arr2));
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(k);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
