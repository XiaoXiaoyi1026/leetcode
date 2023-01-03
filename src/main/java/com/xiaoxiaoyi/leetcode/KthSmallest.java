package com.xiaoxiaoyi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * 给定一个数组, 寻找数组第k小的数
 */
public class KthSmallest {

    /**
     * @return arr中第k小的数
     */
    public static int get(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k > arr.length) {
            throw new RuntimeException("Invalid params!");
        }
        return bfprt(arr, k);
    }

    /**
     * @param arr 原数组
     * @param k   求第k小的数
     * @return arr中第k小的数
     */
    public static int bfprt(int[] arr, int k) {
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
        int n = arr.length;
        // 一共(n + 4) / 5组
        int groups = (n + 4) / 5;
        int[] arrMid = new int[groups];
        if (n <= 5) {
            // 少于或者等于5个数
            Arrays.sort(arr);
            return arr[k - 1];
        } else if (n % 5 != 0) {
            // 单独处理最后一组, n % 5为最后一组的长度
            arrMid[groups - 1] = getLengthLessOrEquals5ArrayMid(arr, n - (n % 5), n);
        }
        for (int i = 0; i < groups - 1; i++) {
            // 前面5个数为一组, 5个数为1组的中位数为第3小的数
            arrMid[i / 5] = getLengthLessOrEquals5ArrayMid(arr, i * 5, (i + 1) * 5);
        }
        // 求arrMid的上中位数
        int choose = bfprt(arrMid, (groups + 1) >> 1);
        // 根据中位数将原数组分成3部分
        int less = 0, lessOrEquals = 0;
        for (int num : arr) {
            if (num <= choose) {
                if (num < choose) {
                    less++;
                }
                lessOrEquals++;
            }
        }
        // 如果k恰好落在等于的范围上, 则可以直接返回
        if (k > less && k <= lessOrEquals) {
            return choose;
        }
        // 没命中才进行调整
        arr = adjust(arr, choose);
        // 根据k的大小选择去左右两边的任意一边递归求解
        if (k <= less) {
            // 去左边求解
            return bfprt(Arrays.copyOfRange(arr, 0, less), k);
        }
        // 去右边求解
        return bfprt(Arrays.copyOfRange(arr, lessOrEquals, n), k - lessOrEquals);
    }

    /**
     * 将arr调整为左侧全部<standard, 中间全部等于standard, 右边全部大于standard的状态
     */
    public static int[] adjust(int[] arr, int standard) {
        int n = arr.length;
        int[] tmp = new int[n];
        List<Integer> equals = new ArrayList<>();
        int left = 0, right = n - 1;
        for (int num : arr) {
            if (num < standard) {
                tmp[left++] = num;
            } else if (num > standard) {
                tmp[right--] = num;
            } else {
                equals.add(num);
            }
        }
        for (int i = left; i <= right; i++) {
            tmp[i] = equals.get(left - i);
        }
        return tmp;
    }

    /**
     * 获取长度小于等于5的arr的中位数
     */
    public static int getLengthLessOrEquals5ArrayMid(int[] arr, int left, int right) {
        int n = right - left;
        if (arr == null || n <= 0 || n > 5) {
            throw new RuntimeException("Array length error!");
        }
        int[] tmp = new int[n];
        System.arraycopy(arr, left, tmp, 0, n);
        Arrays.sort(tmp);
        return tmp[(n - 1) >> 1];
    }

}
