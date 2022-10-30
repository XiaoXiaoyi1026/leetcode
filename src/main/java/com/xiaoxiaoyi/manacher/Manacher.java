package com.xiaoxiaoyi.manacher;

/**
 * @author xiaoxiaoyi
 * 回文直径/半径
 * R: 历史回文串的最右位置
 * C: 取得R时的回文串的中心位置 C <= R
 * 情况1: 遍历到的当前位置cur < R 则选择 暴力向左右两边扩 此情况无法优化
 * 情况2: 因为C已经走过了，所以cur一定大于C，即当前位置满足 C < cur <= R
 * 取cur关于C的对称点为cur', 有L < cur' < C < cur < R
 * 根据cur'上的回文状况进行分类:
 * 1. cur'根据回文半径求出的的回文范围在L和R之间, 此时cur的回文半径和cur'的回文半径一样
 * 2. cur'的回文范围超出了L~R, 此时cur的回文半径就等于R - cur + 1
 * 3. cur'的回文左边界刚好 = L，则需要判断R的右边还能不能扩
 */
public class Manacher {

    /**
     * 把原始串变成处理串
     *
     * @param str 字符串
     * @return 处理过后的串
     */
    public static char[] manacherString(String str) {
        char[] strChar = str.toCharArray();
        char[] res = new char[2 * str.length() + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : strChar[i >> 1];
        }
        return res;
    }

    /**
     * 求str的最大回文子串长度
     *
     * @param str 字符串
     * @return 最大回文子串长度
     */
    public static int maxPalindromicRadiusLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        // 处理后的字符串 123 -> #1#2#3#
        char[] afterProcessingStr = manacherString(str);
        // 回文半径数组
        int[] palindromicRadiusArray = new int[afterProcessingStr.length];
        // 初始回文中心
        int center = -1;
        // 初始回文右边界的再右一个位置，真正的回文右边界为R-1
        int right = -1;
        // 扩出来的最大值
        int max = Integer.MIN_VALUE;
        // 求每个位置的回文半径
        for (int cur = 0; cur != afterProcessingStr.length; cur++) {
            // 当前位置cur > right时，p[i]至少为1,
            // i在right内时, p[i]至少为right - cur或者p[center - (cur - center)]对称点中的最小值
            // 这里就是求p[i]的最小值
            palindromicRadiusArray[cur] = right > cur ?
                    Math.min(palindromicRadiusArray[(center << 1) - cur]
                            , right - cur) : 1;

            // 记录当前回文半径
            int curPalindromicRadius = palindromicRadiusArray[cur];
            // 4种情况都尝试往外扩，限制为当前位置 + 当前位置的回文半径不超过字符串长度而且当前位置 - 当前位置的回文半径也不小于0
            while (cur + curPalindromicRadius < afterProcessingStr.length
                    && cur - curPalindromicRadius > -1) {
                if (afterProcessingStr[cur + curPalindromicRadius]
                        == afterProcessingStr[cur - curPalindromicRadius]) {
                    // 能扩则更新当前回文半径
                    curPalindromicRadius = ++palindromicRadiusArray[cur];
                } else {
                    // 不能扩直接退出
                    break;
                }

                // 判断当前右边界是否要更新
                if (cur + curPalindromicRadius > right) {
                    right = cur + curPalindromicRadius;
                    center = cur;
                }
                // 记录一下最大的回文半径
                max = Math.max(max, palindromicRadiusArray[cur]);
            }
        }
        // 原串的最大回文半径为处理后的最大回文半径 - 1
        return max - 1;
    }
}
