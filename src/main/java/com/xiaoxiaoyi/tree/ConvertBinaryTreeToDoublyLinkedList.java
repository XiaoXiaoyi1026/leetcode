package com.xiaoxiaoyi.tree;

/**
 * @author xiaoxiaoyi
 */
public class ConvertBinaryTreeToDoublyLinkedList {

    /**
     * 向左右两个子树要的信息
     */
    public static class Info {
        // 节点里存整数, 代表子树转换为双链表后的头结点
        public BinarySearchTree.Node<Integer> start;
        // 代表子树转换为双链表后的尾结点
        public BinarySearchTree.Node<Integer> end;

        public Info(BinarySearchTree.Node<Integer> start, BinarySearchTree.Node<Integer> end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 将二叉搜索树转换为双链表
     */
    public static BinarySearchTree.Node<Integer> convert(BinarySearchTree<Integer> binarySearchTree) {
        return convert(binarySearchTree.getRoot());
    }

    /**
     * 将以head开头的二叉搜索树转换为双链表
     */
    public static BinarySearchTree.Node<Integer> convert(BinarySearchTree.Node<Integer> head) {
        // 转换后的start即为整个双链表的头结点
        return convertProcess(head).start;
    }

    /**
     * 转换的过程
     */
    public static Info convertProcess(BinarySearchTree.Node<Integer> head) {
        if (head == null) {
            // base case 递归终止条件, 遍历到null节点直接返回
            return new Info(null, null);
        }

        // 一般节点的情况, 向左右子树要信息
        Info leftInfo = convertProcess(head.getLeft());
        Info rightInfo = convertProcess(head.getRight());

        // 左子树转换后的尾结点就是双链表中当前结点的前趋结点
        if (leftInfo.end != null) {
            // 因为空节点返回的start和end都是null, 所以需要判空
            leftInfo.end.right = head;
        }
        head.left = leftInfo.end;

        // 右子树转换后的头结点就是双链表中当前结点的后继结点
        head.right = rightInfo.start;
        // 因为空节点返回的start和end都是null, 所以需要判空
        if (rightInfo.start != null) {
            rightInfo.start.left = head;
        }

        /*
        当前节点为根的子树转换为双链表后的头结点应该是左边的头结点, 尾结点应该为右边的尾结点
        边界判断: 如果过左子树没有头结点, 那么转换后的头结点就是head自身, 右子树同理
         */
        return new Info(leftInfo.start == null ? head : leftInfo.start,
                rightInfo.end == null ? head : rightInfo.end);
    }

    public static void printDoublyLinkedList(BinarySearchTree.Node<Integer> head) {
        System.out.print(head.element);
        if (head.right != null) {
            System.out.print(" -> ");
            printDoublyLinkedList(head.getRight());
        } else {
            System.out.println();
            while (head.left != null) {
                System.out.print(head.element + " <- ");
                head = head.getLeft();
            }
            System.out.println(head.element);
        }
    }

}
