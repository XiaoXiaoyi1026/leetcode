package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

import java.util.Arrays;

public class GasStationTest extends TestCase {

    public void testGasStation() {
        int[] gas = {8, 4, 6, 5, 2, 8, 4, 12};
        int[] distance = {5, 8, 4, 6, 3, 7, 5, 3};
        System.out.println(Arrays.toString(GasStation.getStatus(gas, distance)));
    }

}
