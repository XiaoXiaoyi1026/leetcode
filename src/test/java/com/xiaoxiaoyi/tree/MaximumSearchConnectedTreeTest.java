package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

import java.util.*;

public class MaximumSearchConnectedTreeTest extends TestCase {

    public MaximumSearchConnectedTree.Node<Integer> generateRandomTree(int size, int max) {
        if (max < size) {
            throw new RuntimeException("Invalid params!");
        }
        if (size > 0) {
            List<MaximumSearchConnectedTree.Node<Integer>> nodes = new ArrayList<>(size);
            while (nodes.size() < size) {
                MaximumSearchConnectedTree.Node<Integer> node;
                do {
                    node = new MaximumSearchConnectedTree.Node<>(
                            (int) (Math.random() * max)
                    );
                } while (nodes.contains(node));
                nodes.add(node);
            }
            for (int i = 0; i < size; i++) {
                MaximumSearchConnectedTree.Node<Integer> father = nodes.get(i);
                MaximumSearchConnectedTree.Node<Integer> leftChild = (i << 1) + 1 >= size ? null : nodes.get((i << 1) + 1);
                MaximumSearchConnectedTree.Node<Integer> rightChild = (i + 1) << 1 >= size ? null : nodes.get((i + 1) << 1);
                setChildes(father, leftChild, rightChild);
            }
            return nodes.get(0);
        }
        return null;
    }

    public void testGenerateRandomTree() {
        int size = (int) (1 + Math.random() * 20);
        int max = 50;
        ElementBinaryTree.print(generateRandomTree(size, max));
    }

    public int normal(MaximumSearchConnectedTree.Node<Integer> node) {
        Stack<MaximumSearchConnectedTree.Node<Integer>> help = new Stack<>();
        MaximumSearchConnectedTree.Node<Integer> cur = node;
        int res = 0;
        while (cur != null) {
            help.push(cur);
            res = Math.max(res, getProcess(cur));
            cur = cur.getLeft();
        }
        while (!help.isEmpty()) {
            cur = help.pop().getRight();
            while (cur != null) {
                help.push(cur);
                res = Math.max(res, getProcess(cur));
                cur = cur.getLeft();
            }
        }
        return res;
    }

    public int getProcess(MaximumSearchConnectedTree.Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        // 开始求假设以node为头的解
        int res = 0;
        Stack<MaximumSearchConnectedTree.Node<Integer>> help = new Stack<>();
        MaximumSearchConnectedTree.Node<Integer> cur = node;
        while (cur != null && search(node, cur)) {
            help.push(cur);
            res++;
            cur = cur.getLeft();
        }
        while (!help.isEmpty()) {
            cur = help.pop().getRight();
            while (cur != null && search(node, cur)) {
                help.push(cur);
                res++;
                cur = cur.getLeft();
            }
        }
        return res;
    }

    public boolean search(MaximumSearchConnectedTree.Node<Integer> cur, MaximumSearchConnectedTree.Node<Integer> target) {
        boolean res = false;
        while (cur != null) {
            if (cur.equals(target)) {
                res = true;
                break;
            } else if (target.element < cur.element) {
                cur = cur.getLeft();
            } else {
                cur = cur.getRight();
            }
        }
        return res;
    }

    public void setChildes(MaximumSearchConnectedTree.Node<Integer> father,
                           MaximumSearchConnectedTree.Node<Integer> left,
                           MaximumSearchConnectedTree.Node<Integer> right) {
        father.left = left;
        father.right = right;
    }

    public void testGet() {
        int size = (int) (1 + Math.random() * 20);
        int max = 50;
        MaximumSearchConnectedTree.Node<Integer> node = generateRandomTree(size, max);
        ElementBinaryTree.print(node);
        System.out.println(MaximumSearchConnectedTree.get(node));
    }

    public void testGetContribution() {
        int size = (int) (1 + Math.random() * 20);
        int max = 50;
        MaximumSearchConnectedTree.Node<Integer> node = generateRandomTree(size, max);
        MaximumSearchConnectedTree.printContribution(node);
    }

    public void testNormal() {
        int size = (int) (1 + Math.random() * 20);
        int max = 50;
        MaximumSearchConnectedTree.Node<Integer> node = generateRandomTree(size, max);
        ElementBinaryTree.print(node);
        System.out.println(normal(node));
    }

    public void testAll() {
        int testTimes = 100;
        for (int i = 0; i < testTimes; i++) {
            int size = (int) (1 + Math.random() * 20);
            int max = 50;
            MaximumSearchConnectedTree.Node<Integer> node = generateRandomTree(size, max);
            int ans1 = normal(node);
            int ans2 = MaximumSearchConnectedTree.get(node);
            if (ans1 != ans2) {
                System.out.println("ooops!");
                ElementBinaryTree.print(node);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                break;
            }
        }
    }
}