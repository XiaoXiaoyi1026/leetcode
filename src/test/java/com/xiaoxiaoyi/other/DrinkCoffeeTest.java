package com.xiaoxiaoyi.other;

import junit.framework.TestCase;

public class DrinkCoffeeTest extends TestCase {

    public void testDrinkCoffee() {
        int[] efficiency = {2, 3, 7};
        int n = 10, washTime = 3, selfCleanTime = 6;
        int ans1 = DrinkCoffee.minimumTime1(efficiency, n, washTime, selfCleanTime);
        System.out.println(ans1);
        int ans2 = DrinkCoffee.minimumTime2(efficiency, n, washTime, selfCleanTime);
        System.out.println(ans2);
    }

}
