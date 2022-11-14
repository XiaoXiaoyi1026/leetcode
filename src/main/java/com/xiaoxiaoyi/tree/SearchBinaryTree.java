package com.xiaoxiaoyi.tree;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 * 搜索二叉树
 */
public class SearchBinaryTree {

    public static class SearchBinaryTreeNode extends Node {
        private SearchBinaryTreeNode parent;

        SearchBinaryTreeNode(Object val) {
            super(val);
            this.parent = null;
        }

        @Override
        public SearchBinaryTreeNode getLeft() {
            return (SearchBinaryTreeNode) super.getLeft();
        }

        @Override
        public SearchBinaryTreeNode getRight() {
            return (SearchBinaryTreeNode) super.getRight();
        }

        public void setLeft(SearchBinaryTreeNode left) {
            super.setLeft(left);
        }

        public void setRight(SearchBinaryTreeNode right) {
            super.setRight(right);
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(SearchBinaryTreeNode parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "SearchBinaryTreeNode{" +
                    "val=" + super.getVal() +
                    '}';
        }
    }

    private SearchBinaryTreeNode root;
    private final Comparator<Node> comparator;

    SearchBinaryTree(Comparator<Node> comparator) {
        root = null;
        this.comparator = comparator;
    }

    public SearchBinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(SearchBinaryTreeNode node) {
        root = node;
    }

    public void addNode(SearchBinaryTreeNode node) {
        if (root == null) {
            root = node;
        } else {
            SearchBinaryTreeNode curNode = root;
            while (curNode != null) {
                if (comparator.compare(node, curNode) > 0) {
                    // node > curNode, 往右边滑
                    if (curNode.getRight() != null) {
                        curNode = curNode.getRight();
                    } else {
                        curNode.setRight(node);
                        node.setParent(curNode);
                        return;
                    }
                } else if (comparator.compare(node, curNode) < 0) {
                    // node < curNode, 往左边滑
                    if (curNode.getLeft() != null) {
                        curNode = curNode.getLeft();
                    } else {
                        curNode.setLeft(node);
                        node.setParent(curNode);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public SearchBinaryTreeNode findNode(Node node) {
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

    public void removeNode(SearchBinaryTreeNode node) {
        SearchBinaryTreeNode findNode = findNode(node);
        if (findNode == null) {
            // 节点不存在, 直接返回
            return;
        }
        // 找其父节点
        Node nodeParent = node.getParent();
        if (findNode.getLeft() == null && findNode.getRight() == null) {
            // 左右子树都为空
            if (nodeParent == null) {
                // 父节点为空且移除的是根节点且树上只有1个根节点
                root = null;
            } else {
                // 父节点不为空
                if (comparator.compare(nodeParent.getLeft(), node) == 0) {
                    // 当前节点是父节点的左孩子
                    nodeParent.setLeft(null);
                } else {
                    nodeParent.setRight(null);
                }
            }
        } else if (findNode.getLeft() == null) {
            // 左孩子为空, 右孩子不为空
            if (nodeParent == null) {
                // 父节点为空, 删除的是根节点, 根节点直接右移即可
                this.setRoot(root.getRight());
            } else {
                // 父节点不为空
                if (comparator.compare(nodeParent.getLeft(), node) == 0) {
                    // 当前节点是父节点的左孩子
                    nodeParent.setLeft(findNode.getRight());
                } else {
                    nodeParent.setRight(findNode.getRight());
                }
            }
        } else if (findNode.getRight() == null) {
            // 右子树为空, 左子树不为空
            if (nodeParent == null) {
                // 父节点为空, 删除的是根节点, 根节点直接左移即可
                root = root.getLeft();
            } else {
                // 父节点不为空
                if (comparator.compare(nodeParent.getLeft(), node) == 0) {
                    // 当前节点是父节点的左孩子
                    nodeParent.setLeft(findNode.getLeft());
                } else {
                    nodeParent.setRight(findNode.getLeft());
                }
            }
        } else {
            // 左右子树都不为空, 找到左子树的最右节点或者右子树的最左节点, 放到当前的位置上
            SearchBinaryTreeNode mostRight = findNode.getLeft();
            while (mostRight.getLeft() != null || mostRight.getRight() != null) {
                if (mostRight.getRight() != null) {
                    mostRight = mostRight.getRight();
                } else if (mostRight.getLeft() != null) {
                    mostRight = mostRight.getLeft();
                }
            }
            // 找到了左子树的最右节点
            mostRight.setLeft(
                    comparator.compare(
                            mostRight, findNode.getLeft()) == 0 ?
                            null : findNode.getLeft()
            );
            mostRight.setRight(findNode.getRight());
            if (nodeParent == null) {
                // 如果删除的是根节点, 则根节点重新指向mostRight
                root = mostRight;
                setRoot(mostRight);
                mostRight.parent = null;
            }
            findNode.setLeft(null);
            findNode.setRight(null);
            // 移除当前节点
            removeNode(findNode);
        }
    }
}