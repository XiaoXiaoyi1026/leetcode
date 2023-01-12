package com.xiaoxiaoyi.monotonicstack;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MonotonicStackTest extends TestCase {
    private static class Element implements Comparable<Element> {
        int val;

        Element(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.valueOf(this.val);
        }

        @Override
        public int compareTo(Element element) {
            return this.val - element.val;
        }
    }

    public void testMonotonicStack() {

        List<Element> input = new ArrayList<>();
        input.add(new Element(5));
        input.add(new Element(4));
        input.add(new Element(3));
        input.add(new Element(4));
        input.add(new Element(5));
        input.add(new Element(3));
        input.add(new Element(5));
        input.add(new Element(6));
        MonotonicStack<Element> monotonicStack = new MonotonicStack<>(input, new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return o2.val - o1.val;
            }

            @Override
            public boolean equals(Object obj) {
                return this == obj;
            }
        });
        // 窗口大小为3
        System.out.println("increaseRight: " + monotonicStack.increaseRight());
        System.out.println("increaseRight: " + monotonicStack.increaseRight());
        System.out.println("increaseRight: " + monotonicStack.increaseRight());
        System.out.println("increaseLeft: " + monotonicStack.increaseLeft());
        // 获取队首节点
        System.out.println("getFirst: " + input.get(monotonicStack.getFirst().val));
        // 左右同时右移
        System.out.println("increaseLeft: " + monotonicStack.increaseLeft());
        System.out.println("increaseRight: " + monotonicStack.increaseRight());
        // 获取队首
        System.out.println("getFirst: " + input.get(monotonicStack.getFirst().val));
        System.out.println("increaseLeft: " + monotonicStack.increaseLeft());
        System.out.println("increaseRight: " + monotonicStack.increaseRight());
        System.out.println("getFirst: " + input.get(monotonicStack.getFirst().val));
        System.out.println("increaseLeft: " + monotonicStack.increaseLeft());
        System.out.println("increaseRight: " + monotonicStack.increaseRight());
        System.out.println("getFirst: " + input.get(monotonicStack.getFirst().val));
        System.out.println("increaseLeft: " + monotonicStack.increaseLeft());
        System.out.println("increaseRight: " + monotonicStack.increaseRight());
        System.out.println("getFirst: " + input.get(monotonicStack.getFirst().val));
        System.out.println("increaseLeft: " + monotonicStack.increaseLeft());
        System.out.println("increaseRight: " + monotonicStack.increaseRight());
        System.out.println("getFirst: " + input.get(monotonicStack.getFirst().val));
    }

}
