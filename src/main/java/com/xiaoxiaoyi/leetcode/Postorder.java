package com.xiaoxiaoyi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoxiaoyi
 * 给定一棵树先序和中序遍历的集合(无重复节点), 生成该树后序遍历的结果
 */
public class Postorder {

    /**
     * 树上的每个节点都用char代替
     */
    public static char[] generate(char[] preorder, char[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return new char[]{};
        }
        int n = preorder.length;
        Map<Character, Integer> inorderMap = new HashMap<>(n);
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        char[] postorder = new char[n];
        // 填后序遍历, 根据先序和中序的0~n-1位置去填后序的0~n-1位置
        process(
                preorder, 0, n - 1,
                inorderMap, 0, n - 1,
                postorder, 0, n - 1
        );
        return postorder;
    }

    private static void process(
            char[] preorder, int preLeft, int preRight,
            Map<Character, Integer> inorderMap, int inLeft, int inRight,
            char[] postorder, int postLeft, int postRight
    ) {
        // n为当前这轮要填的节点个数
        int n = postRight - postLeft + 1;
        if (n > 1) {
            // 拿出先序遍历的第一个元素 (中 左 右)
            char preNode = preorder[preLeft];
            // 因为后序遍历时 左 右 中 的顺序, 所以可以直接填
            postorder[postRight] = preNode;
            // 在中序遍历中寻找先序遍历的这个节点所在的位置, 将中序遍历分为 左 中 右 3个部分
            int cur = inorderMap.get(preNode);
            // 可以算出左子树的节点个数
            int leftNodeNum = cur - inLeft;
            // 同样可以算出右子树的节点个数
            int rightNodeNum = inRight - cur;
            // 递归填postorder左子树部分
            process(
                    // 先序遍历的左子树部分下标范围可以计算出来
                    preorder, preLeft + 1, preLeft + leftNodeNum,
                    // 中序遍历的左子树部分下标范围可以计算出来
                    inorderMap, inLeft, cur - 1,
                    // 后续遍历的左子树部分下标范围可以计算出来
                    postorder, postLeft, postLeft + leftNodeNum - 1
            );
            // 递归填postorder右子树部分
            process(
                    // 先序遍历的右子树部分下标可以计算出来
                    preorder, preRight - rightNodeNum + 1, preRight,
                    // 中序遍历的右子树部分下标范围可以计算出来
                    inorderMap, cur + 1, inRight,
                    // 后序遍历的右子树部分下标范围可以计算出来
                    postorder, postRight - rightNodeNum, postRight - 1
            );
        } else {
            // 如果只有1个元素要填, 直接填即可
            postorder[postLeft] = preorder[preLeft];
        }
    }
}
