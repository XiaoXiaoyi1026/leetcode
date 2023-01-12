package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.tree.orderedlist.Heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * 矩形重合问题
 */
public class RectangularCoincidence {

    /**
     * @param rectangular 矩形描述信息, int[0]为左下x, int[1]为左下y, int[2]为右上x, int[3]为右上y
     * @return 重合的矩形个数
     */
    public static int getMaximumCoincidence(List<int[]> rectangular) {
        if (rectangular == null || rectangular.isEmpty()) {
            return 0;
        }
        // 根据矩形的下边界进行升序排序
        rectangular.sort(Comparator.comparingInt(o -> o[1]));
        // 小根堆按照矩形上界组织
        Heap<int[]> smallRootHeap = new Heap<>(
                (o1, o2) -> o2[3] - o1[3]
        );
        int ans = 0;
        for (int[] rect : rectangular) {
            // 标记有没有计算过
            boolean flag = false;
            // 如果进来的矩形的下界 > 堆中最低的上界, 那么有矩形要出堆
            while (!smallRootHeap.isEmpty() && rect[1] >= smallRootHeap.peek()[3]) {
                // 如果有矩形要出堆, 那么根据当前堆中的已有矩形计算一次结果
                if (!flag) {
                    List<int[]> elements = smallRootHeap.getElements();
                    List<int[]> ropes = new ArrayList<>(elements.size());
                    for (int[] element : elements) {
                        ropes.add(new int[]{element[0], element[2]});
                    }
                    ans = Math.max(ans, RopeCover.getMaximumCovers(ropes));
                    // 标记已经计算过了
                    flag = true;
                }
                smallRootHeap.pop();
            }
            smallRootHeap.insert(rect);
        }
        List<int[]> elements = smallRootHeap.getElements();
        List<int[]> ropes = new ArrayList<>(elements.size());
        for (int[] element : elements) {
            ropes.add(new int[]{element[0], element[2]});
        }
        return Math.max(ans, RopeCover.getMaximumCovers(ropes));
    }

}
