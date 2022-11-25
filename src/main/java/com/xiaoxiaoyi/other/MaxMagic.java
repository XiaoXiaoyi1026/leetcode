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
        double sum1 = sum(set1), sum2 = sum(set2), avg1 = 0, avg2 = 0;
        if (set1.size() != 0) {
            avg1 = sum1 / set1.size();
        }
        if (set2.size() != 0) {
            avg2 = sum2 / set2.size();
        }
        if (avg1 == avg2) {
            // 集合平均值相等时无法用magic操作, 因为无论如何移动都无法满足平均值上升
            return 0;
        }
        if (sum1 < sum2) {
            double tmp = sum1;
            sum1 = sum2;
            sum2 = tmp;
        }
        int magic = 0;
        Set<Integer> maxSet = avg1 >= avg2 ? set1 : set2;
        Set<Integer> minSet = avg1 >= avg2 ? set2 : set1;
        if (avg1 < avg2) {
            double tmp = avg1;
            avg1 = avg2;
            avg2 = tmp;
        }
        // 只可能从大平均值的集合往小平均值的集合拿数字
        int[] betweenTwoAvg = getBetweenTwoAvg(maxSet, avg1, avg2);
        // 有没有进行过magic操作
        boolean flag = true;
        // 如果上一轮进行过magic操作而且还有可以操作的数字
        while (flag && betweenTwoAvg.length > 0) {
            for (int num : betweenTwoAvg) {
                flag = false;
                if (minSet.contains(num)) {
                    // 如果小集合中已经有改数字, 则跳过
                    continue;
                }
                System.out.println("move " + num);
                maxSet.remove(num);
                minSet.add(num);
                System.out.println(maxSet);
                System.out.println(minSet);
                magic++;
                flag = true;
                avg1 = (sum1 - num) / maxSet.size();
                avg2 = (sum2 - num) / minSet.size();
            }
            betweenTwoAvg = getBetweenTwoAvg(maxSet, avg1, avg2);
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

    private static double sum(Set<Integer> set) {
        double res = 0;
        for (Integer num : set) {
            res += num;
        }
        return res;
    }

}
