package com.xiaoxiaoyi.dynamic;

import junit.framework.TestCase;

public class RobotWalkTest extends TestCase {
    // 一共5个格子, 终点 4 起点 2, 走 4 步
    private static final int n = 5, p = 4, m = 2, k = 6;

    public void testRobotWalk1() {
        long startTime = System.currentTimeMillis();
        System.out.println(RobotWalk.walkWays1(n, p, m, k));
        long endTime = System.currentTimeMillis();
        System.out.println("暴力递归尝试: " + (endTime - startTime));
    }

    public void testRobotWalk2() {
        long startTime = System.currentTimeMillis();
        System.out.println(RobotWalk.walkWays2(n, p, m, k));
        long endTime = System.currentTimeMillis();
        System.out.println("记忆化搜索: " + (endTime - startTime));
    }

    public void testDpWalk() {
        long startTime = System.currentTimeMillis();
        System.out.println(RobotWalk.dpWalk(n, p, m, k));
        long endTime = System.currentTimeMillis();
        System.out.println("动态规划严格表结构求解: " + (endTime - startTime));
    }

}