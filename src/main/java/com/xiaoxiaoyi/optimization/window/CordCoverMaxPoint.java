package com.xiaoxiaoyi.optimization.window;

/**
 * @author xiaoxiaoyi
 * 求一根绳子能覆盖到的最多点的个数
 */
public class CordCoverMaxPoint {

    public static int cordCoverMaxPoint(int[] points, int cord) {
        if (points == null || points.length == 0 || cord <= 0) {
            // 如果点集为空或者没有点, 或者绳子的长度<=0, 直接返回0
            return 0;
        }
        // 从第1个点开始, tmp记录中间遍历得到的所有结果
        int left = 0, right = 0, res = 0, tmp = 0;
        // 窗口右指针到达最后一个点时结束遍历
        while (right != points.length) {
            if (points[right] - points[left] + 1 <= cord) {
                // 绳子可以覆盖到这个点
                tmp++;
            } else {
                // right到了绳子覆盖不到的点, 更新res
                res = Math.max(res, tmp);
                // left右移
                left++;
            }
            right++;
        }
        return res;
    }
}