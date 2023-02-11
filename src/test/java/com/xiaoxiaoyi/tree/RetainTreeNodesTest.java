package com.xiaoxiaoyi.tree;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 保留树节点测试
 * @date 2/11/2023 3:16 PM
 */
public class RetainTreeNodesTest extends TestCase {

    public RetainTreeNodes.Node generate() {
        RetainTreeNodes.Node node1 = new RetainTreeNodes.Node(1, false, new ArrayList<>());
        RetainTreeNodes.Node node2 = new RetainTreeNodes.Node(2, false, new ArrayList<>());
        RetainTreeNodes.Node node3 = new RetainTreeNodes.Node(3, false, new ArrayList<>());
        RetainTreeNodes.Node node4 = new RetainTreeNodes.Node(4, false, new ArrayList<>());
        RetainTreeNodes.Node node5 = new RetainTreeNodes.Node(5, false, new ArrayList<>());
        RetainTreeNodes.Node node6 = new RetainTreeNodes.Node(6, true, new ArrayList<>());
        RetainTreeNodes.Node node7 = new RetainTreeNodes.Node(7, true, new ArrayList<>());
        List<RetainTreeNodes.Node> node1NextNodes = node1.getNextNodes();
        List<RetainTreeNodes.Node> node2NextNodes = node2.getNextNodes();
        List<RetainTreeNodes.Node> node3NextNodes = node3.getNextNodes();
        node1NextNodes.add(node2);
        node1NextNodes.add(node3);
        node1NextNodes.add(node4);
        node2NextNodes.add(node7);
        node3NextNodes.add(node5);
        node3NextNodes.add(node6);
        return node1;
    }

    public void testRetain() {
        RetainTreeNodes.Node node = generate();
        RetainTreeNodes.retain(node);
        // 深度优先遍历
        System.out.println(node.getValue());
        List<RetainTreeNodes.Node> nextNodes = node.getNextNodes();
        Stack<RetainTreeNodes.Node> stack = new Stack<>();
        while (nextNodes != null && !nextNodes.isEmpty()) {
            stack.push(node);
            node = nextNodes.get(0);
            System.out.println(node.getValue());
            nextNodes.remove(0);
            nextNodes = node.getNextNodes();
        }
        while (!stack.isEmpty()) {
            node = stack.pop();
            nextNodes = node.getNextNodes();
            while (nextNodes != null && !nextNodes.isEmpty()) {
                node = nextNodes.get(0);
                System.out.println(node.getValue());
                nextNodes.remove(0);
                nextNodes = node.getNextNodes();
            }
        }
    }

}
