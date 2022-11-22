package com.xiaoxiaoyi.orderedlist;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 * 搜索二叉树
 */
public class BinarySearchTree {

    public int size;
    public BinarySearchTreeNode root;
    public final Comparator<BinarySearchTreeNode> comparator;

    public BinarySearchTree(Comparator<BinarySearchTreeNode> comparator) {
        root = null;
        this.comparator = comparator;
    }

    public BinarySearchTreeNode insert(Object element) {
        return insert(new BinarySearchTreeNode(element));
    }

    public BinarySearchTreeNode insert(BinarySearchTreeNode node) {
        if (root == null) {
            size++;
            root = node;
        } else {
            BinarySearchTreeNode curNode = root, parent = null;
            while (curNode != null) {
                parent = curNode;
                if (comparator.compare(node, curNode) > 0) {
                    // node > curNode, 往右边滑
                    curNode = curNode.right;
                } else if (comparator.compare(node, curNode) < 0) {
                    // node < curNode, 往左边滑
                    curNode = curNode.left;
                } else {
                    // 发现相同元素, 直接退出
                    return curNode;
                }
            }
            node.parent = parent;
            if (comparator.compare(parent, node) > 0) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
        size++;
        return node;
    }

    public BinarySearchTreeNode findNode(Object element) {
        return findNode(new BinarySearchTreeNode(element));
    }

    public BinarySearchTreeNode findNode(BinarySearchTreeNode node) {
        if (root == null) {
            return null;
        } else {
            BinarySearchTreeNode curNode = root;
            while (curNode != null) {
                if (comparator.compare(node, curNode) > 0) {
                    // node > curNode, 往右边滑
                    curNode = curNode.right;
                } else if (comparator.compare(node, curNode) < 0) {
                    // node < curNode, 往左边滑
                    curNode = curNode.left;
                } else {
                    // node == curNode, 退出循环
                    break;
                }
            }
            // 要么找到了退出循环返回, 要么返回null
            return curNode;
        }
    }

    public BinarySearchTreeNode remove(Object element) {
        return remove(new BinarySearchTreeNode(element));
    }

    public BinarySearchTreeNode remove(BinarySearchTreeNode node) {
        node = findNode(node);
        BinarySearchTreeNode nodeToReturn = null;
        if (node != null) {
            // 如果要删除的节点存在
            if (node.left == null) {
                // 如果左子树为空, 则用节点的右子树替代该节点即可
                nodeToReturn = nodeTransplant(node, node.right);
            } else if (node.right == null) {
                // 如果右子树为空, 则用节点的左子树替代该节点即可
                nodeToReturn = nodeTransplant(node, node.left);
            } else {
                // 左右子树都不为空, 后继结点为其右子树的最小节点
                BinarySearchTreeNode successorNode = getMinimum(node.right);
                if (!successorNode.parent.equals(node)) {
                    // 如果后继结点不是node的直接子节点, 将后继结点替换成其右子节点
                    nodeTransplant(successorNode, successorNode.right);
                    // 后继结点接管原节点的右子树
                    successorNode.right = (node.right);
                    successorNode.right.parent = successorNode;
                }
                // 后继结点替换掉当前节点
                nodeTransplant(node, successorNode);
                // 后继结点接管原节点的左子树
                successorNode.left = node.left;
                successorNode.left.parent = node.right;
            }
        }
        return nodeToReturn;
    }

    /**
     * 替换nodeToReplace为newNode
     */
    public BinarySearchTreeNode nodeTransplant(BinarySearchTreeNode nodeToReplace, BinarySearchTreeNode newNode) {
        if (nodeToReplace.parent == null) {
            // 替换根节点
            root = newNode;
        } else if (nodeToReplace.equals(nodeToReplace.parent.left)) {
            // 如果被替换掉的是父节点的左边节点
            nodeToReplace.parent.left = newNode;
        } else {
            nodeToReplace.parent.right = newNode;
        }
        if (newNode != null) {
            // 如果新节点不是null, 则设置新节点的父节点为原来节点的父节点
            newNode.parent = nodeToReplace.parent;
        }
        // 返回新节点
        return newNode;
    }

    /**
     * 获取以node为根的搜索树上最小值的节点
     */
    public BinarySearchTreeNode getMinimum(BinarySearchTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 获取以node为根的搜索树上最大值的节点
     */
    public BinarySearchTreeNode getMaximum(BinarySearchTreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public int getSize() {
        return size;
    }

    public void printTree() {
        printSubtree(root);
    }

    public void printSubtree(BinarySearchTreeNode node) {
        if (node.right != null) {
            printTree(node.right, true, "");
        }
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, "");
        }
    }

    public void printTree(BinarySearchTreeNode node, boolean isRight, String indent) {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight ? "        " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight ? " |      " : "        "));
        }
    }

    public void printNodeValue(BinarySearchTreeNode node) {
        if (node.element == null) {
            System.out.print("<null>");
        } else {
            System.out.print(node.element);
        }
        System.out.println();
    }
}