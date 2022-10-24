package com.xiaoxiaoyi.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author xiaoxiaoyi
 */
public class Solution {

    public int minGroups(int[][] intervals) {
        if (intervals.length == 1) {
            return 1;
        }
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (!pq.isEmpty() && intervals[i][0] > pq.peek()) {
                pq.poll();
            }
            pq.add(intervals[i][1]);
        }
        return pq.size();
    }

}
