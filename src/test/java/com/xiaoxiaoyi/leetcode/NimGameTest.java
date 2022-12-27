package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class NimGameTest extends TestCase {

    public void testNimGame() {
        int[] coins = {1, 1, 2, 1, 3, 3};
        System.out.println(NimGame.nimGame(coins));
    }

}
