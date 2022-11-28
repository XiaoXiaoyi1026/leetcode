package com.xiaoxiaoyi.tree;

/**
 * @author xiaoxiaoyi
 * Mirros遍历 时间复杂度O(n) 空间复杂度O(1) 利用二叉树叶节点的大量null指针进行回溯, 节省空间(线索树)
 * 申请一个cur指针指向当前遍历到的节点
 * 一个mostRight指针指向以当前节点为根的左子树上的底层最右边的节点
 * 1. 当前节点cur没有左子树, 则进入其右子树: cur = cur.right(返回根节点)
 * 2. 当前节点cur有左子树, 则先得到其mostRight节点
 * 1) 当mostRight指向null, 说明当前节点cur是第1次被遍历到, 令mostRight.right = cur, cur = cur.left
 * 2) 当mostRight不为null, 说明当前节点cur是第2次被遍历到, 令mostRight.right = null(还原), cur = cur.right
 * 3. 当cur为null停止遍历
 * 遍历特性: 每一个拥有左子树的节点都会经过2次, 没有左子树的节点只会经过1次
 * 先序遍历: 第1次到达一个节点或者该节点只能到达1次时打印
 * 中序遍历: 第2次到达一个节点或者该节点只能到达1次时打印
 * 后序遍历: 第2次到达一个节点的时候逆序打印其右边界上的节点, 然后逆序打印整棵树的右边界
 */
public class Morris {

    /**
     * 普通遍历: 使用系统自动压栈来辅助遍历, 每个节点都可以到达3次
     */
    public static void process(Tree.Node<Integer> curNode) {
        if (curNode == null) {
            // base case
            return;
        }
        // 1 第1次到达该节点
        System.out.println("第1次: " + curNode);
        process(curNode.left);
        // 2 第2次到达该节点
        System.out.println("第2次: " + curNode);
        process(curNode.left);
        // 3 第3次到达该节点
        System.out.println("第3次: " + curNode);
    }

