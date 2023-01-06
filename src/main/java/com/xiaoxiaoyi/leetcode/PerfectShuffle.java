package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 完美洗牌问题: 给定一个2*n长度的数组
 * 数组分成左右2个半区, 分别为L1, L2, ..., Ln, R1, R2, ...,Rn
 * 要求在额外空间复杂度O(1)的情况下, 将数组调整为R1, L1, R2, L2, ...,Rn, Ln
 */
public class PerfectShuffle {

    public static void shuffle(int[] arr) {
        if (arr == null || arr.length == 0 || (arr.length & 1) != 0) {
            throw new RuntimeException("Invalid param!");
        }
        shuffle(arr, 0, arr.length - 1);
    }

    public static void shuffle(int[] arr, int begin, int end) {
        int n = end - begin + 1;
        if (n == 2) {
            // base case: 如果范围内只有2个数, 则直接交换即可
            exchange(arr, begin, end);
            return;
        }
        // 先找比n小的最大的那个数m, 满足m = 3^k-1(k >= 1)
        int m = 2;
        while (m < n) {
            m = (m + 1) * 3 - 1;
        }
        // 此时m是>n的第一个满足m = 3^k-1的数, 往回走一个
        m = (m + 1) / 3 - 1;
        /*
        将原数组后半部分的前m/2个元素和前半部分的后n/2-m/2个元素进行范围交换
        交换完成后, 原数组的前m个数就是L1, R1, L2, R2, ..., L(m/2), R(m/2)
        然后在0~m-1范围上进行调整, 调整完后后面的n-m个元素递归进行这个过程即可
         */
        int rightBegin = begin + (n >> 1);
        int leftBegin = begin + (m >> 1);
        int leftEnd = rightBegin - 1;
        // 原数组后半位置的开始下标
        int rightEnd = leftEnd + (m >> 1);
        exchange(arr, leftBegin, leftEnd, rightBegin, rightEnd);
        adjust(arr, begin, begin + m - 1);
        shuffle(arr, begin + m, end);
    }

    /**
     * @param arr   数组
     * @param begin 要调整范围的起始下标
     * @param end   要调整范围的结束下标
     */
    public static void adjust(int[] arr, int begin, int end) {
        // 范围内一共n个数, n = 3^k-1, k >= 1
        int n = end - begin + 1;
        /*
        当n = 3^k - 1, k >= 1时, 存在触发点: 1, 3, 9, ...,3^(k-1)
        存在关系: 下标 = 触发点 - 1, 即对应下标为: 0, 2, 8, ...,3^(k-1) - 1
         */
        int trigger = 0;
        while (trigger < n) {
            // 当前开始循环的数的下标
            int from = begin + trigger;
            // 计算他要去到的位置
            int to = computeTo(from, begin, end);
            // 一开始被弹出的数
            int cur = arr[from];
            // 下标循环怼的过程
            while (arr[to] != cur) {
                // 如果它要去到的位置上不是它
                int tmp = arr[to];
                // 将cur放到它应该在的位置上
                arr[to] = cur;
                // 原本位置上的数被弹出
                cur = tmp;
                // 更新被弹出位置上的数应该去到的位置
                to = computeTo(to, begin, end);
            }
            // 下一个触发点对应的下标
            trigger = (trigger + 1) * 3 - 1;
        }
    }

    /**
     * @param from  原来的下标from, 0 <= from < n
     * @param begin 范围起始下标
     * @param end   范围结束下标
     * @return 调整后将到达的下标
     */
    public static int computeTo(int from, int begin, int end) {
        if (((end - begin + 1) & 1) != 0) {
            throw new RuntimeException("Invalid params!");
        }
        // 范围下半开始位置
        int half = ((begin + end) >> 1) + 1;
        if (from < half) {
            // 说明from在范围的前半部分
            return ((from - begin) << 1) + 1 + begin;
        } else {
            // 右半部分下标变化
            return ((from - half) << 1) + begin;
        }
    }

    /**
     * 将arr的leftBegin~leftEnd和rightBegin~rightEnd进行交换
     */
    public static void exchange(int[] arr, int leftBegin, int leftEnd, int rightBegin, int rightEnd) {
        if (leftBegin < 0 || leftBegin > leftEnd || leftEnd >= rightBegin || rightBegin > rightEnd || rightEnd >= arr.length) {
            throw new RuntimeException("Invalid params!");
        }
        reverse(arr, leftBegin, leftEnd);
        reverse(arr, rightBegin, rightEnd);
        reverse(arr, leftBegin, rightEnd);
        if (rightBegin - leftEnd > 1) {
            int leftCount = leftEnd - leftBegin;
            int rightCount = rightEnd - rightBegin;
            // 如果两部分不连续, 则把中间部分逆序回来
            reverse(arr, leftBegin + rightCount, rightEnd - leftCount);
        }
    }

    /**
     * 将arr[begin]~arr[end]部分逆序
     */
    public static void reverse(int[] arr, int begin, int end) {
        if (begin < 0 || begin > end || end >= arr.length) {
            throw new RuntimeException("Invalid params!");
        }
        int left = begin;
        int right = end;
        while (left < right) {
            exchange(arr, left++, right--);
        }
    }

    /**
     * 将arr[from]和arr[to]两个位置的元素交换
     */
    public static void exchange(int[] arr, int from, int to) {
        if (from < 0 || from >= arr.length || to < 0 || to >= arr.length) {
            throw new RuntimeException("Invalid params!");
        }
        if (from != to) {
            int tmp = arr[from];
            arr[from] = arr[to];
            arr[to] = tmp;
        }
    }

}
