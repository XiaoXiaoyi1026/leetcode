package com.xiaoxiaoyi.dynamic;

import junit.framework.TestCase;

public class HorseJumpTest extends TestCase {

    public void testHorseJump() {
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            int x = (int) (Math.random() * 9);
            int y = (int) (Math.random() * 10);
            int step = (int) (Math.random() * 10);
            int ans1 = HorseJump.jumpWays1(x, y, step);
            int ans2 = HorseJump.jumpWays2(x, y, step);
            if (ans1 != ans2) {
                System.out.println("ooops!");
                break;
            }
        }
    }

}