    /**
     * morris遍历实现
     *
     * @param root 根节点
     */
    public static void morris(Tree.Node<Integer> root) {
        if (root == null) {
            // cur为空时, 退出遍历
            return;
        }
        // 申请一个cur指针指向当前遍历到的节点
        Tree.Node<Integer> cur = root;
        // 申请一个mostRight指针指向cur的左子树上的底层最右节点
        Tree.Node<Integer> mostRight;
        // 当cur指向null时遍历结束
        while (cur != null) {
            // 一开始mostRight应该指向cur的左孩子
            mostRight = cur.left;
            // 判断当前节点有无左孩子
            if (mostRight != null) {
                // 有左孩子, 分2种情况, 先获得左子树上的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    // 第2个条件是为了防止遍历错误, 万一是改过的节点也会退出
                    mostRight = mostRight.right;
                }
                // mostRight此时会指向左子树上的最右节点
                if (mostRight.right == null) {
                    // 第1次到达cur(有左孩子的节点)
                    System.out.println("第1次: " + cur);
                    mostRight.right = cur;
                    // 进入左子树
                    cur = cur.left;
                    continue;
                } else {
                    // 第2次到达cur(有左孩子的节点)
                    System.out.println("第2次: " + cur);
                    mostRight.right = null;
                }
            } else {
                // 第1次到达cur(叶节点, 只会到达1次)
                System.out.println("第1次: " + cur);
            }
            // 当前节点无左孩子或者第2次到达cur, 则遍历右边
            cur = cur.right;
        }
    }

    /**
     * morris先序遍历实现
     *
     * @param root 根节点
     */
    public static void morrisPreorderTraversal(Tree.Node<Integer> root) {
        if (root == null) {
            // cur为空时, 退出遍历
            return;
        }
        // 申请一个cur指针指向当前遍历到的节点
        Tree.Node<Integer> cur = root;
        // 申请一个mostRight指针指向cur的左子树上的底层最右节点
        Tree.Node<Integer> mostRight;
        // 当cur指向null时遍历结束
        while (cur != null) {
            // 一开始mostRight应该指向cur的左孩子
            mostRight = cur.left;
            // 判断当前节点有无左孩子
            if (mostRight != null) {
                // 有左孩子, 分2种情况, 先获得左子树上的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    // 第2个条件是为了防止遍历错误, 万一是改过的节点也会退出
                    mostRight = mostRight.right;
                }
                // mostRight此时会指向左子树上的最右节点
                if (mostRight.right == null) {
                    // 第1次到达cur(有左孩子的节点)
                    System.out.println("第1次: " + cur);
                    mostRight.right = cur;
                    // 进入左子树
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                // 第1次到达cur(叶节点, 只会到达1次)
                System.out.println("第1次: " + cur);
            }
            // 当前节点无左孩子或者第2次到达cur, 则遍历右边
            cur = cur.right;
        }
    }

    /**
     * morris中序遍历实现
     *
     * @param root 根节点
     */
    public static void morrisInorderTraversal(Tree.Node<Integer> root) {
        if (root == null) {
            // cur为空时, 退出遍历
            return;
        }
        // 申请一个cur指针指向当前遍历到的节点
        Tree.Node<Integer> cur = root;
        // 申请一个mostRight指针指向cur的左子树上的底层最右节点
        Tree.Node<Integer> mostRight;
        // 当cur指向null时遍历结束
        while (cur != null) {
            // 一开始mostRight应该指向cur的左孩子
            mostRight = cur.left;
            // 判断当前节点有无左孩子
            if (mostRight != null) {
                // 有左孩子, 分2种情况, 先获得左子树上的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    // 第2个条件是为了防止遍历错误, 万一是改过的节点也会退出
                    mostRight = mostRight.right;
                }
                // mostRight此时会指向左子树上的最右节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    // 进入左子树
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            // 第2次到达或者没有左子树的节点会被打印
            System.out.println(cur);
            // 当前节点无左孩子或者第2次到达cur, 遍历右边
            cur = cur.right;
        }
    }

    /**
     * morris后序遍历实现
     */
    public static void morrisPostorderTraversal(Tree.Node<Integer> root) {
        if (root == null) {
            // cur为空时, 退出遍历
            return;
        }
        // 申请一个cur指针指向当前遍历到的节点
        Tree.Node<Integer> cur = root;
        // 申请一个mostRight指针指向cur的左子树上的底层最右节点
        Tree.Node<Integer> mostRight;
        // 当cur指向null时遍历结束
        while (cur != null) {
            // 一开始mostRight应该指向cur的左孩子
            mostRight = cur.left;
            // 判断当前节点有无左孩子
            if (mostRight != null) {
                // 有左孩子, 分2种情况, 先获得左子树上的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    // 第2个条件是为了防止遍历错误, 万一是改过的节点也会退出
                    mostRight = mostRight.right;
                }
                // mostRight此时会指向左子树上的最右节点
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    // 进入左子树
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    // 第2次到达cur, 逆序打印左子树的右边界
                    printReverse(cur.left);
                }
            }
            // 当前节点无左孩子或者第2次到达cur, 则遍历右边
            cur = cur.right;
        }
        // 遍历完成逆序打印整个树的右边界
        printReverse(root);
    }

    /**
     * 逆序打印以root为根节点的右边界
     *
     * @param root 根节点
     */
    private static void printReverse(Tree.Node<Integer> root) {
        // 逆序右边界, tail指向右边界的尾结点, 即逆序后的头结点, cur 指向 tail
        Tree.Node<Integer> tail = reverse(root), cur = tail;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.right;
        }
        // 把右边界逆序回去
        reverse(tail);
    }

    /**
     * 将右边界逆序
     *
     * @param cur 当前节点
     */
    private static Tree.Node<Integer> reverse(Tree.Node<Integer> cur) {
        // pre记录cur的前一个结点, next指向cur的后一个节点
        Tree.Node<Integer> pre = null, next;
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        // 返回逆序后的头结点
        return pre;
    }
}
