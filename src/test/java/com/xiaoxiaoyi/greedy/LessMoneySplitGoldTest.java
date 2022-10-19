package com.xiaoxiaoyi.greedy;

import com.xiaoxiaoyi.greedy.LessMoneySplitGold;
import junit.framework.TestCase;

public class LessMoneySplitGoldTest extends TestCase {

    public void testLessMoneySplitGold() {
        int[] lengths = new int[]{10, 20, 30, 40};
        System.out.println(LessMoneySplitGold.lessMoney(lengths));
    }

}
