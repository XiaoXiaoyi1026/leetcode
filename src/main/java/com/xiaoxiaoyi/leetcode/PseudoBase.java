package com.xiaoxiaoyi.leetcode;

/**
 * @author xiaoxiaoyi
 * 普通K机制: 每一位上的范围0~K-1
 * 伪K进制: 每一位上的范围1~K
 */
public class PseudoBase {

    /**
     * @param base10 10进制数
     * @return 对应的伪26进制, 每一位上的范围(A~Z), A对应数字1, Z对应数字26
     */
    public static String pseudoBase26(int base10) {
        // 基数从1开始
        int base = 1, numberOfA = 0;
        while (base10 > base) {
            // A代表1, 这里是计算结果最多有多少位
            numberOfA++;
            // 当前位置上分配个A, 代表1个base的值, 分配完后base10应该减去base
            base10 -= base;
            // 下一位的base乘以26
            base *= 26;
        }
        // 记录结果
        StringBuilder res = new StringBuilder(numberOfA);
        // 从右往左计算结果的每一位
        for (int i = 0; i < numberOfA; i++) {
            // 基数每次除以26
            base /= 26;
            // 此时的base10是减完每一位A后的, 所以当前位对应的字符为A+base10/base
            res.append((char) (base10 / base + 'A'));
            // 算完当前位之后base10应该取余
            base10 %= base;
        }
        return String.valueOf(res);
    }

    /**
     * 伪26进制(A-Z)转10进制数字
     */
    public static int base10(String pseudoBase26) {
        int res = 0, base = 1;
        char[] chars = pseudoBase26.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            // A对应1 ... Z对应26
            res += (chars[i] - 'A' + 1) * base;
            base *= 26;
        }
        return res;
    }

}
