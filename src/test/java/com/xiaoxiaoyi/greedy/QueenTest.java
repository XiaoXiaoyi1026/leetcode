package com.xiaoxiaoyi.greedy;

import junit.framework.TestCase;

public class QueenTest extends TestCase {

    public void testQueen() {
        int n = 14;
        // 优化后的
        long constantTimeOptimizationQueensStartTime = System.currentTimeMillis();
        System.out.println(Queens.constantTimeOptimizationQueens(n));
        long constantTimeOptimizationQueensEndTime = System.currentTimeMillis();
        System.out.println("优化后:" + (constantTimeOptimizationQueensEndTime -
                constantTimeOptimizationQueensStartTime) + "ms");

        // 优化前
        long queensStartTime = System.currentTimeMillis();
        System.out.println(Queens.queens(n));
        long queensEndTime = System.currentTimeMillis();
        System.out.println("优化前:" + (queensEndTime - queensStartTime) + "ms");
    }

}
