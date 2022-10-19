package com.xiaoxiaoyi.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author xiaoxiaoyi
 * 给定一些项目的花费和做完项目后能够得到的利润(回报 - 花费)，给定初始资金w和最多能够做的项目数量k
 * 求能够获得的最大资金w
 */
public class FindMaximizedCapital {

    /**
     * 项目
     */
    public static class Project {
        // 项目花费
        int c;
        // 项目利润
        int p;

        // 初始化项目
        Project(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }

    /**
     * 按照项目的花费进行升序排序
     */
    private static class MinCostComparator implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            return o1.c - o2.c;
        }
    }

    /**
     * 按照项目的利润降序排序
     */
    private static class MaxProfitComparator implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            return o2.p - o1.p;
        }
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // 先准备一个以项目花费升序排列的小根堆和一个以项目利润降序排列的大根堆
        PriorityQueue<Project> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Project> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());

        // 然后将所有项目加入小根堆
        for (int i = 0; i < profits.length; i++) {
            minCostQ.add(new Project(capital[i], profits[i]));
        }

        // 最多执行k次，开始根据初始资金逐步解锁项目加入大根堆
        for (int i = 0; i < k; i++) {
            // 当还有项目未执行且当前最小成本的项目成本小于当前资金时
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= w) {
                // 小根堆弹出一个加入大根堆，即解锁该项目
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                // 说明当前资金不够做后续的任何项目，直接返回当前资金即可
                return w;
            }
            // 拿大根堆最上面的项目做，做完当前资金加上利润为下一轮的初始资金
            System.out.println("做了项目: c_" + maxProfitQ.peek().c + "_p_" + maxProfitQ.peek().p);
            w += maxProfitQ.poll().p;
        }

        // 返回当前资金
        return w;
    }

}
