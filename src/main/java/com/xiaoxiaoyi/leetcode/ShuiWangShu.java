package com.xiaoxiaoyi.leetcode;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 水王数(只出现次数超过数组长度一半的
 *要求时间复杂度O(N), 且不能申请表, 只能申请有限变量)
 * @date 3/18/2023 1:35 PM
 */
public class ShuiWangShu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int shuiWangShu = shuiWangShu(nums);
        if (shuiWangShu == -1) {
            System.out.println("水王数不存在");
        } else {
            System.out.println(shuiWangShu);
        }
    }

    @Contract(pure = true)
    public static int shuiWangShu(@NotNull int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0];
        }
        // 一次删掉两个不同的数字, 如果存在水王数, 则留下的一定是水王数, 但是剩下的数不一定是水王数
        int res = -1;
        // 候选出现的次数
        int HP = 0;
        for (int num : nums) {
            if (HP == 0) {
                // 如果当前没有候选, 则当前遍历到的数作为候选
                res = num;
                HP = 1;
            } else {
                // 如果当前存在候选
                if (num == res) {
                    // 如果当前遍历到的数和候选一样, 那么候选的HP+1
                    HP++;
                } else {
                    // 否则当前数将选择和候选的一点HP同归于尽(删除这两个不一样的数字)
                    HP--;
                }
            }
        }
        if (HP == 0) {
            return -1;
        }
        HP = 0;
        // 再次遍历确认候选是否是水王数
        for (int num : nums) {
            if (num == res) {
                HP++;
            }
        }
        if (HP > (n >> 1)) {
            return res;
        } else {
            return -1;
        }
    }

}
