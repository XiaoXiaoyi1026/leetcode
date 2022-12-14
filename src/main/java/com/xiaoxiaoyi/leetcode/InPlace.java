package com.xiaoxiaoyi.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * in place 算法, 给定一个长度为n的数组, 规定里面的数的范围都是1~n
 * 有些数不会出现, 有些数会重复出现, 问所有没出现过的数
 */
public class InPlace {

    public static List<Integer> inPlace(int[] arr) {
        List<Integer> res = new ArrayList<>();
        // 力求让arr[i]位置上的数 = i + 1
        for (int value : arr) {
            // 如果value应该在的位置不是value
            while (arr[value - 1] != value) {
                // 将位置上的数拿出来
                int tmp = arr[value - 1];
                // 放到它该在的位置
                arr[value - 1] = value;
                // 然后进行下一轮交换
                value = tmp;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            // 遍历调整后的数组, 如果i位置的数不是i + 1, 说明i + 1未出现
            if (arr[i] != i + 1) {
                res.add(i + 1);
            }
        }
        return res;
    }

}
