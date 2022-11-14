package com.xiaoxiaoyi.tree;

import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 * 搜索二叉树
 */
public class SearchBinaryTree {

    private Node root;
    private final Comparator<Node> comparator;

    SearchBinaryTree(Comparator<Node> comparator) {
        root = null;
        this.comparator = comparator;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node node) {
        root = node;
    }

    public void addNode(Node node) {
        if (root == null) {
            root = node;
        } else {
            Node curNode = root;
            while (curNode != null) {
                if (comparator.compare(node, curNode) > 0) {
                    // node > curNode, 往右边滑
                    if (curNode.getRight() != null) {
                        curNode = curNode.getRight();
                    } else {
                        curNode.setRight(node);
                        return;
                    }
                } else if (comparator.compare(node, curNode) < 0) {
                    // node < curNode, 往左边滑
                    if (curNode.getLeft() != null) {
                        curNode = curNode.getLeft();
                    } else {
                        curNode.setLeft(node);
                        return;
                    }
                } else {
                    // node == curNode, 直接返回
                    return;
                }
            }
        }
    }

    public Node findNode(Node node) {
        if (root == null) {
            return null;
        } else {
            Node curNode = root;
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

    public Node findNodeParent(Node node) {
        if (root == null) {
            return null;
        } else {
            Node curNode = root, parent = null;
            while (curNode != null) {
                if (comparator.compare(node, curNode) > 0) {
                    // node > curNode, 往右边滑
                    parent = curNode;
                    curNode = curNode.getRight();
                } else if (comparator.compare(node, curNode) < 0) {
                    // node < curNode, 往左边滑
                    parent = curNode;
                    curNode = curNode.getLeft();
                } else {
                    // node == curNode, 退出循环
                    break;
                }
            }
            // 返回父节点
            return parent;
        }
    }

    public void removeNode(Node node) {
        Node findNode = findNode(node);
        if (findNode == null) {
            // 节点不存在, 直接返回
            return;
        }
        // 找其父节点
        Node nodeParent = findNodeParent(node);
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
                root = root.getRight();
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
            Node mostRight = findNode.getLeft();
            while (mostRight.getLeft() != null || mostRight.getRight() != null) {
                if (mostRight.getRight() != null) {
                    mostRight = mostRight.getRight();
                } else if (mostRight.getLeft() != null) {
                    mostRight = mostRight.getLeft();
                }
            }
            // 找到了左子树的最右节点
            mostRight.setLeft(findNode.getLeft());
            mostRight.setRight(findNode.getRight());
            if (nodeParent == null) {
                // 如果删除的是根节点, 则根节点重新指向mostRight
                root = mostRight;
            }
            findNode.setLeft(null);
            findNode.setRight(null);
            // 移除当前节点
            removeNode(findNode);
        }
    }
}
