package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * n个人玩约瑟夫环, 数到m的人出局, 打印出局顺序
 */
public class PlayGame {

    public static void getOutOrder(int n, int m) {
        // i代表的是轮数
        for (int i = 0; i < n; i++) {
            // 当前轮出局的编号, n - i为当前轮剩余的人数
            int number = (m - 1) % (n - i) + 1;
            // oldNumber为number在上一轮对应的编号
            int oldNumber = number;
            // 第i轮出局的编号向上还原i轮可以得到最开始对应的编号
            for (int j = 1; j <= i; j++) {
                // 向上还原一轮: 老编号 = (新编号 + m - 1) % 老n + 1
                oldNumber = (number + m - 1) % (n - i + j) + 1;
                // 下一轮的number为这一轮的oldNumber
                number = oldNumber;
            }
            System.out.print(oldNumber + (i == n - 1 ? "" : " "));
        }
    }

    public static void main(String[] args) {
        // 7个人玩约瑟夫环游戏, 规定从1开始报数, 报到3的人出局, 打印每一轮出局的人的编号
        getOutOrder(7, 3);
    }

}
