package com.xiaoxiaoyi.leetcode;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * 给定一个原始数组 arr，返回数组最大分数的最小左旋步数k。
 * 如果arr[i] <= i, 则得一分
 * 示例：
 * 输入：arr = [2,3,1,4,0]
 * 当k == 0时，arr = [2,3,1,4,0], 得分 2
 * 当k == 1, arr = [3,1,4,0,2],  得分 3
 * 当k == 2, arr = [1,4,0,2,3],  得分 3
 * 当k == 3, arr = [4,0,2,3,1],  得分 4
 * 当k == 4, arr = [0,2,3,1,4],  得分 3
 * 返回3
 */
public class SmallestRotationWithHighestScore {

    /**
     * 最优解 差分数组
     *
     * @param arr 原始数组
     * @return 旋转的最高分数
     */
    public static int bestRotation2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] score = new int[n + 1]; // score[i]代表数组整体向右移动i个位置的得分
        int curNum;
        for (int cur = 0; cur < n; cur++) {
            curNum = arr[cur];
            if (curNum < n) { // 如果当前数字大于等于n, 则无论其往右移动多少位都无法使数组得分
                if (curNum >= cur) {
                    score[curNum - cur]++; // 如果当前数字大于等于下标, 则k在[curNum - cur, n - cur)范围内可以获得分数
                    score[n - cur]--;
                } else { // 如果当前数字小于当前下标, 那么当前数字的左边部分和右边部分都有范围可以获得得分
                    score[0]++;
                    score[n - cur]--;
                    score[n - cur + curNum]++;
                    score[n]--;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            score[i] += score[i - 1]; // 差分数组处理
        }
        int bestScore = score[0]; // score最后统计的是向右移动的结果
        int bestK = 0; // K是向左移动的步数, 当K == 0时, 结果就是score[0], 当 K == 1时, 结果是score[n - 1]
        for (int i = n - 1; i >= 1; i--) {
            if (score[i] > bestScore) {
                bestScore = score[i];
                bestK = n - i; // 注意 K 和 i的关系转化
            }
        }
        return bestK;
    }

    /**
     * 暴力解
     *
     * @param arr 数组
     * @return 得分最高的最小旋转步数
     */
    public static int bestRotation(@NotNull int[] arr) {
        int bestScore = 0;
        int bestK = arr.length;
        for (int step = 0; step < arr.length; step++) {
            if (score(rotation(arr, step)) > bestScore) {
                bestScore = score(rotation(arr, step));
                bestK = step;
            }
        }
        return bestK;
    }

    /**
     * 计算数组得分
     *
     * @param arr 数组
     * @return 得分
     */
    @Contract(pure = true)
    public static int score(@NotNull int[] arr) {
        int score = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= i) {
                score++;
            }
        }
        return score;
    }

    /**
     * 逆时针旋转数组
     *
     * @param arr  原始数组
     * @param step 步数
     * @return 旋转后的数组
     */
    @NotNull
    public static int[] rotation(@NotNull int[] arr, int step) {
        int n = arr.length;
        if (n == 0 || step % n == 0) {
            return arr;
        }
        step %= n; // step大于n时，将step对n取余，保证step不超过n。
        int[] res = arr.clone();
        int cur = 0; // 起始下标
        int next; // 下一个下标
        int out = res[cur]; // 当前弹出的数
        int tmp; // 中间变量
        do {
            next = ((cur - step) + n) % n;
            tmp = res[next];
            res[next] = out;
            out = tmp;
            cur = next;
        } while (cur != 0);
        return res;
    }

    /**
     * 测试
     */
    public static void test() {
        System.out.println(bestRotation(new int[]{2, 3, 1, 4, 0}));
        System.out.println(bestRotation2(new int[]{2, 3, 1, 4, 0}));
    } // 结果应该是: 4

    public static void main(String[] args) {
        test();
    }

}
