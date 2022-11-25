package com.xiaoxiaoyi.other;

import junit.framework.TestCase;

import java.util.Arrays;

public class NonRepeatingFixedDifferenceNumberPairTest extends TestCase {

    private int[] generateRandomNums(int length, int max) {
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = (int) (Math.random() * (max + 1));
        }
        return res;
    }

    public void testNonRepeatingFixedDifferenceNumberPair() {
        int testTimes = 100, length, max, k;
        for (int i = 0; i < testTimes; i++) {
            length = (int) (Math.random() * 10) + 5;
            max = (int) (Math.random() * 10) + 10;
            k = (int) (Math.random() * 10) + 2;
            System.out.println(Arrays.deepToString(NonRepeatingFixedDifferenceNumberPair
                    .getNonRepeatingFixedDifferenceNumberPair(
                            generateRandomNums(length, max), k
                    )));
        }
    }

}
