package com.xiaoxiaoyi.leetcode;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 乘船过河问题2, 要求2人一船的时候体重和为偶数
 * @date 3/19/2023 2:57 PM
 */
public class CrossTheRiverByBoat2 {

    public static void main(String[] args) {
        int[] weights = {1, 1, 3, 3, 3, 5, 5, 5, 6, 6, 7, 7, 8};
        System.out.println(minCostBoat(weights, 10));
    }

    public static int minCostBoat(@NotNull int[] weights, int limit) {
        // 将原数组分成奇数和偶数2个子数组
        List<Integer> jiShu = new ArrayList<>();
        List<Integer> ouShu = new ArrayList<>();
        for (int weight : weights) {
            if ((weight & 1) == 1) {
                jiShu.add(weight);
            } else {
                ouShu.add(weight);
            }
        }
        int[] tmp1 = new int[jiShu.size()];
        int i = 0;
        for (Integer weight : jiShu) {
            tmp1[i++] = weight;
        }
        int[] tmp2 = new int[jiShu.size()];
        i = 0;
        for (int weight : ouShu) {
            tmp2[i++] = weight;
        }
        return CrossTheRiverByBoat.needBoats2(tmp1, limit) +
                CrossTheRiverByBoat.needBoats2(tmp2, limit);
    }

}
