package com.xiaoxiaoyi.matrix;

import javax.print.event.PrintEvent;

/**
 * @author xiaoxiaoyi
 * 拼三角形问题, 小红有n根木棍, 假设第1根木棍长度为1
 * 第2根长度为2...第i根长度为i...第n根木棍长度为n
 * 现在小红想要挑选其中任意3根木根拼成三角形, 而小明想要捣乱
 * 问: 小明从中至少拿出多少根木棍可以使得任意3根都拼不成三角形?
 * 业务题, 分析: 木棍长度从小到大排序, 那么使得i-2 + i-1 <= i即可
 * 又根据题目的至少, 可以使得i-2+i-1=i就可以达成目标(斐波拉契数列)
 */
public class SpellTriangle extends Fibonacci {

    public static int takeStick(int n) {
        int reserve = 2, fibonacci;
        while (true) {
            fibonacci = Fibonacci.fibonacci(reserve);
            if (fibonacci > n) {
                return n - reserve + 2;
            } else if (fibonacci == n) {
                return n - reserve + 1;
            }
            reserve++;
        }
    }

}
