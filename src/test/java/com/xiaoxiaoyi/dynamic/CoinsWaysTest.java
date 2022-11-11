package com.xiaoxiaoyi.dynamic;

import junit.framework.TestCase;

public class CoinsWaysTest extends TestCase {

    public void testCoinsWays() {
        int len = 10, max = 10, testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] coins = CoinsWays.randomCoins(len, max);
            int target = (int) (Math.random() * 3 * max) + max;
            int ans1 = CoinsWays.takeCoins1(coins, target);
            int ans2 = CoinsWays.takeCoins2(coins, target);
            int ans3 = CoinsWays.takeCoins3(coins, target);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("ooops!");
                break;
            }
        }
    }

}
