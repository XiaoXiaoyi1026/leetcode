package com.xiaoxiaoyi.tree.orderedlist;

import java.util.*;

/**
 * @author xiaoxiaoyi
 * 天际线问题
 * 给定一个input[n][3]
 * 矩阵每一行有3个数, 前2个数代表x轴上的范围, 第3个数代表y轴上的范围
 * 比如[1, 4, 3]代表x轴上1~4, y轴上0~3的矩形区域
 * 返回轮廓线, 是res[m][3]的形式
 * res[i][0]代表轮廓线在x轴上的开始位置
 * res[i][1]代表轮廓线在x轴上的结束位置
 * res[i][2]代表轮廓线在y轴上到达的高度
 * 可以由res[m][3]拼出input[n][3]所有矩形重合后的轮廓(不包括x轴)
 * 讨论在何时需要产生轮廓线: 在y轴高度发生变化时需要
 * 假设来到x轴上的i位置, 在x = i时, y轴上的高度从a变成了b
 * 则可以产生轮廓线信息[i, ?, b], ?代表该条轮廓线的结束位置此时并不知道
 * 但可以确定的是此轮廓线的起始x轴坐标为i, 到达高度为b
 * 假设在x轴上i位置之后的j位置, y轴高度又发生了变化, 从b变成了c
 * 那么此时就可以确定i位置的轮廓线结束位置为j, 即[i, j, b]
 * 然后j位置产生新的轮廓线[j, ?, c], 继续遍历x轴寻找下一个y轴高度变化的位置
 * 如果y轴高度从不为0的任意数变为0, 此时不产生新的轮廓线
 * 如果input[i](0<i<n) = [u, d, h], 那么可以拆解出2个信息:
 * 1. 矩形i在x轴的u位置有一条边, 方向向上, 表示在y轴上从高度0到达高度h, 即: [u, up, h]
 * 2. 矩形i在x轴的d位置有一条边, 方向向下, 表示在y轴上从高度h到达高度0, 即: [d, down, h]
 * 按照这种方式可以根据input的信息得到每一个矩形在y轴上产生的高度变化信息
 * 我们将这些信息按照0位置的数值进行升序排序, 0位置的数值相同时按照up在前, down在后的顺序排序
 * 如果0和1位置的信息都一样, 则无所谓谁前谁后
 */
public class TheSkylineProblem {

    public static class YAxisVaryInfo implements Comparable<YAxisVaryInfo> {
        // 在x轴上的位置
        public int x;
        // true代表y从0上升至h, false代表y从h下降至0
        public boolean up;
        // y轴变化的高度
        public int h;

        public YAxisVaryInfo(int x, boolean up, int h) {
            this.x = x;
            this.up = up;
            this.h = h;
        }

        @Override
        public int compareTo(YAxisVaryInfo o) {
            if (this.x == o.x && this.up) {
                // 如果一维数据相等, 变化是up的排前面
                return 1;
            }
            // 一位数据不相等则按x升序排序
            return this.x - o.x;
        }
    }

    public static List<int[]> getContourLine(int[][] inputs) {
        if (inputs == null || inputs.length == 0) {
            return null;
        }
        int n = inputs.length;
        // y轴变化信息是输入信息规模的2倍
        YAxisVaryInfo[] yAxisVaryInfos = new YAxisVaryInfo[n << 1];
        for (int i = 0; i < inputs.length; i++) {
            int[] info = inputs[i];
            // 代表y在x = info[0]的位置从0上升至info[2]
            yAxisVaryInfos[i << 1] = new YAxisVaryInfo(info[0], true, info[2]);
            // 代表y在x = info[1]的位置从info[2]下降至0
            yAxisVaryInfos[(i << 1) + 1] = new YAxisVaryInfo(info[1], false, info[2]);
        }
        // 排序, 按照x升序, x相同up在前
        Arrays.sort(yAxisVaryInfos);
        // 准备2个Map, 一个记录高度以及出现的次数, TreeMap默认按照key升序排列
        TreeMap<Integer, Integer> times = new TreeMap<>();
        // 一个记录每个x的最高高度
        TreeMap<Integer, Integer> highest = new TreeMap<>();
        for (YAxisVaryInfo yAxisVaryInfo : yAxisVaryInfos) {
            if (yAxisVaryInfo.up) {
                times.put(yAxisVaryInfo.h,
                        times.getOrDefault(yAxisVaryInfo.h, 0) + 1);
            } else {
                Integer frequency = times.get(yAxisVaryInfo.h);
                if (frequency == 1) {
                    // 如果高度的次数只有1, 直接移除
                    times.remove(yAxisVaryInfo.h);
                } else {
                    // 否则高度出现次数 - 1
                    times.put(yAxisVaryInfo.h, frequency - 1);
                }
            }
            // 根据高度出现频率中的最大高度设置当前x的最大高度
            highest.put(yAxisVaryInfo.x, times.isEmpty() ? 0 : times.lastKey());
        }
        // 新轮廓线的开始位置
        int start = 0;
        // 当前x之前的最大高度, 一开始为0
        int preHeight = 0;
        List<int[]> res = new ArrayList<>();
        // 根据highest中存储的每个x轴位置的最高y轴信息生成轮廓线信息
        for (Map.Entry<Integer, Integer> xHeightInfo : highest.entrySet()) {
            // 当前x坐标
            int curX = xHeightInfo.getKey();
            // 当前最大高度
            int curHeight = xHeightInfo.getValue();
            if (curHeight != preHeight) {
                /*
                当当前高度不等于之前的最高高度时, 说明高度在x轴位置发生了变化
                产生新的轮廓线, 开始位置为start, 结束位置为当前x的位置, 高度为之前的最大高度
                 */
                if (preHeight != 0) {
                    res.add(new int[]{start, curX, preHeight});
                }
                // 画完一条轮廓线后再更新下一个轮廓线的起始位置和记录之前高度
                start = curX;
                preHeight = curHeight;
            }
        }
        return res;
    }

}
