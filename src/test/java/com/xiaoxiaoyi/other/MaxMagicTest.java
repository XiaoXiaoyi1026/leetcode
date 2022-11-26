package com.xiaoxiaoyi.other;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

public class MaxMagicTest extends TestCase {

    private Set<Integer> generateRandomSet(int length, int max) {
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < length; i++) {
            res.add((int) (Math.random() * (max + 1)));
        }
        return res;
    }

    private int[] setToArray(Set<Integer> set) {
        int[] res = new int[set.size()];
        int i = 0;
        for (Integer num : set) {
            res[i++] = num;
        }
        return res;
    }

    public void testMaxMagic() {
        int length = 10;
        Set<Integer> set1 = generateRandomSet((int) (Math.random() * length) + 3, 50);
        Set<Integer> set2 = generateRandomSet((int) (Math.random() * length) + 3, 10);
        System.out.println(MaxMagic.maxMagic(setToArray(set1), setToArray(set2)));
    }

}
