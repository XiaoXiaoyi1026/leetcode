package com.xiaoxiaoyi.monotonicstack;

import java.util.Comparator;
import java.util.Deque;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * 子数组的指标问题, 例如求数组包含且以每一个元素为子数组的最小值时, 子数组的累加和
 */
public class SubarrayIndicator {

    public static int[] subarrayIndicator(List<Integer> input) {
        MonotonicStack<Integer> monotonicStack = new MonotonicStack<>(input, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }

            @Override
            public boolean equals(Object obj) {
                return this == obj;
            }
        });
        int[] res = new int[input.size()];
        for (int i = 0; i < input.size(); i++) {
            // 记录当前元素入队时出的元素
            List<MonotonicStack.Element> elements = monotonicStack.increaseRight();
            if (elements != null) {
                for (MonotonicStack.Element element : elements) {
                    int sum = 0;
                    for (int j = element.pre; j < element.end; j++) {
                        sum += input.get(j);
                    }
                    while (element != null) {
                        res[element.val] = sum;
                        element = element.next;
                    }
                }
            }
        }
        Deque<MonotonicStack.Element> deque = monotonicStack.getDeque();
        while (!deque.isEmpty()) {
            MonotonicStack.Element last = deque.pollLast();
            int pre = deque.isEmpty() ? 0 : deque.peekLast().val + 1, sum = 0;
            for (int i = pre; i < input.size(); i++) {
                sum += input.get(i);
            }
            while (last != null) {
                res[last.val] = sum;
                last = last.next;
            }
        }
        return res;
    }
}
