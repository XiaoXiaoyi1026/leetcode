package com.xiaoxiaoyi.tree;

import com.xiaoxiaoyi.utils.RandomGenerate;
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

        public int query(int from, int to) {
            int res = 0;
            for (int i = from; i < to; i++) {
                res += arr[i];
            }
            return res;
        }
    }

    public void testBuild() {
        int[] arr = RandomGenerate.array(10, 100, true);
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
        Random random = RandomGenerate.getRandom();
        int[] arr;
        int from;
        int to;
        int value;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            arr = RandomGenerate.array(length, maxValue);
            testTree = new TestTree(arr);
            segmentTree = new SegmentTree(arr);
            while (random.nextBoolean()) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                value = random.nextInt(maxValue);
                testTree.add(from, to, value);
                segmentTree.add(from, to, value);
            }
            from = random.nextInt(length);
            to = from + random.nextInt(length - from);
            testTreeQueryAnswer = testTree.query(from, to);
            segmentTreeQueryAnswer = segmentTree.query(from, to);
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
        System.out.println("测试结束!!!");
    }

    public void testUpdateAndQuery() {
        int testTimes = 1000;
        int length = 10;
        int maxValue = 20;
        TestTree testTree;
        SegmentTree segmentTree;
        Random random = RandomGenerate.getRandom();
        int[] arr;
        int from;
        int to;
        int value;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            arr = RandomGenerate.array(length, maxValue);
            testTree = new TestTree(arr);
            segmentTree = new SegmentTree(arr);
            while (random.nextBoolean()) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                value = random.nextInt(maxValue);
                testTree.update(from, to, value);
                segmentTree.update(from, to, value);
            }
            from = random.nextInt(length);
            to = from + random.nextInt(length - from);
            assertEquals(testTree.query(from, to), segmentTree.query(from, to));
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
        Random random = RandomGenerate.getRandom();
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            System.out.println("测试数据组：" + (i + 1));
            arr = RandomGenerate.array(length, maxValue);
            testTree = new TestTree(arr);
            segmentTree = new SegmentTree(arr);
            System.out.println("arr = " + Arrays.toString(testTree.arr));
            while (random.nextBoolean()) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                value = random.nextInt(maxValue);
                testTree.add(from, to, value);
                segmentTree.add(from, to, value);
                System.out.println("[" + from + ", " + to + ") add " + value);
                System.out.println("arr = " + Arrays.toString(testTree.arr));
                segmentTree.print();
            }
            while (random.nextBoolean()) {
                from = random.nextInt(length);
                to = from + random.nextInt(length - from);
                value = random.nextInt(maxValue);
                testTree.update(from, to, value);
                segmentTree.update(from, to, value);
                System.out.println("[" + from + ", " + to + ") update " + value);
                System.out.println("arr = " + Arrays.toString(testTree.arr));
                segmentTree.print();
            }
            from = random.nextInt(length);
            to = from + random.nextInt(length - from);
            testTreeQueryAnswer = testTree.query(from, to);
            segmentTreeQueryAnswer = segmentTree.query(from, to);
            if (testTreeQueryAnswer != segmentTreeQueryAnswer) {
                System.out.println("from = " + from);
                System.out.println("to = " + to);
                System.out.println("testTreeQueryAnswer = " + testTreeQueryAnswer);
                System.out.println("segmentTreeQueryAnswer = " + segmentTree.query(from, to));
                System.out.println("segmentTree: ");
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(segmentTree.query(j, j + 1) + " ");
                }
                System.out.println();
                segmentTree.print();
                System.out.println(Arrays.toString(testTree.arr));
                System.out.println("测试未通过!!!");
                break;
            }
        }
        System.out.println("测试结束!!!");
    }

    public void testUpdate2() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        SegmentTree segmentTree = new SegmentTree(arr);
        segmentTree.update(0, 0, 100);
        segmentTree.print();
    }

}
