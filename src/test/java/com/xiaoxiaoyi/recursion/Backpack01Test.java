package com.xiaoxiaoyi.recursion;

import junit.framework.TestCase;

public class Backpack01Test extends TestCase {

    public void testBackpack01() {
        int capacity = 10;
        int[] values = new int[]{2, 5, 7, 2, 3};
        int[] weights = new int[]{3, 6, 8, 1, 5};
        System.out.println(Backpack01.backpack01(capacity, values, weights));
    }

}
