package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 模3问题
 * 给定一个数组[1, 12, 123, 1234, ....]
 * 求第l到第r个数有多少个可以被3整除
 */
public class Mod3 {

    /**
     * 由数组规律可知数组第i项是以i+1结尾的数字, l代表的是第l个数, 而不是数组第l项
     * 一个数是否可以整除3, 取决于各位数之和能否整除3
     * 相反, 各位数之和能否整除3也可以转化为这个数能否整除3
     */
    public static int mod3(int l, int r) {
        // 计算 1 + 2 + ... + l - 1的和
        int sum = ((1 + (l - 1)) * (l - 1)) >> 1;
        int ans = 0;
        for (int i = l; i <= r; i++) {
            sum += i;
            if (sum % 3 == 0) {
                ans++;
            }
        }
        return ans;
    }

}
