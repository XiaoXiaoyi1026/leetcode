package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomGenerate;
import com.xiaoxiaoyi.matrix.Matrix;
import junit.framework.TestCase;

public class DriverAssignmentTest extends TestCase {

    public void testAll() {
        int testTimes = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int rows = (int) (Math.random() * 50) >> 1;
            int cols = 2;
            int max = (int) (Math.random() * 50);
            int[][] income = RandomGenerate.matrix(rows, cols, max);
            int ans1 = DriverAssignment.maxMoney1(income);
            int ans2 = DriverAssignment.maxMoney2(income);
            if (ans1 != ans2) {
                System.out.println("ooops!");
                Matrix.print(income);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
