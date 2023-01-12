package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RopeCoverTest extends TestCase {

    public void testGetMaximumCovers() {
        List<int[]> ropes = new ArrayList<>(5);
        ropes.add(new int[]{2, 5});
        ropes.add(new int[]{1, 9});
        ropes.add(new int[]{3, 10});
        ropes.add(new int[]{6, 8});
        ropes.add(new int[]{2, 4});
        System.out.println(RopeCover.getMaximumCovers(ropes));
    }

}
