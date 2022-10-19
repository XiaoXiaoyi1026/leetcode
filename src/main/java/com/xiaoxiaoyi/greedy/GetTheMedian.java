package com.xiaoxiaoyi.greedy;

import java.util.PriorityQueue;

/**
 * @author xiaoxiaoyi
 * 在不断往字符串中加入数字的过程中，始终能以最快的速度获取到数组的中位数
 */
public class GetTheMedian {

    /**
     * 存放数组中较小的一半，大根堆
     * 存放数组较大的一半，小根堆
     */
    PriorityQueue<Integer> smallerHalf, largerHalf;

    /**
     * 初始化
     */
    GetTheMedian() {
        smallerHalf = new PriorityQueue<>((o1, o2) -> o2 - o1);
        largerHalf = new PriorityQueue<>();
    }

    /**
     * 获取中位数
     *
     * @return 中位数
     */
    public double getTheMedian() {
        int smallSize = smallerHalf.size();
        int largerSize = largerHalf.size();
        if (smallSize == 0 && largerSize == 0) {
            return 0.0;
        } else if (smallSize == 0) {
            return largerHalf.peek();
        } else if (largerSize == 0) {
            return smallerHalf.peek();
        } else {
            if (((smallSize + largerSize) & 1) == 0) {
                // 总长度为偶数
                return (smallerHalf.peek() + largerHalf.peek()) / 2.0;
            } else {
                // 总长度为奇数，判断中位数在哪边
                return smallSize > largerSize ? smallerHalf.peek() : largerHalf.peek();
            }
        }
    }

    /**
     * 往数组中加入一个数字
     *
     * @param number 数字
     */
    public GetTheMedian addNumber(int number) {
        if (smallerHalf.size() == 0 || number <= smallerHalf.peek()) {
            // 将加入的数字同较小部分的大根堆堆顶进行比较
            smallerHalf.add(number);
        } else {
            // 否则加入较大部分的小根堆
            largerHalf.add(number);
        }
        // 判断两个堆的长度差是否 > 1
        if (Math.abs(smallerHalf.size() - largerHalf.size()) > 1) {
            // 如果长度差大于1则进行调整，将较大长度的堆顶元素加入较小长度的堆
            if (smallerHalf.size() > largerHalf.size()) {
                largerHalf.add(smallerHalf.poll());
            } else {
                smallerHalf.add(largerHalf.poll());
            }
        }
        return this;
    }

}
