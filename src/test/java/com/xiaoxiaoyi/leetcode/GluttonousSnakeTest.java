package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class GluttonousSnakeTest extends TestCase {

    public void testGluttonousSnake() {
        int[][] map = {
                {100, 20, -70, -20},
                {300, 40, -100, 100},
                {-400, 70, -20, 80},
                {-350, -50, 90, -10},
                {200, 80, 80, -200},
        };
        System.out.println(GluttonousSnake.maximumLength(map));
        System.out.println(GluttonousSnake.maximumLength2(map));
    }

}
