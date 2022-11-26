package com.xiaoxiaoyi.other;

import java.util.*;

/**
 * @author xiaoxiaoyi
 * 求最大的magic操作次数
 * magic: 在2个集合间, 将一个集合中的数字拿到另一个集合中, 要求拿完后, 两个集合的平均值都要上升
 * 拿过去的那个数字在接收集合中不存在
 */
public class MaxMagic {

    /**
     * 保证set1和set2非空且无重复
     */
    public static int maxMagic(int[] nums1, int[] nums2) {
        int sum1 = sum(nums1), sum2 = sum(nums2);
        double avg1 = avg(sum1, nums1.length), avg2 = avg(sum2, nums2.length);
        if (avg1 == avg2) {
            // 集合平均值相等时无法用magic操作, 因为无论如何移动都无法满足平均值上升
            return 0;
        }
        if (avg1 < avg2) {
            // avg1指向较大的
            double tmp1 = avg1;
            avg1 = avg2;
            avg2 = tmp1;
            // nums1指向平均值较大的
            int[] tmp2 = nums1;
            nums1 = nums2;
            nums2 = tmp2;
            // sum1指向较大平均值的set
            tmp1 = sum1;
            sum1 = sum2;
            sum2 = (int) tmp1;
        }
        Set<Integer> maxSet = new HashSet<>(), minSet = new HashSet<>();
        // 对较大平均值的数组排序, 方便后续遍历
        Arrays.sort(nums1);
        for (int num : nums1) {
            maxSet.add(num);
        }
        for (int num : nums2) {
            minSet.add(num);
        }
        System.out.println(maxSet);
        System.out.println(minSet);
        System.out.println(avg1);
        System.out.println(avg2);
        int magic = 0;
        for (int num : nums1) {
            if (num < avg1 && num > avg2 && !minSet.contains(num)) {
                System.out.println("=========================");
                System.out.println("move " + num);
                maxSet.remove(num);
                minSet.add(num);
                sum1 -= num;
                sum2 += num;
                avg1 = avg(sum1, maxSet.size());
                avg2 = avg(sum2, minSet.size());
                System.out.println(maxSet);
                System.out.println(minSet);
                System.out.println(avg1);
                System.out.println(avg2);
                magic++;
            }
        }
        return magic;
    }

    private static double avg(int sum, int size) {
        return sum / (double) size;
    }

    private static int sum(int[] nums) {
        int res = 0;
        for (Integer num : nums) {
            res += num;
        }
        return res;
    }
}
