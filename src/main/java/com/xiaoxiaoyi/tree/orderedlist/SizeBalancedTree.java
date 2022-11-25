package com.xiaoxiaoyi.tree.orderedlist;

/**
 * @author xiaoxiaoyi
 * SBTree, 要求一个节点的兄弟节点两个子树的节点个数<=这个节点为根的树上的节点个数
 */
public class SizeBalancedTree<K extends Comparable<K>, V> {

    public static class SizeBalancedTreeNode<K extends Comparable<K>, V> {
        public K key;
        public V value;

        public SizeBalancedTreeNode<K, V> left;
        public SizeBalancedTreeNode<K, V> right;

        public int size;

        public SizeBalancedTreeNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 1;
        }
    }

    /**
     * 整棵树的根节点
     */
    private SizeBalancedTreeNode<K, V> root;

    public SizeBalancedTreeNode<K, V> getRoot() {
        return root;
    }

    /**
     * 树的大小(节点个数)
     */
    public int size() {
        return root == null ? 0 : root.size;
    }

    /**
     * 右旋操作
     *
     * @param cur 要进行右旋的子树的根节点
     */
    private SizeBalancedTreeNode<K, V> rightRotate(SizeBalancedTreeNode<K, V> cur) {
        // 记录左子树根节点
        SizeBalancedTreeNode<K, V> leftNode = cur.left;
        // 改变当前节点的左指针, 接管左子树的右子树
        cur.left = leftNode.right;
        // 左子树的右子树接管当前这个节点
        leftNode.right = cur;
        // 更新左子树的size为旋转前的子树size(右旋操作使得左子树接管了当前这棵树)
        leftNode.size = cur.size;
        // 更新旋转后的当前节点的size
        cur.size = (cur.left != null ? cur.left.size : 0) +
                (cur.right != null ? cur.right.size : 0) + 1;
        // 返回旋转后的根节点
        return leftNode;
    }

    /**
     * 左旋操作
     *
     * @param cur 进行左旋操作的子树根节点
     */
    private SizeBalancedTreeNode<K, V> leftRotate(SizeBalancedTreeNode<K, V> cur) {
        // 记录右边的节点(左旋后变为根节点代替cur)
        SizeBalancedTreeNode<K, V> rightNode = cur.right;
        // 当前节点的右指针接管右节点的左子树
        cur.right = rightNode.left;
        // 右节点的左指针接管当前这个节点
        rightNode.left = cur;
        // 接管后右节点为根的子树size变为当前这个节点的size
        rightNode.size = cur.size;
        // 当前节点的size更新
        cur.size = (cur.left != null ? cur.left.size : 0) +
                (cur.right != null ? cur.right.size : 0) + 1;
        // 返回旋转后的根节点
        return rightNode;
    }

    /**
     * 调整操作
     */
    private SizeBalancedTreeNode<K, V> maintain(SizeBalancedTreeNode<K, V> cur) {
        if (cur == null) {
            return null;
        }
        if (cur.left != null && cur.left.left != null && cur.right != null && cur.left.left.size > cur.right.size) {
            // ll型, 执行1次右旋操作
            cur = rightRotate(cur);
            // 右旋1次改变了右子树和自己, 所以要递归调整
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (cur.left != null && cur.left.right != null && cur.right != null && cur.left.right.size > cur.right.size) {
            // lr型, 先对左子树执行1次左旋
            cur.left = leftRotate(cur.left);
            // 然后再整棵树上执行1次右旋
            cur = rightRotate(cur);
            // 然后改变了的树都要递归调整
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (cur.right != null && cur.right.right != null && cur.left != null && cur.right.right.size > cur.left.size) {
            // rr型, 先执行1次左旋
            cur = leftRotate(cur);
            // 然后看改变了哪些树, 递归重新调整
            cur.left = maintain(cur.left);
            cur = maintain(cur);
        } else if (cur.right != null && cur.right.left != null && cur.left != null && cur.right.left.size > cur.left.size) {
            // rl型, 先在右子树上执行1次右旋操作
            cur.right = rightRotate(cur.right);
            // 然后再整棵树上执行1次左旋操作
            cur = leftRotate(cur);
            // 然后改变了的树都要调整
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        }
        // 返回调整后的树根节点
        return cur;
    }

    /**
     * 查找一个节点
     */
    private SizeBalancedTreeNode<K, V> findLastIndex(K key) {
        SizeBalancedTreeNode<K, V> cur, pre;
        // pre指向遍历的前1个节点, cur指向遍历到的当前节点
        pre = cur = this.root;
        while (cur != null) {
            // 记录当前节点(cur后移后pre指向的就是前1个)
            pre = cur;
            if (key.compareTo(cur.key) == 0) {
                // 找到了目标
                break;
            } else if (key.compareTo(cur.key) < 0) {
                // 要找的那个key比cur小, 往左走
                cur = cur.left;
            } else {
                // 否则大于, 往右走
                cur = cur.right;
            }
        }
        // 返回目标节点的前1个节点
        return pre;
    }

    /**
     * 返回不小于(大于等于)key的最后一个节点
     */
    private SizeBalancedTreeNode<K, V> findLastNotSmallIndex(K key) {
        SizeBalancedTreeNode<K, V> ans = null, cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                // 找到了一样的key, 记录结果并退出返回
                ans = cur;
                break;
            } else if (key.compareTo(cur.key) < 0) {
                // 目标key小于当前遍历到的key, 记录下这个结果, 然后往左走看看能不能找到更小的
                ans = cur;
                cur = cur.left;
            } else {
                // 当前遍历到的key比目标key小, 直接去右边找
                cur = cur.right;
            }
        }
        // 返回找到的节点
        return ans;
    }

    /**
     * 查找最后一个不大于(小于等于)目标key的节点
     */
    private SizeBalancedTreeNode<K, V> findLastNotBigIndex(K key) {
        SizeBalancedTreeNode<K, V> ans = null, cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                // 找到了key值相等的节点, 直接记录并返回
                ans = cur;
                break;
            } else if (key.compareTo(cur.key) < 0) {
                // 如果当前的key大于目标key, 则直接去左边找
                cur = cur.left;
            } else {
                // 如果当前的key小于目标key, 往右边找有没有更接近的
                ans = cur;
                cur = cur.right;
            }
        }
        return ans;
    }

    /**
     * 添加节点
     *
     * @param cur 要插入到的目标子树的根节点
     */
    private SizeBalancedTreeNode<K, V> add(SizeBalancedTreeNode<K, V> cur, K key, V value) {
        if (cur == null) {
            // 如果是插入到空节点, 直接new出来即可
            return new SizeBalancedTreeNode<>(key, value);
        }
        // 因为要插入到这颗子树下面, 所以当前子树的size要+1
        cur.size++;
        if (key.compareTo(cur.key) < 0) {
            // 如果插入的节点key值小于当前子树的根节点的key, 则该节点应该被插入到当前子树的左子树
            cur.left = add(cur.left, key, value);
        } else {
            // 否则应该被插入到右子树(不考虑相等的情况, 规定输入的key应该不存在, 就算存在也只会被覆盖)
            cur.right = add(cur.right, key, value);
        }
        // 插入节点的子树需要调整
        return maintain(cur);
    }

    /**
     * 根据key删除节点
     */
    private SizeBalancedTreeNode<K, V> delete(SizeBalancedTreeNode<K, V> cur, K key) {
        // 要从当前子树删除一个节点, size - 1
        cur.size--;
        if (key.compareTo(cur.key) > 0) {
            // 如果要删除的节点的key值大于当前子树的根节点的值, 则说明应该去右子树删除该节点
            cur.right = delete(cur.right, key);
        } else if (key.compareTo(cur.key) < 0) {
            // 否则去左子树删除该节点
            cur.left = delete(cur.left, key);
        } else {
            // 找到要删除的节点了, 分情况
            if (cur.left == null && cur.right == null) {
                // 该节点是孤独的, 直接指向null
                cur = null;
            } else if (cur.left == null) {
                // 该节点没有左子树, 那么当前节点指向右子树即可
                cur = cur.right;
            } else if (cur.right == null) {
                // 当前节点有左子树, 没有右子树, 直接指向左子树即可
                cur = cur.left;
            } else {
                // 左右子树都有, pre指向前一个, des指向要被销毁的节点, 默认拿要被销毁节点的右子树来替代
                SizeBalancedTreeNode<K, V> pre = null, des = cur.right;
                // 右子树size - 1
                des.size--;
                while (des.left != null) {
                    pre = des;
                    des = des.left;
                    des.size--;
                }
                if (pre != null) {
                    pre.left = des.right;
                    des.right = cur.right;
                }
                des.left = cur.left;
                des.size = des.left.size + des.right.size + 1;
                // 释放当前节点
                cur = des;
            }
        }
        return cur;
    }

    private SizeBalancedTreeNode<K, V> getIndex(SizeBalancedTreeNode<K, V> cur, int kth) {
        if (cur == null) {
            return null;
        }
        if (kth == (cur.left != null ? cur.left.size : 0) + 1) {
            return cur;
        } else if (kth <= (cur.left != null ? cur.left.size : 0)) {
            return getIndex(cur.left, kth);
        } else {
            return getIndex(cur.right, kth - (cur.left != null ? cur.left.size : 0) - 1);
        }
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        SizeBalancedTreeNode<K, V> lastNode = findLastIndex(key);
        return lastNode != null && key.compareTo(lastNode.key) == 0;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        SizeBalancedTreeNode<K, V> lastNode = findLastIndex(key);
        if (lastNode != null && key.compareTo(lastNode.key) == 0) {
            lastNode.value = value;
        } else {
            root = add(root, key, value);
        }
    }

    public void remove(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        if (containsKey(key)) {
            root = delete(root, key);
        }
    }

    public K getIndexKey(int index) {
        if (index < 0 || index >= this.size()) {
            throw new RuntimeException("invalid parameter.");
        }
        SizeBalancedTreeNode<K, V> node = getIndex(root, index + 1);
        return node == null ? null : node.key;
    }

    public V getIndexValue(int index) {
        if (index < 0 || index >= this.size()) {
            throw new RuntimeException("invalid parameter.");
        }
        SizeBalancedTreeNode<K, V> node = getIndex(root, index + 1);
        return node == null ? null : node.value;
    }

    public V get(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        SizeBalancedTreeNode<K, V> lastNode = findLastIndex(key);
        if (lastNode != null && key.compareTo(lastNode.key) == 0) {
            return lastNode.value;
        } else {
            return null;
        }
    }

    public K firstKey() {
        if (root == null) {
            return null;
        }
        SizeBalancedTreeNode<K, V> cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.key;
    }

    public K lastKey() {
        if (root == null) {
            return null;
        }
        SizeBalancedTreeNode<K, V> cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.key;
    }

    public K floorKey(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        SizeBalancedTreeNode<K, V> lastNoBigNode = findLastNotBigIndex(key);
        return lastNoBigNode == null ? null : lastNoBigNode.key;
    }

    public K ceilingKey(K key) {
        if (key == null) {
            throw new RuntimeException("invalid parameter.");
        }
        SizeBalancedTreeNode<K, V> lastNoSmallNode = findLastNotSmallIndex(key);
        return lastNoSmallNode == null ? null : lastNoSmallNode.key;
    }
}
