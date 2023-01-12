package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.tree.orderedlist.Heap;

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
        int ans = 0;
        // 定义一个小根堆(有序表即可), 按照线段的end组织
        Heap<int[]> heap = new Heap<>((o1, o2) -> o2[1] - o1[1]);
        // 所有绳子依次入堆
        for (int[] rope : ropes) {
            boolean flag = false;
            // 如果绳子的起点 >= 小根堆堆顶
            while (!heap.isEmpty() && rope[0] >= heap.peek()[1]) {
                // 那么有线段需要出堆, 此时堆中的线段数量为可能的结果
                if (!flag) {
                    ans = Math.max(ans, heap.size());
                    flag = true;
                }
                heap.pop();
            }
            heap.insert(rope);
        }
        return Math.max(ans, heap.size());
    }

}
