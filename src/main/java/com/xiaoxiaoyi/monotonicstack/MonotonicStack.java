package com.xiaoxiaoyi.monotonicstack;

import java.util.*;

/**
 * @author xiaoxiaoyi
 * 单调栈, 默认为降序
 */
public class MonotonicStack<T> {

    /**
     * 双端队列，存储元素下标
     */
    private final Deque<Element> deque;
    /**
     * 原对象数组
     */
    private final List<T> objects;
    private int left;
    private int right;
    /**
     * 对象的比较器
     */
    private final Comparator<T> comparator;

    public static class Element {
        /**
         * 元素的下标
         */
        Integer val;
        /**
         * 指向下一个的指针
         */
        Element next;

        Integer pre;
        Integer end;

        Element(Integer val) {
            this.val = val;
            this.next = null;
            this.pre = 0;
            this.end = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Element)) {
                return false;
            }

            Element element = (Element) o;

            return Objects.equals(val, element.val);
        }

        @Override
        public int hashCode() {
            return val != null ? val.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "val=" + val +
                    '}';
        }
    }

    MonotonicStack(List<T> objects, Comparator<T> comparator) {
        // 初始化双端队列
        deque = new LinkedList<>();
        this.objects = objects;
        left = -1;
        right = 0;
        this.comparator = comparator;
    }

    /**
     * 获得双端队列的头部节点
     *
     * @return 头部节点(下标值)
     */
    public Element getFirst() {
        if (deque.isEmpty()) {
            return null;
        }
        return deque.peekFirst();
    }

    /**
     * 获得双端队列的尾部节点
     *
     * @return 尾部节点(下标值)
     */
    public Element getLast() {
        if (deque.isEmpty()) {
            return null;
        }
        return deque.peekLast();
    }

    /**
     * 右边界右移一个位置
     *
     * @return 出队的元素列表
     */
    public List<Element> increaseRight() {
        List<Element> elements = null;
        if (right < objects.size()) {
            // 如果right右移后未出界
            T curObject = objects.get(right);
            // 创建当前元素
            Element curElement = new Element(right++);
            while (!deque.isEmpty()) {
                // 获取队尾节点
                T lastObject = objects.get(this.getLast().val);
                if (comparator.compare(curObject, lastObject) > 0) {
                    // 如果当前元素 > 队尾元素，则队尾元素出队
                    if (elements == null) {
                        elements = new ArrayList<>();
                    }
                    Element element = deque.pollLast();
                    assert element != null;
                    element.pre = deque.isEmpty() ? 0 : this.getLast().val + 1;
                    element.end = right - 1;
                    elements.add(element);
                } else if (curObject.equals(lastObject)) {
                    // 最后一个弹出
                    // 当前元素变为头节点
                    curElement.next = deque.pollLast();
                } else {
                    // 当前元素 < 队尾元素则直接退出循环
                    break;
                }
            }
            // 当前元素入队
            deque.addLast(curElement);
        }
        // 返回出队的所有元素集合
        return elements;
    }

    /**
     * 左边界右移一个位置
     *
     * @return 出队的元素
     */
    public Element increaseLeft() {
        if (left < 0 || deque.isEmpty()) {
            left++;
            return null;
        }
        Element res = null;
        // 保证left右移一位后 < right
        if (left + 1 < right) {
            // 获取队首元素
            T dequeHeadObject = objects.get(this.getFirst().val);
            // 获取当前出窗口的元素
            T curObject = objects.get(left++);
            if (curObject.equals(dequeHeadObject)) {
                // 如果当前元素恰好等于队顶元素，则队列弹出队顶
                res = deque.pollFirst();
            }
            // 当前元素不是头元素则不管
        }
        // 返回出队的元素
        return res;
    }

    public Deque<Element> getDeque() {
        return deque;
    }
}
