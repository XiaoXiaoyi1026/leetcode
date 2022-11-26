package com.xiaoxiaoyi.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author xiaoxiaoyi
 * 求最大的magic操作次数
 * magic: 在2个集合间, 将一个集合中的数字拿到另一个集合中, 要求拿完后, 两个集合的平均值都要上升
 * 拿过去的那个数字在接收集合中不存在
 */
public class MaxMagic {

    public static int maxMagic(Set<Integer> set1, Set<Integer> set2) {
        int sum1 = sum(set1), sum2 = sum(set2);
        double avg1 = sum1 / (double) set1.size(), avg2 = sum2 / (double) set2.size();
        if (avg1 == avg2) {
            // 集合平均值相等时无法用magic操作, 因为无论如何移动都无法满足平均值上升
            return 0;
        }
        int magic = 0;
        if (avg1 < avg2) {
            // avg1指向较大的
            double tmp = avg1;
            avg1 = avg2;
            avg2 = tmp;
            // set1指向平均值较大的set
            Set<Integer> tmpSet = set1;
            set1 = set2;
            set2 = tmpSet;
            // sum1指向较大平均值的set
            tmp = sum1;
            sum1 = sum2;
            sum2 = (int) tmp;
        }
        System.out.println(set1);
        System.out.println(set2);
        System.out.println(avg1);
        System.out.println(avg2);
        // 只可能从大平均值的集合往小平均值的集合拿数字
        int[] betweenTwoAvg = getBetweenTwoAvg(set1, avg1, avg2);
        // 有没有进行过magic操作
        boolean flag = true;
        // 如果上一轮进行过magic操作而且还有可以操作的数字
        while (flag && betweenTwoAvg.length > 0) {
            for (int num : betweenTwoAvg) {
                flag = false;
                if (set2.contains(num)) {
                    // 如果小集合中已经有改数字, 则跳过
                    continue;
                }
                System.out.println("move " + num);
                set1.remove(num);
                set2.add(num);
                avg1 = (sum1 - num) / (double) set1.size();
                avg2 = (sum2 + num) / (double) set2.size();
                magic++;
                flag = true;
                System.out.println(set1);
                System.out.println(set2);
                System.out.println(avg1);
                System.out.println(avg2);
                betweenTwoAvg = getBetweenTwoAvg(set1, avg1, avg2);
            }
        }
        return magic;
    }

    private static int[] getBetweenTwoAvg(Set<Integer> maxSet, double maxAvg, double minAvg) {
        List<Integer> tmp = new ArrayList<>();
        for (Integer num : maxSet) {
            if (num > minAvg && num < maxAvg) {
                tmp.add(num);
            }
        }
        int[] res = new int[tmp.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = tmp.get(i);
        }
        Arrays.sort(res);
        return res;
    }

    private static int sum(Set<Integer> set) {
        int res = 0;
        for (Integer num : set) {
            res += num;
        }
        return res;
    }
}
