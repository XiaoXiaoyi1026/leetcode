package com.xiaoxiaoyi.greedy;

import junit.framework.TestCase;

public class PackingMachineTest extends TestCase {

    public void testMinimumNumberOfAdjustments() {
        int[] nums = {100, 0, 0, 0, 20};
        int ans = PackingMachine.minimumNumberOfAdjustments(nums);
        System.out.println(ans);
    }

}
