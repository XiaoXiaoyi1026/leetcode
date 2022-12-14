package com.xiaoxiaoyi.tree;

/**
 * @author xiaoxiaoyi
 */
public class MaximumBinarySearchSubtree {

    /**
     * 每个节点需要提供的信息
     */
    public static class Info {
        // 子树中最大搜索子树的头结点
        public ElementBinaryTree.Node<Integer> maximumSearchSubtreeRoot;
        // 以当前节点为根的树上最大搜索子树的大小
        public int maximumSearchSubtreeSize;
        // 以当前节点为根的树上的最大值
        public Integer maximumElement;
        // 以当前节点为根的树上的最小值
        public Integer minimumElement;
        // 当前节点为根的树是否为搜索二叉树
        public boolean isSearchTree;

        public Info(ElementBinaryTree.Node<Integer> maximumSearchSubtreeRoot,
                    int maximumSearchSubtreeSize,
                    Integer maximumElement, Integer minimumElement,
                    boolean isSearchTree) {
            this.maximumSearchSubtreeRoot = maximumSearchSubtreeRoot;
            this.maximumSearchSubtreeSize = maximumSearchSubtreeSize;
            this.maximumElement = maximumElement;
            this.minimumElement = minimumElement;
            this.isSearchTree = isSearchTree;
        }
    }

    public static ElementBinaryTree.Node<Integer> maximumSearchSubtreeRoot(ElementBinaryTree<Integer> tree) {
        return maximumSearchSubtreeRoot(tree.getRoot());
    }

    public static ElementBinaryTree.Node<Integer> maximumSearchSubtreeRoot(ElementBinaryTree.Node<Integer> root) {
        return searchProcess(root).maximumSearchSubtreeRoot;
    }

    private static Info searchProcess(ElementBinaryTree.Node<Integer> curElementNode) {
        if (curElementNode == null) {
            // 当前节点为空, 假设树上的节点值都为>0的数
            return new Info(
                    null,
                    0,
                    null,
                    null,
                    true
            );
        }
        Info leftInfo = searchProcess(curElementNode.getLeft());
        Info rightInfo = searchProcess(curElementNode.getRight());

        /*
        判断当前子树是否为搜索二叉树
        1. 左右子树都是搜索二叉树
        2. 当前节点的值 > 左子树最大值
        3. 当前节点的值 < 右子树最小值
         */
        boolean curIsSearchTree = leftInfo.isSearchTree && rightInfo.isSearchTree
                && (leftInfo.maximumElement == null || curElementNode.element > leftInfo.maximumElement)
                && (rightInfo.minimumElement == null || curElementNode.element < rightInfo.minimumElement);

        /*
        如果当前节点为根的树是搜索二叉树, 则最大搜索子树的根节点就是当前节点
        否则左右两子树的最大搜索子树的大小进行过比较, 较大的那个搜索子树的根为当前节点下的最大搜索子树的根节点
         */
        ElementBinaryTree.Node<Integer> maximumSearchSubtreeRoot = curIsSearchTree ?
                curElementNode : leftInfo.maximumSearchSubtreeSize >= rightInfo.maximumSearchSubtreeSize ?
                leftInfo.maximumSearchSubtreeRoot : rightInfo.maximumSearchSubtreeRoot;
        /*
        如果当前节点为根的树是搜索二叉树, 则最大搜索子树的大小是左边的大小 + 右边的大小 + 1
        否则取左右两边更大的那个
         */
        int maximumSearchSubtreeSize = curIsSearchTree ?
                leftInfo.maximumSearchSubtreeSize + rightInfo.maximumSearchSubtreeSize + 1
                : Math.max(leftInfo.maximumSearchSubtreeSize, rightInfo.maximumSearchSubtreeSize);

        // 如果是搜索树, 如果右子树最大值为null, 则整棵树的最大值就是当前节点的element, 否则就是右子树的最大值
        Integer maxElement = curIsSearchTree ? rightInfo.maximumElement == null ? curElementNode.element : rightInfo.maximumElement
                // 如果不是搜索树, 如果左子树的最大值为空, 如果右子树的最大值为空, 则当前节点的element为最大值
                : leftInfo.maximumElement == null ? rightInfo.maximumElement == null ? curElementNode.element
                // 如果右子树最大值不为空, 则最大值应该比较当前节点和右子树的最大值
                : Math.max(curElementNode.element, rightInfo.maximumElement)
                // 如果左子树最大值不为空, 如果右子树的最大值为空, 则最大值取当前节点和左子树最大值之一, 否则取3者最大值
                : Math.max(curElementNode.element, rightInfo.maximumElement == null ? leftInfo.maximumElement
                : Math.max(leftInfo.maximumElement, rightInfo.maximumElement));

        // 逻辑同取最大值
        Integer minElement = curIsSearchTree ? leftInfo.minimumElement == null ? curElementNode.element : leftInfo.minimumElement
                : leftInfo.minimumElement == null ? rightInfo.minimumElement == null ? curElementNode.element
                : Math.min(curElementNode.element, rightInfo.minimumElement)
                : Math.max(curElementNode.element, rightInfo.minimumElement == null ? leftInfo.minimumElement
                : Math.min(leftInfo.minimumElement, rightInfo.minimumElement));

        // 返回当前树的信息
        return new Info(maximumSearchSubtreeRoot,
                maximumSearchSubtreeSize,
                maxElement, minElement,
                curIsSearchTree);
    }

}
