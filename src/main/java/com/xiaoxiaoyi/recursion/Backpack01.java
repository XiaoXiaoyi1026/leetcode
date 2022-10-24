package com.xiaoxiaoyi.recursion;

/**
 * @author xiaoxiaoyi
 * 经典01背包问题，给你一个最多装capacity重量的背包，一个物品价值数组values和物品重量数组weights
 * 问在不超过背包总重量的情况下，能够装下的最多价值的物品是多少
 */
public class Backpack01 {

    private static int capacity;
    private static int[] values;
    private static int[] weights;

    /**
     * 01背包问题
     *
     * @param capacity 背包总容量
     * @param values   物品的价值
     * @param weights  物品的重量
     * @return 最大能装的价值
     */
    public static int backpack01(int capacity, int[] values, int[] weights) {
        Backpack01.capacity = capacity;
        Backpack01.values = values;
        Backpack01.weights = weights;

        return process(0, 0);
    }

    /**
     * 选择物品
     *
     * @param curItem        当前物品的下标
     * @param selectedWeight 已选物品的总重量
     * @return 返回从当前物品开始选择，后面所有物品能够选择的最大重量
     */
    private static int process(int curItem, int selectedWeight) {
        if (selectedWeight == capacity) {
            // 如果已选物品重量等于背包的容量，则后续无法选择任何物品，重量为0
            return 0;
        }
        if (curItem == weights.length) {
            // 如果所有物品都已经选完了，则没有后续选择，重量为0
            return 0;
        }
        // 判断当前物品能否装进背包
        if (weights[curItem] <= capacity - selectedWeight) {
            // 可以装进去，则有2条路，装或者不装当前物品，返回装或不装的最大重量
            return Math.max(process(curItem + 1, selectedWeight),
                    values[curItem] + process(curItem + 1, selectedWeight + weights[curItem]));
        }
        // 当前物品无法装进背包，只能放弃，再去尝试装后面的物品
        return process(curItem + 1, selectedWeight);
    }

}
