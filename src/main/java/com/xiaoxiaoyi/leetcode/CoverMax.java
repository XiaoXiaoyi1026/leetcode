package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.heap.Heap;
import com.xiaoxiaoyi.utils.RandomUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一些线段segments
 * segments[i]代表第i条线段的范围是从[segments[i][0], segments[i][1]]的
 * 其中最多有多少条存在重合部分的线段
 * [1, 4]和[3, 6] 重合
 * [1, 4]和[4, 8] 不重合
 */
public class CoverMax {

    public static int coverMax1(int[][] segments) {
        if (segments == null || segments.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int[] segment : segments) {
            min = Math.min(min, segment[0]);
            max = Math.max(max, segment[1]);
        }
        int ans = 1;
        int tmp;
        for (double i = (min + 0.5); i < max; i++) {
            tmp = 0;
            for (int[] segment : segments) {
                if (segment[0] < i && i < segment[1]) {
                    tmp++;
                }
            }
            ans = Math.max(ans, tmp);
        }
        return ans;
    }

    public static int coverMax2(int[][] segments) {
        if (segments == null || segments.length == 0) {
            return 0;
        }
        // 按照线段起始位置升序排序
        Arrays.sort(segments, Comparator.comparingInt(s -> s[0]));
        int ans = 0;
        Heap<Integer> helpHeap = new Heap<>(segments.length);
        // O(NlogN), 每个线段的结尾只会进一次和出一次堆
        for (int[] segment : segments) {
            while (!helpHeap.isEmpty() && helpHeap.peek() <= segment[0]) {
                helpHeap.poll();
            }
            // O(logN)
            helpHeap.insert(segment[1]);
            ans = Math.max(ans, helpHeap.size());
        }
        return ans;
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int segmentMaxLength = 100;
        int maxSegmentNumber = 100;
        int segmentStart;
        int segmentEnd;
        int[] segment;
        int[][] segments;
        int segmentsLength;
        int ans1;
        int ans2;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            segmentsLength = 1 + RandomUtils.nextInt(maxSegmentNumber);
            segments = new int[segmentsLength][2];
            for (int j = 0; j < segmentsLength; j++) {
                segmentStart = RandomUtils.nextInt(segmentMaxLength);
                segmentEnd = segmentStart + RandomUtils.nextInt(segmentMaxLength) + 1;
                segment = new int[]{segmentStart, segmentEnd};
                segments[j] = segment;
            }
            ans1 = coverMax1(segments);
            ans2 = coverMax2(segments);
            if (ans1 != ans2) {
                System.out.println("准备数据：" + Arrays.deepToString(segments));
                System.out.println("ans1: " + ans1 + ", ans2: " + ans2);
                break;
            }
        }
        System.out.println("测试结束!!!");
    }

}
