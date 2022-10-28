package com.xiaoxiaoyi.greedy;

import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author xiaoxiaoyi
 * 分割金块的最小成本问题，给定一个整数序列，金块总长度为该序列的累加和
 * 每一次切割金块都要花费金块当前长度的铜板，例如要对60长度的金块进行一次切割则需要花费60个铜板
 * 问怎么样切割金块使其变成目标序列的长度能够最省铜板，返回所用的铜板数
 */
public class LessMoneySplitGold {

    /**
     * 求最少铜板
     *
     * @param lengths 长度序列
     * @return 铜板数
     */
    public static int lessMoney(int[] lengths) {
        // 准备一个小根堆
        PriorityQueue<Integer> pQ = new PriorityQueue<>(lengths.length);
        // 所有长度进入小根堆
        for (int length : lengths) {
            pQ.add(length);
        }
        // 记录总铜板数
        int sum = 0;
        // 每轮切割要花费的铜板数
        int cur;
        // 循环进行计算
        while (pQ.size() > 1) {
            cur = pQ.poll() + Objects.requireNonNull(pQ.poll());
            pQ.add(cur);
            sum += cur;
        }
        return sum;
    }

}
