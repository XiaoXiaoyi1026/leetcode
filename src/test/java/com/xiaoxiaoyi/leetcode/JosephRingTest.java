package com.xiaoxiaoyi.leetcode;

import junit.framework.TestCase;

public class JosephRingTest extends TestCase {

    public void testJosephRing() {
        JosephRing.Node node1 = new JosephRing.Node(1);
        JosephRing.Node node2 = new JosephRing.Node(2);
        JosephRing.Node node3 = new JosephRing.Node(3);
        JosephRing.Node node4 = new JosephRing.Node(4);
        JosephRing.Node node5 = new JosephRing.Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node1;
        System.out.println(JosephRing.getLive(node1, 3));
    }

    public void testGetAdmissionId() {
        int n = 10;
        int[] arr = {7, 4, 5};
        System.out.println(JosephRing.getAdmissionId(n, arr));
    }

}
