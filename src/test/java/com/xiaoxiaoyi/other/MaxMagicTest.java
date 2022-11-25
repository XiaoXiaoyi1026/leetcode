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

    public void testMaxMagic() {
        int length = 10, max = 10;
        Set<Integer> set1 = generateRandomSet((int) (Math.random() * length) + 3, max);
        Set<Integer> set2 = generateRandomSet((int) (Math.random() * length) + 3, max);
        System.out.println(set1);
        System.out.println(set2);
        System.out.println(MaxMagic.maxMagic(set1, set2));
    }

}
