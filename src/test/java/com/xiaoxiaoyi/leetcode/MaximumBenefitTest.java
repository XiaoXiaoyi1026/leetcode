package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class MaximumBenefitTest extends TestCase {

    public void testMaximumBenefit() {
        int amount = 8, days = 10;
        int[][] activitiesMessage = {
                {3, 2000, 0, 1, 1, 0, 0, 0, 0, 0},
                {3, 4000, 0, 0, 0, 1, 1, 0, 0, 0},
                {2, 2500, 0, 0, 0, 1, 0, 0, 0, 0},
                {1, 1600, 0, 0, 0, 0, 1, 1, 1, 0},
                {4, 3800, 0, 0, 0, 0, 0, 0, 0, 1},
                {2, 2600, 0, 0, 0, 0, 0, 0, 0, 1},
                {4, 4000, 0, 0, 0, 0, 0, 0, 0, 1},
                {3, 3500, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[] ans = MaximumBenefit.maximumBenefit(amount, days, activitiesMessage);
        System.out.println(Arrays.toString(ans));
    }

}
