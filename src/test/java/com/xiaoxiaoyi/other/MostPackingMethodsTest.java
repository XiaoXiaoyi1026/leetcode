package com.xiaoxiaoyi.other;

import junit.framework.TestCase;

public class MostPackingMethodsTest extends TestCase {

    public void testMostPackingMethods() {
        int[] arr = {1, 2, 3, 4, 5};
        int n = 10;
        System.out.println(MostPackingMethods.mostPackingMethods(arr, n));
    }

}
