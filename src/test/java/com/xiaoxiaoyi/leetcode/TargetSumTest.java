package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomUtils;
import junit.framework.TestCase;

public class TargetSumTest extends TestCase {

    public void testAll() {
        int length = (int) (Math.random() * 20);
        int max = (int) (Math.random() * 100);
        int testTimes = 1000;
        int target = (int) (Math.random() * 100);
        int[] nums;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            nums = RandomUtils.array(length, max);
            int ans1 = TargetSum.findTargetSumWays1(nums, target);
            int ans2 = TargetSum.findTargetSumWays2(nums, target);
            int ans3 = TargetSum.findTargetSumWays3(nums, target);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("ooops!");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
