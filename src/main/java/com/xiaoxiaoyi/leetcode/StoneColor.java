package com.xiaoxiaoyi.leetcode;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 石头染色, 给定n * 3矩阵, 代表n块石头的信息, 每块石头的信息包含: 颜色, 变为红色需要花费的代价, 变为蓝色需要花费的代价
 * 0代表无色, 1代表红色, 2代表蓝色, 只有无色的石头可以染色
 * 要将所有的石头都染上颜色且使得红色石头的数量和蓝色石头一样多, 怎样染色最省
 * @date 3/18/2023 5:12 PM
 */
public class StoneColor {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 3, 7},
                {0, 6, 2},
                {0, 2, 9},
                {0, 6, 4},
        };
        System.out.println(color(matrix));
    }

    @Contract(pure = true)
    public static int color(@NotNull int[][] matrix) {
        int n = matrix.length;
        if ((n & 1) != 0) {
            // 如果石头数量为奇数, 则直接放弃染色
            return -1;
        }
        // 分别记录两种石头的数量
        int redNum = 0;
        int blueNum = 0;
        for (int[] msg : matrix) {
            int color = msg[0];
            if (color == 1) {
                redNum++;
            }
            if (color == 2) {
                blueNum++;
            }
        }
        // 任意一种石头的数量超过一般则直接放弃
        if (redNum > n >> 1 || blueNum > n >> 1) {
            return -1;
        }
        // 记录花费
        int cost = 0;
        // 记录红转蓝减少的代价
        int[] dec = new int[n - redNum - blueNum];
        int i = 0;
        for (int[] msg : matrix) {
            if (msg[0] == 0) {
                // 假设所有无色石头都染上红色
                cost += msg[1];
                // 顺便记录选择染上蓝色的话代价的减少值
                dec[i++] = msg[1] - msg[2];
            }
        }
        // 对代价进行升序排序
        Arrays.sort(dec);
        // 蓝色石头需要的数量 = n/2-现有蓝色石头的数量
        blueNum = n >> 1 - blueNum;
        for (int j = 0; j < blueNum; j++) {
            // 选择代价减少最多的变成蓝色石头
            cost -= dec[dec.length - 1 - j];
        }
        return cost;
    }

}
