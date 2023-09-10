package com.xiaoxiaoyi.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaoxiaoyi
 */
@Getter
@Setter
public class BinaryTree {

    Node root;

    @Getter
    @Setter
    public static class Node {
        Node left;
        Node right;

        public Node() {
            left = right = null;
        }

    }

}
