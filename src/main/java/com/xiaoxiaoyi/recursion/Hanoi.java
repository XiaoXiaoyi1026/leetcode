package com.xiaoxiaoyi.recursion;

/**
 * @author xiaoxiaoyi
 * 汉诺塔问题
 * 三根柱子，n个盘子，开始n个盘子按照小盘上，大盘下摞在from这根柱子上，要求打印最优的挪动步骤
 * 使得form上的n个盘子到to上，且中间不能出现大盘子在小盘子之上的情况
 */
public class Hanoi {

    public static void hanoi(int n) {
        if (n > 0) {
            process(n, "左", "右", "中");
        }
    }

    /**
     * 递归遍历子问题
     *
     * @param n     要挪动的盘子数量
     * @param from  起始柱子
     * @param to    目标柱子
     * @param other 辅助柱子
     */
    private static void process(int n, String from, String to, String other) {
        if (n == 1) {
            // 只有一个盘子时可直接进行移动
            System.out.println("盘子1: " + from + " -> " + to);
        } else {
            // 1. 将n-1个盘子从form借助to移到other上
            process(n - 1, from, other, to);
            // 2. 打印将第n个盘子从from移到to
            System.out.println("盘子" + n + ": " + from + " -> " + to);
            // 3. 将n-1个盘子从other借助form移到to
            process(n - 1, other, to, from);
        }
    }

}
