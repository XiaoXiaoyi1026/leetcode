package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RectangularCoincidenceTest extends TestCase {

    public void testGetMaximumCoincidence() {
        List<int[]> rectangular = new ArrayList<>(5);
        rectangular.add(new int[]{1, 1, 3, 5});
        rectangular.add(new int[]{2, 0, 4, 3});
        rectangular.add(new int[]{4, 6, 6, 7});
        rectangular.add(new int[]{0, 1, 5, 2});
        rectangular.add(new int[]{2, 2, 5, 7});
        System.out.println(RectangularCoincidence.getMaximumCoincidence(rectangular));
    }

}
