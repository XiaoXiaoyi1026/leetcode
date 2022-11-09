package com.xiaoxiaoyi.dynamic;

import junit.framework.TestCase;

public class TakeCoinsTest extends TestCase {

    public void testTakeCoins() {
        int len = 10, max = 10, testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[] coins = TakeCoins.getRandomCoins(len, max);
            int money = (int) (Math.random() * 3 * max) + max;
            // 记录3种不同方法的结果
            int takeCoins1 = TakeCoins.takeCoins1(coins, money);
            int takeCoins2 = TakeCoins.takeCoins2(coins, money);
            int takeCoins3 = TakeCoins.takeCoins3(coins, money);
            if (takeCoins1 != takeCoins2 || takeCoins2 != takeCoins3) {
                // 如果3种方法中有1个不一样则返回错误
                System.out.println("ooops!");
                // 打印错误状况
                System.out.println(takeCoins1);
                System.out.println(takeCoins2);
                System.out.println(takeCoins3);
                break;
            }
        }
    }

}
