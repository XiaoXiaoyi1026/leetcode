package com.xiaoxiaoyi.recursion;

/**
 * @author xiaoxiaoyi
 * 给定一个卡牌数组，规定2名玩家只能从数组两端拿牌，每个玩家都很聪明，问谁最后能够获胜
 */
public class BestWayToHoldPoker {

    /**
     * 主函数
     *
     * @param arr 牌
     * @return 先手或者后手情况下的最大值
     */
    public static int bestWayToHoldPoker(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    /**
     * 后手函数
     *
     * @param arr   牌
     * @param left  可选牌左边界
     * @param right 可选牌右边界
     * @return 先手的2种情况中的最小值
     */
    private static int s(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        return Math.min(f(arr, left + 1, right), f(arr, left, right - 1));
    }

    /**
     * 先手函数
     *
     * @param arr   牌
     * @param left  可选牌左边界
     * @param right 可选牌右边界
     * @return 后手的2种情况中的最大值
     */
    private static int f(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        return Math.max(arr[left] + s(arr, left + 1, right), arr[right] + s(arr, left, right - 1));
    }

}
