package com.xiaoxiaoyi.other;

import junit.framework.TestCase;

import java.util.Arrays;

public class AdjustArrayTest extends TestCase {
    public int[] generateRandomArray(double oddNumberProbability, double divisibleByFourProbability, int max, int maxSize) {
        int[] arr = new int[(int) (Math.random() * maxSize)];
        for (int i = 0; i < arr.length; i++) {
            double random = Math.random();
            if (random < oddNumberProbability) {
                arr[i] = (int) (Math.random() * max * 2 + 1);
            } else if (random >= oddNumberProbability && random < oddNumberProbability + divisibleByFourProbability) {
                arr[i] = (int) (Math.random() * max * 4);
            } else {
                int tmp;
                do {
                    tmp = (int) (Math.random() * max * 2);
                } while (tmp % 4 == 0);
                arr[i] = tmp;
            }
        }
        return arr;
    }

    public void testAdjustArray() {
        for (int i = 0; i < 10; i++) {
            double oddNumberProbability = Math.random();
            double divisibleByFourProbability = Math.random() * (1 - oddNumberProbability);
            int[] arr = generateRandomArray(oddNumberProbability, divisibleByFourProbability, 20, 10);
            System.out.println(Arrays.toString(arr));
            System.out.println(AdjustArray.can(arr));
        }
    }

}
