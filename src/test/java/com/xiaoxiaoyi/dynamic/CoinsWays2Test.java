package com.xiaoxiaoyi.dynamic;

import junit.framework.TestCase;

import java.util.Arrays;

public class CoinsWays2Test extends TestCase {

    public void testTake() {
        int[] coins = {2, 3, 5};
        System.out.println(Arrays.toString(CoinsWays2.takeOne(coins, 6)));
        System.out.println(Arrays.toString(CoinsWays2.takeOrdinary(coins, 6)));
    }

    public void testGet() {
        int[] one = {2, 3, 5};
        int[] ordinary = {1, 2, 4};
        System.out.println(Arrays.toString(CoinsWays2.takeOne(one, 10)));
        System.out.println(Arrays.toString(CoinsWays2.takeOrdinary(ordinary, 10)));
        System.out.println(CoinsWays2.get(one, ordinary, 10));
    }

}
