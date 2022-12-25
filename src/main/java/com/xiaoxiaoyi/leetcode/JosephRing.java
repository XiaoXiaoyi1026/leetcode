package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 约瑟夫环问题, 给定一个有n个节点的循环单链表
 * 从起始节点开始报数, 刚好报到m的节点从环中删除
 * 问进行完n-1轮后最后剩下节点是谁
 */
public class JosephRing {

    public static class Node {
        public int serialNumber;
        public Node next;

        Node(int serialNumber) {
            this.serialNumber = serialNumber;
            next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "serialNumber=" + serialNumber + "}";
        }
    }

    public static Node getLive(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node cur = head.next;
        int tmp = 1;
        while (cur != head) {
            tmp++;
            cur = cur.next;
        }
        // 获取能存活下来的节点的编号
        tmp = getLive(tmp, m);
        while (--tmp > 0) {
            // 往后数tmp个节点
            assert head != null;
            head = head.next;
        }
        // 只让它自己成环, 删除其它不能活下来的节点
        assert head != null;
        head.next = head;
        // 返回
        return head;
    }

    public static int getLive(int i, int m) {
        if (i == 1) {
            // 如果环上只有1个节点, 那么存活的节点编号就是1
            return 1;
        }
        /*
        问题: 已知在i长度的情况下, 存活下来的节点编号是x, 问在i+1长度情况时, 它对应的编号为?
        先分析y = x % i的图像
        在长度固定的情况下, 假设长度为n, 那么节点编号与报的数的关系
        当报数在1~n时, 对应的节点编号为1~n, 当报数范围为n+1~2n时, 对应的节点编号为1~n ...
        以节点编号作y轴, 报数作x轴, 画出报数与节点编号之间的关系
        由y = x % i图像和函数变换 可得 编号 = (报数 - 1) % n + 1
        假设开始有n个节点, 那么第一轮编号为m的节点会被删除
        第m+1个节点的编号变为1 ... 第n个节点的编号变为n - m
        第1个节点变为n-m+1 ... 第m-1个节点n-1, 剩下n-1个节点
        分析新老节点之间编号的函数关系, 将y轴设为老节点编号, x轴对应新节点编号
        可知点(1, m + 1), (n, n - m), (1, n - m + 1), (n - 1, m - 1)
        由这4个点作出图像, 发现是2根与y = x % i图像相似的线段, 延长2根线段
        发现此图像可以由编号 = (报数 - 1) % n + 1图像向左平移m个单位得到
        即编号 = (报数 - 1 + m) % n + 1 => 老编号 = (新编号 - 1 + m) % n + 1
        m为长度为n时, 报数为m的节点, 所以它在删除前的编号 = (m - 1) % n + 1
        得到: 老编号 = (新编号 - 1 + (m - 1) % n + 1) % n + 1
        = (新编号 + (m - 1) % n) % n + 1
        假设m - 1 = kn + rest(1 < rest < n)
        原式 = (新编号 + rest) % n + 1
        = (新编号 + rest + kn) % n + 1
        = (新编号 + m - 1) % n + 1
         */
        return (getLive(i - 1, m) + m - 1) % i + 1;
    }

}
