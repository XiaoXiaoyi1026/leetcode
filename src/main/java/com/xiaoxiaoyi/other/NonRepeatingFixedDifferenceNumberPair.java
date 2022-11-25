package com.xiaoxiaoyi.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个数组和目标值k, 要求返回所有的差值为k的数字对无重复
 */
public class NonRepeatingFixedDifferenceNumberPair {

    public static int[][] getNonRepeatingFixedDifferenceNumberPair(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        System.out.println("map -> " + map + "||k -> " + k);
        List<int[]> tmp = new ArrayList<>();
        for (int num : map.keySet()) {
            if (map.containsKey(num + k)) {
                tmp.add(new int[]{num, nums[map.get(num + k)]});
            }
        }
        int[][] res = new int[tmp.size()][2];
        for (int i = 0; i < tmp.size(); i++) {
            res[i] = tmp.get(i);
        }
        return res;
    }

}
