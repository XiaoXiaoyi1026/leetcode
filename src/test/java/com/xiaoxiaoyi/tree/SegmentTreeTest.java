package com.xiaoxiaoyi.tree;

import com.xiaoxiaoyi.utils.RandomUtils;
import junit.framework.TestCase;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

public class SegmentTreeTest extends TestCase {

    static class TestTree {
        int[] arr;

        TestTree(@NotNull int[] arr) {
            this.arr = new int[arr.length];
            System.arraycopy(arr, 0, this.arr, 0, arr.length);
        }

        public void add(int from, int to, int value) {
            for (int i = from; i < to; i++) {
                arr[i] += value;
            }
        }

        public void update(int from, int to, int value) {
            for (int i = from; i < to; i++) {
                arr[i] = value;
            }
        }

        public int get(int from, int to) {
            int res = 0;
            for (int i = from; i < to; i++) {
                res += arr[i];
            }
            return res;
        }

        public int max(int from, int to) {
            int res = Integer.MIN_VALUE;
            for (int i = from; i < to; i++) {
                res = Math.max(res, arr[i]);
            }
            return res;
        }

        @Override
        public String toString() {
            return Arrays.toString(arr);
        }
    }

    public void testBuild() {
        int[] arr = RandomUtils.array(10, 100, true);
        SegmentTree tree = new SegmentTree(arr);
        tree.print();
    }

