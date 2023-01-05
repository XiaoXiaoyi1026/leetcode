package com.xiaoxiaoyi.leetcode;

import java.util.Arrays;

/**
 * @author xiaoxiaoyi
 * 给定一个数组, 寻找数组第k小的数
 */
public class BFPRT {

    /**
     * @return arr中第k小的数
     */
    public static int getKthSmallest(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k > arr.length) {
            throw new RuntimeException("Invalid params!");
        }
        // 第k小的数, 排序后对应的下标为k-1
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }

    /**
     * 求arr[start]~arr[end]范围内第k小的数
     * i一定 >=start && <=end
     */
    public static int bfprt(int[] arr, int start, int end, int i) {
        /*
        使用二分搜索法, 先从原数组中随机选择1个数x, 数组中<x的数放左边, =x的放中间, >x的放右边
        判断=x范围内的小标有没有命中, 如果没有再根据k的情况递归选择去左边或者右边找这个数
        BFPRT算法优化在第1步: 选择x的过程
        即: 将原arr5个数一组分成(n + 4)/5组数据, 然后每一组取中位数
        如果最后1组不够5个数且是偶数个, 那么取上中位数
        每个组的中位数取出来后组成的新数组arrMid再去求其上中位数, 求出来的上中位数就是x
        设数组长度为n, 那么求数组上中位数的过程其实就是求数组中第(n+1)/2小的数
        bfprt算法这样做之后, 假设原数组刚好分出来n/5组, 再假设每组都有5个数
        算法规模取决于每一次选边的时候, 被选择的那一边的规模
        假设现在算法选择了左边, 用最坏情况考虑, 可以知道arrMid的规模为n/5
        那么arrMid选中位数时, 会排除掉至少n/10规模的数, 这部分数映射到原来的数组就排除掉了至少(3*n)/10规模的数
        那么剩余的规模就至多到达(7*n)/10的规模, 实现了较为平衡的收敛过程
         */
        if (start == end) {
            // base case: 如果范围内只有1个数, 那么这个数就是答案
            return arr[start];
        }
        // 获取划分标准的那个数
        int standard = medianOfMedians(arr, start, end);
        // 根据标准对原数组进行调整
        int[] lens = adjust(arr, start, end, standard);
        // 如果i恰好落在等于的范围上, 则可以直接返回
        if (i >= lens[0] && i <= lens[1]) {
            return standard;
        }
        // 根据k的大小选择去左右两边的任意一边递归求解
        if (i < lens[0]) {
            // 去左边求解
            return bfprt(arr, start, lens[0] - 1, i);
        }
        // 去右边求解
        return bfprt(arr, lens[1] + 1, end, i);
    }

    /**
     * @param arr 原数组
     * @return 5个数为一组, 每一组求一个中位数, 然后在这个中位数数组中的中位数返回
     */
    public static int medianOfMedians(int[] arr, int start, int end) {
        int n = end - start + 1;
        // 共分成(n + 4) / 5组
        int groups = (n + 4) / 5;
        int[] medians = new int[groups];
        for (int i = 0; i < groups; i++) {
            int begin = start + i * 5;
            int stop = Math.min(end, begin + 4);
            medians[i] = getMedian(arr, begin, stop);
        }
        // 递归调用主函数, 求medians的中位数
        return bfprt(medians, 0, groups - 1, groups >>> 1);
    }

    /**
     * 将arr调整为左侧全部<standard, 中间全部等于standard, 右边全部大于standard的状态
     */
    public static int[] adjust(int[] arr, int start, int end, int standard) {
        int n = end - start + 1;
        if (n <= 0) {
            throw new RuntimeException("Invalid params!");
        }
        int cur = start;
        // 小于区的右边界
        int small = start - 1;
        // 大于区的左边界
        int big = end + 1;
        while (cur != big) {
            if (arr[cur] < standard) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] > standard) {
                swap(arr, cur, --big);
            } else {
                cur++;
            }
        }
        // 返回等于区的下标范围
        return new int[]{small + 1, big - 1};
    }

    /**
     * 获取arr[start]~arr[end]范围上的中位数
     */
    public static int getMedian(int[] arr, int start, int end) {
        int n = end - start + 1;
        if (n <= 0 || n > 5) {
            throw new RuntimeException("Invalid params!");
        }
        int[] tmp = new int[n];
        System.arraycopy(arr, start, tmp, 0, n);
        Arrays.sort(tmp);
        return tmp[(n - 1) >> 1];
    }

    public static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }
}
