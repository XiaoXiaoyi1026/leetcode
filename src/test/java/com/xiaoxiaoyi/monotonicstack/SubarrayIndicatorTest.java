package com.xiaoxiaoyi.monotonicstack;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubarrayIndicatorTest extends TestCase {

    public void testSubarrayIndicator() {
        List<Integer> input = new ArrayList<>();
        input.add(3);
        input.add(2);
        input.add(5);
        input.add(6);
        input.add(7);
        input.add(6);
        System.out.println(Arrays.toString(SubarrayIndicator.subarrayIndicator(input)));
    }

}
