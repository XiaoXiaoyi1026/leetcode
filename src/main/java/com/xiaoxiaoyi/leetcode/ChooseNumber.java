package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 选数字问题, 要求从1到199这200个整数中, 选择任意2个数组成一对, 使得它们的和大于199
 * 比如[1, 199]和[199, 1]算1个组合
 */
public class ChooseNumber {

    public static int combinations() {
        int ans = 0;
        for (int cur = 1; cur < 200; cur++) {
            // cur为当前选择到的数, another为另外一个数的下限, 上限即为199
            int anotherLowerLimit = 200 - cur;
            for (int another = Math.max(anotherLowerLimit, cur + 1); another < 200; another++) {
                // 要求2个数之间必须是递增的关系, 可以排除相同两数但位置不同的情况
                ans++;
                System.out.println("[" + cur + ", " + another + "]");
            }
        }
        return ans;
    }

}
