package com.xiaoxiaoyi.optimization.greedy;

/**
 * @author xiaoxiaoyi
 * 装苹果问题, 一种袋子能6个, 一种袋子能装8个, 问最少用多少个袋子可以刚好装下n个苹果
 * 如果不能刚好装下, 则返回-1
 */
public class PackApples {

    public static int packApples(int apples) {
        if ((apples & 1) == 1) {
            // 奇数个苹果直接返回-1
            return -1;
        }
        // 尝试可能性
        int eightBag = apples / 8;
        // 当8个袋子>=0且剩余苹果数小于24时才进行尝试
        while (eightBag >= 0 && apples - eightBag * 8 < 24) {
            if ((apples - eightBag * 8) % 6 == 0) {
                // 剩余苹果数能被6袋子装下, 返回数量
                return eightBag + (apples - eightBag * 8) / 6;
            }
            eightBag--;
        }
        return -1;
    }

    public static int bestSolution(int apples) {
        if ((apples & 1) == 1 || apples < 6 || apples == 10) {
            return -1;
        }
        // 发现的规律
        return (apples - 1) / 8 + 1;
    }

}
