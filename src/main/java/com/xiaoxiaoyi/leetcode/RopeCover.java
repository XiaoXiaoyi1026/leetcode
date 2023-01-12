package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.monotonicstack.MonotonicStack;

import java.util.Comparator;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * 绳子覆盖问题
 */
public class RopeCover {

    public static int getMaximumCovers(List<int[]> ropes) {
        if (ropes == null || ropes.size() == 0) {
            return 0;
        }
        // 先将绳子按照起始点升序排序
        ropes.sort(Comparator.comparingInt(rope -> rope[0]));
        // 然后进入单调栈
        MonotonicStack<int[]> monotonicStack = new MonotonicStack<>(
                ropes, (o1, o2) -> o2[0] - o1[0]
        );
        int ans = 0;
        for (int i = 0; i < ropes.size(); i++) {
            monotonicStack.increaseRight();
            ans = Math.max(ans, monotonicStack.getDeque().size());
        }
        return ans;
    }

}
