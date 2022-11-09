package com.xiaoxiaoyi.dynamic;

import junit.framework.TestCase;

import java.util.Arrays;

public class BestWayToHoldPokerTest extends TestCase {

    public void testBestWayToHoldPoker() {
        int len = 10, max = 10, testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[] pokers = BestWayToHoldPoker.getRandomPokers(len, max);
            int ans1 = BestWayToHoldPoker.bestWayToHoldPoker1(pokers);
            int ans2 = BestWayToHoldPoker.bestWayToHoldPoker2(pokers);
            int ans3 = BestWayToHoldPoker.bestWayToHoldPoker3(pokers);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("ooops!");
                System.out.println(Arrays.toString(pokers));
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
    }

}
