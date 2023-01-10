package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 加油站问题, 一共n个加油站, 首尾相连
 * 给定一个油数组gas, gas[i]代表加油站i上存有的油量
 * 给定一个距离数组distance, distance[i]代表加油站i到加油站(i+1)%n的距离
 * 问从哪些加油站出发, 顺指针可以走完一圈, 假设车的油箱能够装的油量没有上限
 * 规定: 车一开始油箱没有油, 即为0, 走到一个加油站可以把该加油站所有的油加到油箱中
 * 如果从某个节点出发到达中途某个节点处时, 剩余油量不足以到达下一个加油站, 那么这个节点为false
 * 能够走完一圈则为true, 返回每个节点对应的状态boolean[true/false]
 * 时间复杂度O(n), 空间复杂度O(1)
 */
public class GasStation {

    public static boolean[] getStatus(int[] gas, int[] distance) {
        if (gas == null || distance == null || gas.length != distance.length) {
            throw new RuntimeException("Invalid params!");
        }
        int n = gas.length;
        boolean[] res = new boolean[n];
        // 先把gas数组变成每个加油站的纯能(通过后能够带走多少油)信息
        for (int i = 0; i < n; i++) {
            gas[i] -= distance[i];
        }
        /*
        begin:  连通区左边界(包含)
        end:    连通区右边界(不包含)
        need:   左边节点想要接入连通区至少需要的油量
        rest:   跑过连通区后剩余的油量
         */
        int begin = 0, end, need = 0, rest;
        // 选择gas中随便一个正数作起点
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] > 0) {
                begin = i;
                break;
            }
        }
        // 要求必须 起始点 > 0
        if (gas[begin] > 0) {
            end = (begin + 1) % n;
            rest = gas[begin];
            while (begin != end) {
                int tmp = end;
                // 尝试往右扩
                while (begin != tmp && rest + gas[tmp] >= 0) {
                    // rest + gas[end] >= 0, 说明可以往右扩
                    rest += gas[tmp];
                    tmp = (tmp + 1) % n;
                }
                // 如果右扩完后能够绕一圈, 则说明这个起点可行
                if (begin == tmp) {
                    res[begin] = true;
                    begin = begin == 0 ? n - 1 : begin - 1;
                    while (begin != end) {
                        // 判断这个节点能否接到连通区头部
                        need -= gas[begin];
                        if (need <= 0) {
                            // 如果gas[begin] >= need, 那么可以说明这个节点可以接到连通区头部
                            res[begin] = true;
                            need = 0;
                        }
                        begin = begin == 0 ? n - 1 : begin - 1;
                    }
                    break;
                }
                end = tmp;
                // 如果不能往右扩了, 则尝试往左扩
                begin = begin == 0 ? n - 1 : begin - 1;
                while (begin != end) {
                    // 如果gas[begin] < 0, 那么它后面的节点想要从头部接上需要的代价就为need - gas[begin]
                    need -= gas[begin];
                    if (need < 0) {
                        rest -= need;
                        need = 0;
                        break;
                    }
                    begin = begin == 0 ? n - 1 : begin - 1;
                }
            }
        }
        // 还原gas
        for (int i = 0; i < gas.length; i++) {
            gas[i] += distance[i];
        }
        return res;
    }

}
