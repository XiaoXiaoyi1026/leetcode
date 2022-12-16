package com.xiaoxiaoyi.leetcode;

import java.util.*;

/**
 * @author xiaoxiaoyi
 * 输入: 活动数量, 最多可花费天数
 * 每个活动所要花费的天数和可以得到的报酬, 以及代表活动间关系的邻接矩阵, 无环
 * 规定, 可以选择任意一个活动参加, 但是一旦开始参加活动, 那么后续的所有活动就都必须参加
 * 问在最多可花费的天数内, 最多可以获得多少报酬
 */
public class MaximumBenefit {

    public static class Activity {
        /**
         * 活动所需天数
         */
        public int day;
        /**
         * 活动可得报酬
         */
        public int pay;
        /**
         * 活动的前面有哪些活动
         */
        public List<Activity> front;
        /**
         * key: 天数, value: 报酬
         * 每一项代表选择该活动需要花费的总天数, 以及获得的报酬
         */
        TreeMap<Integer, Integer> map;

        @Override
        public String toString() {
            return "Activity{" +
                    "day=" + day +
                    ", pay=" + pay +
                    '}';
        }
    }

    /**
     * 由题意可知, 最后的活动必会参加, 所以考虑反向宽度优先遍历
     *
     * @param amount            活动数量
     * @param days              最多可花费天数
     * @param activitiesMessage 活动情况
     */
    public static int[] maximumBenefit(int amount, int days, int[][] activitiesMessage) {
        // 先从前往后初始化每一个活动
        Activity[] activities = new Activity[amount];
        for (int row = 0; row < amount; row++) {
            Activity activity = new Activity();
            activity.day = activitiesMessage[row][0];
            activity.pay = activitiesMessage[row][1];
            activity.front = new ArrayList<>();
            // map用有序表, 根据key升序
            activity.map = new TreeMap<>(Comparator.comparingInt(key -> key));
            // 判断节点有没有后续节点
            boolean later = false;
            for (int col = 0; col < amount; col++) {
                if (activitiesMessage[row][col + 2] == 1) {
                    later = true;
                    break;
                }
            }
            // 如果没有后续节点, 则put一条信息进map
            if (!later) {
                activity.map.put(activity.day, activity.pay);
            }
            activities[row] = activity;
        }
        /*
        原始领接矩阵每一行沿着矩阵对角线顺时针90度即可得到反向图, 所以按照列进行遍历, 头2列是活动的天数和报酬, 不参与遍历
        根据分析, 对原始矩阵从右往左, 从上往下遍历即可得到正确的活动信息
         */
        for (int col = amount + 1; col > 1; col--) {
            for (int row = 0; row < amount; row++) {
                if (activitiesMessage[row][col] == 1) {
                    // 说明col活动的前面有row
                    activities[col - 2].front.add(activities[row]);
                }
            }
            System.out.println(activities[col - 2].front);
        }
        // 从后往前进行宽度优先遍历
        for (int i = amount - 1; i >= 0; i--) {
            Activity cur = activities[i];
            // 遍历其所有前趋节点
            for (Activity activity : cur.front) {
                for (Integer day : cur.map.keySet()) {
                    if (day + activity.day <= days) {
                        activity.map.put(day + activity.day, cur.map.get(day) + activity.pay);
                    }
                }
            }
            int prePay = 0;
            // 可以预见的是, 当前节点生成完map之后, map中按照天数升序排列的
            for (Integer day : cur.map.keySet()) {
                if (cur.map.get(day) < prePay) {
                    cur.map.remove(day);
                } else {
                    prePay = cur.map.get(day);
                }
            }
            System.out.println(cur.map);
        }
        // 宽度优先遍历完成之后, 从最开始的节点的map中找到day<=days的最右pay, 即为答案
        int[] ans = {0, 0};
        for (Activity activity : activities) {
            for (Integer day : activity.map.keySet()) {
                if (day <= days && activity.map.get(day) > ans[0]) {
                    ans[0] = activity.map.get(day);
                    ans[1] = day;
                }
            }
        }
        return ans;
    }

}
