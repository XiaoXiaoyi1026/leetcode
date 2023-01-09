package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class HanoiStepTest extends TestCase {

    public void testGet() {
        int[] arr = {3, 3, 2, 1};
        System.out.println(HanoiStep.get(arr));
        System.out.println(HanoiStep.get2(arr));
    }

}
