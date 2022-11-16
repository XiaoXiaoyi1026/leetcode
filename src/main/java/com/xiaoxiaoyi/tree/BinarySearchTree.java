package com.xiaoxiaoyi.tree;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 * 搜索二叉树
 */
public class BinarySearchTree {

    protected static class SearchBinaryTreeNode extends Node {
        private SearchBinaryTreeNode parent;

        SearchBinaryTreeNode(Object val) {
            super(val);
            this.parent = null;
        }

        @Override
        protected SearchBinaryTreeNode getLeft() {
            return (SearchBinaryTreeNode) super.getLeft();
        }

        @Override
        protected SearchBinaryTreeNode getRight() {
            return (SearchBinaryTreeNode) super.getRight();
        }

        protected void setLeft(SearchBinaryTreeNode left) {
            super.setLeft(left);
        }

        protected void setRight(SearchBinaryTreeNode right) {
            super.setRight(right);
        }

        protected SearchBinaryTreeNode getParent() {
            return parent;
        }

        protected void setParent(SearchBinaryTreeNode parent) {
            this.parent = parent;
        }
    }

    private SearchBinaryTreeNode root;
    private final Comparator<Node> comparator;

    BinarySearchTree(Comparator<Node> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    protected SearchBinaryTreeNode getRoot() {
        return root;
    }

    protected void setRoot(SearchBinaryTreeNode node) {
        root = node;
    }

    protected Node insert(Object o) {
        return insert(new SearchBinaryTreeNode(o));
    }

    protected Node insert(SearchBinaryTreeNode node) {
        if (root == null) {
            root = node;
        } else {
            SearchBinaryTreeNode curNode = root, parent = null;
            while (curNode != null) {
                parent = curNode;
                if (comparator.compare(node, curNode) > 0) {
                    // node > curNode, 往右边滑
                    curNode = curNode.getRight();
                } else if (comparator.compare(node, curNode) < 0) {
                    // node < curNode, 往左边滑
                    curNode = curNode.getLeft();
                } else {
                    // 发现相同元素, 直接退出
                    return curNode;
                }
            }
            node.setParent(parent);
            if (comparator.compare(parent, node) > 0) {
                parent.setLeft(node);
            } else {
                parent.setRight(node);
            }
        }
        return node;
    }

    protected SearchBinaryTreeNode findNode(Node node) {
        if (root == null) {
            return null;
        } else {
            SearchBinaryTreeNode curNode = root;
            while (curNode != null) {
                if (comparator.compare(node, curNode) > 0) {
                    // node > curNode, 往右边滑
                    curNode = curNode.getRight();
                } else if (comparator.compare(node, curNode) < 0) {
                    // node < curNode, 往左边滑
                    curNode = curNode.getLeft();
                } else {
                    // node == curNode, 退出循环
                    break;
                }
            }
            // 要么找到了退出循环返回, 要么返回null
            return curNode;
        }
    }

    protected Node removeNode(SearchBinaryTreeNode node) {
        node = findNode(node);
        Node nodeToReturn = null;
        if (node != null) {
            // 如果要删除的节点存在
            if (node.getLeft() == null) {
                // 如果左子树为空, 则用节点的右子树替代该节点即可
                nodeToReturn = transplant(node, node.getRight());
            } else if (node.getRight() == null) {
                // 如果右子树为空, 则用节点的左子树替代该节点即可
                nodeToReturn = transplant(node, node.getLeft());
            } else {
                // 左右子树都不为空, 后继结点为其右子树的最小节点
                SearchBinaryTreeNode successorNode = (SearchBinaryTreeNode) getMinimum(node.getRight());
                if (successorNode.getParent() != node) {
                    // 如果后继结点不是node的直接子节点, 将后继结点替换成其右子节点
                    transplant(successorNode, successorNode.getRight());
                    // 后继结点接管原节点的右子树
                    successorNode.setRight(node.getRight());
                    successorNode.getRight().setParent(successorNode);
                }
                // 后继结点替换掉当前节点
                transplant(node, successorNode);
                // 后继结点接管原节点的左子树
                successorNode.setLeft(node.getLeft());
                successorNode.getLeft().setParent(node.getRight());
            }
        }
        return nodeToReturn;
    }

    /**
     * 替换nodeToReplace为newNode
     */
    protected Node transplant(SearchBinaryTreeNode nodeToReplace, SearchBinaryTreeNode newNode) {
        if (nodeToReplace.getParent() == null) {
            // 替换根节点
            setRoot(newNode);
        } else if (nodeToReplace.equals(nodeToReplace.getParent().getLeft())) {
            // 如果被替换掉的是父节点的左边节点
            nodeToReplace.getParent().setLeft(newNode);
        } else {
            nodeToReplace.getParent().setRight(newNode);
        }
        if (newNode != null) {
            // 如果新节点不是null, 则设置新节点的父节点为原来节点的父节点
            newNode.setParent(nodeToReplace.getParent());
        }
        // 返回新节点
        return newNode;
    }

    /**
     * 获取以node为根的搜索树上最小值的节点
     */
    protected Node getMinimum(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }
}