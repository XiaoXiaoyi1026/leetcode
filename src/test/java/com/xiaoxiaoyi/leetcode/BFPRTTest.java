package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.exception.MyException;
import junit.framework.TestCase;

import java.util.Arrays;

public class BFPRTTest extends TestCase {

    public int[] generateRandomArray(int length, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * max);
        }
        return arr;
    }

    public int getKthSmallest(int[] arr, int k) {
        int n = arr.length;
        int[] tmp = new int[n];
        System.arraycopy(arr, 0, tmp, 0, n);
        Arrays.sort(tmp);
        return tmp[k - 1];
    }

    public void testKthSmallest() throws MyException {
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            int length = 1 + (int) (Math.random() * 20);
            int max = (int) (Math.random() * 20);
            int[] arr = generateRandomArray(length, max);
            int k = 1 + (int) (Math.random() * length);
            int ans1 = BFPRT.getKthSmallest(arr, k);
            int ans2 = getKthSmallest(arr, k);
            if (ans1 != ans2) {
                System.out.println("ooops!");
                System.out.println("k = " + k);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println(Arrays.toString(arr));
                break;
            }
        }
    }

    public void testAdjust() throws MyException {
        int length = 1 + (int) (Math.random() * 20);
        int max = (int) (Math.random() * 20);
        int[] arr = generateRandomArray(length, max);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(BFPRT.adjust(arr, 0, arr.length - 1, arr[0])));
        System.out.println(Arrays.toString(arr));
    }
}
