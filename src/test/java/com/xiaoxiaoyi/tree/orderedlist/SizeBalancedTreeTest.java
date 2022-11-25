package com.xiaoxiaoyi.tree.orderedlist;

import junit.framework.TestCase;

public class SizeBalancedTreeTest extends TestCase {

    private SizeBalancedTree<String, Integer> sizeBalancedTree;

    private void createTree() {
        sizeBalancedTree = new SizeBalancedTree<>();
        sizeBalancedTree.put("d", 4);
        sizeBalancedTree.put("c", 3);
        sizeBalancedTree.put("a", 1);
        sizeBalancedTree.put("b", 2);
        // sizeBalancedTree.put("e", 5);
        sizeBalancedTree.put("g", 7);
        sizeBalancedTree.put("f", 6);
        sizeBalancedTree.put("h", 8);
        sizeBalancedTree.put("i", 9);
    }

    public void printAll(SizeBalancedTree.SizeBalancedTreeNode<String, Integer> head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(SizeBalancedTree.SizeBalancedTreeNode<String, Integer> head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + "(" + head.key + "," + head.value + ")" + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public void testGetAndPut() {
        createTree();
        sizeBalancedTree.put("a", 111);
        System.out.println(sizeBalancedTree.get("a"));
        sizeBalancedTree.put("a", 1);
        System.out.println(sizeBalancedTree.get("a"));
    }

    public void testPrintTreeNodes() {
        createTree();
        for (int i = 0; i < sizeBalancedTree.size(); i++) {
            System.out.println(sizeBalancedTree.getIndexKey(i) + " , " + sizeBalancedTree.getIndexValue(i));
        }
    }

    public void testPrintAll() {
        createTree();
        printAll(sizeBalancedTree.getRoot());
    }

    public void testAboutKey() {
        createTree();
        System.out.println(sizeBalancedTree.firstKey());
        System.out.println(sizeBalancedTree.lastKey());
        System.out.println(sizeBalancedTree.floorKey("g"));
        System.out.println(sizeBalancedTree.ceilingKey("g"));
        System.out.println(sizeBalancedTree.floorKey("e"));
        System.out.println(sizeBalancedTree.ceilingKey("e"));
        System.out.println(sizeBalancedTree.floorKey(""));
        System.out.println(sizeBalancedTree.ceilingKey(""));
        System.out.println(sizeBalancedTree.floorKey("j"));
        System.out.println(sizeBalancedTree.ceilingKey("j"));
    }

    public void testRemove() {
        createTree();
        sizeBalancedTree.remove("d");
        printAll(sizeBalancedTree.getRoot());
        sizeBalancedTree.remove("f");
        printAll(sizeBalancedTree.getRoot());
    }

}
