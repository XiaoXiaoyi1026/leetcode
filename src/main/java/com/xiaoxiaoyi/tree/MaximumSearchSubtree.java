package com.xiaoxiaoyi.tree;

import java.util.Stack;

/**
 * @author xiaoxiaoyi
 * 给定一颗二叉树(其中没有相同节点), 问以这棵树的最大二叉搜索树的节点个数有多少个
 * 搜索二叉子树只要求连通即可
 */
public class MaximumSearchSubtree {

    public static class Node<T extends Comparable<T>> extends ElementBinaryTree.Node<T> {

        /**
         * 我的左子树向负责的节点贡献了多少
         */
        public int leftContribution;
        /**
         * 我的右子树向负责的节点贡献了多少
         */
        public int rightContribution;

        public Node(T element) {
            super(element);
            leftContribution = rightContribution = 0;
        }

        @Override
        public Node<T> getLeft() {
            return (Node<T>) super.getLeft();
        }

        @Override
        public Node<T> getRight() {
            return (Node<T>) super.getRight();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "leftContribution=" + leftContribution +
                    ", rightContribution=" + rightContribution +
                    ", element=" + element +
                    '}';
        }
    }

    public static int get(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        return GenerateTopologyContributionRecords(node);
    }

    public static void printContribution(Node<Integer> node) {
        GenerateTopologyContributionRecords(node);
        print(node);
    }

    private static void print(Node<Integer> node) {
        if (node != null) {
            System.out.println(node);
            print(node.getLeft());
            print(node.getRight());
        }
    }

    /**
     * 生成对节点node负责的拓扑贡献记录
     * 算法复杂度保持在O(N)
     */
    private static int GenerateTopologyContributionRecords(Node<Integer> node) {
        if (node == null) {
            // 如果node为空节点
            return 0;
        }
        // 得到左子树上对左孩子负责的信息, res维持树上的最大连通搜索节点个数
        int res = GenerateTopologyContributionRecords(node.getLeft());
        // 得到右子树上对右孩子负责的信息
        res = Math.max(res, GenerateTopologyContributionRecords(node.getRight()));
        // 将左右子树上的信息更新成对我负责的, cur一开始指向node的左孩子
        Node<Integer> cur = node.getLeft();
        // 保证左孩子不为空且左孩子对node存在贡献
        if (cur != null && cur.element < node.element) {
            // 辅助栈
            Stack<Node<Integer>> help = new Stack<>();
            help.push(cur);
            cur = cur.getRight();
            // cur还能往右走则往右走, O(N)
            while (cur != null && help.peek().rightContribution != 0) {
                if (cur.element > node.element) {
                    // 如果发现了某个节点的element > node的element, 则切断与其的联系(向上每一个节点的右贡献都减掉该节点的总贡献)
                    int contribution = help.peek().rightContribution;
                    while (!help.isEmpty()) {
                        // 向上每一个节点的右贡献都减掉该节点的总贡献
                        help.pop().rightContribution -= contribution;
                    }
                    break;
                }
                help.push(cur);
                // 沿着右孩子向下遍历
                cur = cur.getRight();
            }
            cur = node.getLeft();
            // 更新对自己负责的左贡献
            node.leftContribution = cur.leftContribution + cur.rightContribution + 1;
        }
        // 去右边重复一样的操作
        cur = node.getRight();
        // 保证右孩子不为空且右孩子对node有贡献
        if (cur != null && cur.element > node.element) {
            // 辅助栈
            Stack<Node<Integer>> help = new Stack<>();
            help.push(cur);
            cur = cur.getLeft();
            // 当cur还可以往左走则往左走, O(N)
            while (cur != null && help.peek().leftContribution != 0) {
                if (cur.element < node.element) {
                    // 如果发现某个节点的值小于node, 那么其上的所有节点的左贡献应该减去当前节点的总贡献
                    int contribution = help.peek().leftContribution;
                    while (!help.isEmpty()) {
                        // 其上的所有节点的左贡献减去当前节点的总贡献
                        help.pop().leftContribution -= contribution;
                    }
                    break;
                }
                help.push(cur);
                cur = cur.getLeft();
            }
            cur = node.getRight();
            // 更新对自己负责的右贡献
            node.rightContribution = cur.leftContribution + cur.rightContribution + 1;
        }
        res = Math.max(res, node.leftContribution + node.rightContribution + 1);
        return res;
    }

}
