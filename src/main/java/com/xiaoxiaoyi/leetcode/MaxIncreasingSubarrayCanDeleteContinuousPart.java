package com.xiaoxiaoyi.leetcode;

import java.util.Arrays;
import java.util.Objects;

import com.xiaoxiaoyi.tree.SegmentTree;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * 最大递增子数组可以删除连续部分
 */
public class MaxIncreasingSubarrayCanDeleteContinuousPart {

    private MaxIncreasingSubarrayCanDeleteContinuousPart() {

    }

    /**
     * 离散化数组
     *
     * @param sorted 升序数组
     * @param value  要离散化的数字
     * @return 离散化后的数字, [0, sorted.length)
     */
    @Contract(pure = true)
    public static int rank(@NotNull int[] sorted, int value) {
        int res = 0;
        while (res < sorted.length && sorted[res] < value) {
            res++;
        }
        return res;
    }

    public static int maxLen(int[] arr) {
        if (Objects.isNull(arr) || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] sorted = new int[n];
        System.arraycopy(arr, 0, sorted, 0, n);
        Arrays.sort(sorted);
        SegmentTree segmentTree = new SegmentTree(n);
        // 存入必须以arr[0]结尾且前面一定不可删的答案, 初始化更新从[rank(sorted, arr[0]) - 1,  rank(sorted, arr[0])]范围的线段树信息为1
        segmentTree.update(rank(sorted, arr[0]) + 1, 1);
        // dp[i]表示必须以arr[i]结尾且前面可删可不删的答案
        int[] dp = new int[n];
        // 以arr[0]结尾且不能删的答案是1
        dp[0] = 1;
        int ans = 1;
        // 前面一定不能删除的答案
        int ansOfCanNotDelete = 1;
        int rank;
        int case1;
        int case2;
        for (int i = 1; i < n; i++) {
            // 获取arr[i]的离散化信息
            rank = rank(sorted, arr[i]);
            // 可能性1: 前面一定不能删
            case1 = arr[i] > arr[i - 1] ? (dp[i - 1] + 1) : 1;
            // 可能性2: 前面可删可不删, 当我不是最小值的时候, 去我的左边找最大的结果 + 1
            case2 = rank > 0 ? (segmentTree.max(rank) + 1) : 1;
            dp[i] = Math.max(case1, case2);
            ans = Math.max(ans, dp[i]);
            // 必须以arr[i]结尾且前面一定不能删的答案存入线段树
            if (arr[i] > arr[i - 1]) {
                ansOfCanNotDelete++;
            } else {
                ansOfCanNotDelete = 1;
            }
            if (segmentTree.max(rank + 1) < ansOfCanNotDelete) {
                segmentTree.update(rank + 1, ansOfCanNotDelete);
            }
        }
        return ans;
    }

}
