package com.xiaoxiaoyi.recursion;

import junit.framework.TestCase;

public class BestWayToHoldPokerTest extends TestCase {

    public void testBestWayToHoldPoker() {
        int[] pokers = new int[]{1, 2, 2, 4};
        System.out.println(BestWayToHoldPoker.bestWayToHoldPoker(pokers));
    }

}