    public void testAddAndQuery() {
        int testTimes = 1000;
        int length = 10;
        int maxValue = 20;
        TestTree testTree;
        SegmentTree segmentTree;
        int testTreeQueryAnswer;
        int segmentTreeQueryAnswer;
        Random random = RandomUtils.getRandom();
        int[] arr;
        int from;
        int to;
        int value;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            arr = RandomUtils.array(length, maxValue);
            testTree = new TestTree(arr);
            segmentTree = new SegmentTree(arr);
            while (random.nextBoolean()) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                value = random.nextInt(maxValue);
                testTree.add(from, to, value);
                segmentTree.add(from, to, value);
            }
            while (random.nextFloat() < 0.8) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                testTreeQueryAnswer = testTree.get(from, to);
                segmentTreeQueryAnswer = segmentTree.get(from, to);
                if (testTreeQueryAnswer != segmentTreeQueryAnswer) {
                    System.out.println("testTreeQueryAnswer = " + testTreeQueryAnswer);
                    System.out.println("segmentTreeQueryAnswer = " + segmentTreeQueryAnswer);
                    System.out.println("arr = " + Arrays.toString(arr));
                    System.out.println("from = " + from);
                    System.out.println("to = " + to);
                    System.out.println("testTree = " + testTree);
                    System.out.println("segmentTree = " + segmentTree);
                    System.out.println("testTree.query(from, to) != segmentTree.query(from, to)");
                    break;
                }
            }
        }
        System.out.println("测试结束!!!");
    }

    public void testUpdateAndQuery() {
        int testTimes = 1000;
        int length = 10;
        int maxValue = 20;
        TestTree testTree;
        SegmentTree segmentTree;
        Random random = RandomUtils.getRandom();
        int[] arr;
        int from;
        int to;
        int value;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            arr = RandomUtils.array(length, maxValue);
            testTree = new TestTree(arr);
            segmentTree = new SegmentTree(arr);
            while (random.nextFloat() < 0.8) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                value = random.nextInt(maxValue);
                testTree.update(from, to, value);
                segmentTree.update(from, to, value);
            }
            while (random.nextFloat() < 0.8) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                assertEquals(testTree.get(from, to), segmentTree.get(from, to));
            }
        }
        System.out.println("测试结束!!!");
    }

    public void testAddAndUpdateAndQuery() {
        int testTimes = 1000;
        int length = 10;
        int maxValue = 20;
        int[] arr;
        TestTree testTree;
        SegmentTree segmentTree;
        int testTreeQueryAnswer;
        int segmentTreeQueryAnswer;
        int from;
        int to;
        int value;
        Random random = RandomUtils.getRandom();
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            arr = RandomUtils.array(length, maxValue);
            testTree = new TestTree(arr);
            segmentTree = new SegmentTree(arr);
            while (random.nextFloat() < 0.8) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                value = random.nextInt(maxValue);
                testTree.add(from, to, value);
                segmentTree.add(from, to, value);
            }
            while (random.nextFloat() < 0.8) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                value = random.nextInt(maxValue);
                testTree.update(from, to, value);
                segmentTree.update(from, to, value);
            }
            while (random.nextFloat() < 0.8) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                testTreeQueryAnswer = testTree.get(from, to);
                segmentTreeQueryAnswer = segmentTree.get(from, to);
                if (testTreeQueryAnswer != segmentTreeQueryAnswer) {
                    System.out.println("from = " + from);
                    System.out.println("to = " + to);
                    System.out.println("testTreeQueryAnswer = " + testTreeQueryAnswer);
                    System.out.println("segmentTreeQueryAnswer = " + segmentTreeQueryAnswer);
                    System.out.println("segmentTree: ");
                    for (int j = 0; j < arr.length; j++) {
                        System.out.print(segmentTree.get(j, j + 1) + " ");
                    }
                    System.out.println();
                    segmentTree.print();
                    System.out.println(Arrays.toString(testTree.arr));
                    System.out.println("测试未通过!!!");
                    break;
                }
            }
        }
        System.out.println("测试结束!!!");
    }

    public void testMax() {
        int testTimes = 1000;
        int length = 10;
        int maxValue = 20;
        TestTree testTree;
        SegmentTree segmentTree;
        Random random = RandomUtils.getRandom();
        int[] arr;
        int from;
        int to;
        int max1;
        int max2;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            arr = RandomUtils.array(length, maxValue);
            testTree = new TestTree(arr);
            segmentTree = new SegmentTree(arr);
            from = random.nextInt(length);
            to = from + random.nextInt(length - from);
            max1 = testTree.max(from, to);
            max2 = segmentTree.max(from, to);
            if (max1 != max2) {
                System.out.println("max1 = " + max1);
                System.out.println("max2 = " + max2);
                System.out.println("arr = " + Arrays.toString(arr));
                System.out.println("from = " + from);
                System.out.println("to = " + to);
                System.out.println("testTree = " + testTree);
                System.out.println("segmentTree = " + segmentTree);
                break;
            }
        }
        System.out.println("测试结束!!!");
    }

    public void testAddAndMax() {
        int testTimes = 10000;
        int length = 100;
        int maxValue = 200;
        TestTree testTree;
        SegmentTree segmentTree;
        Random random = RandomUtils.getRandom();
        int[] arr;
        int from;
        int to;
        int value;
        int max1;
        int max2;
        boolean testFailed = false;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            arr = RandomUtils.array(length, maxValue);
            testTree = new TestTree(arr);
            segmentTree = new SegmentTree(arr);
            while (random.nextFloat() < 0.8) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                value = random.nextInt(maxValue);
                testTree.add(from, to, value);
                segmentTree.add(from, to, value);
            }
            while (random.nextFloat() < 0.8) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                max1 = testTree.max(from, to);
                max2 = segmentTree.max(from, to);
                if (max1 != max2) {
                    System.out.println("max1 = " + max1);
                    System.out.println("max2 = " + max2);
                    System.out.println("arr = " + Arrays.toString(arr));
                    System.out.println("from = " + from);
                    System.out.println("to = " + to);
                    System.out.println("testTree = " + testTree);
                    System.out.println("segmentTree = " + segmentTree);
                    testFailed = true;
                }
            }
            if (testFailed) {
                break;
            }
        }
        System.out.println("测试结束!!!");
    }

    public void testUpdateAndMax() {
        int testTimes = 10000;
        int length = 100;
        int maxValue = 200;
        TestTree testTree;
        SegmentTree segmentTree;
        Random random = RandomUtils.getRandom();
        int[] arr;
        int from;
        int to;
        int value;
        int max1;
        int max2;
        boolean testFailed = false;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            arr = RandomUtils.array(length, maxValue);
            testTree = new TestTree(arr);
            segmentTree = new SegmentTree(arr);
            while (random.nextFloat() < 0.8) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                value = random.nextInt(maxValue);
                testTree.update(from, to, value);
                segmentTree.update(from, to, value);
            }
            while (random.nextFloat() < 0.8) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                max1 = testTree.max(from, to);
                max2 = segmentTree.max(from, to);
                if (max1 != max2) {
                    System.out.println("max1 = " + max1);
                    System.out.println("max2 = " + max2);
                    System.out.println("arr = " + Arrays.toString(arr));
                    System.out.println("from = " + from);
                    System.out.println("to = " + to);
                    System.out.println("testTree = " + testTree);
                    System.out.println("segmentTree = " + segmentTree);
                    testFailed = true;
                }
            }
            if (testFailed) {
                break;
            }
        }
        System.out.println("测试结束!!!");
    }
}
