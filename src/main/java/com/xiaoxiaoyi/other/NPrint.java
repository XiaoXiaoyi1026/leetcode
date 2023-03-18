package com.xiaoxiaoyi.other;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description N型打印
 * @date 3/18/2023 12:35 PM
 */
public class NPrint {

    public static void main(String[] args) {
        System.out.println(convert("AB", 1));
    }

    public static String convert(String s, int numRows) {
        if (s == null) {
            return null;
        }
        int n = s.length();
        if (numRows >= n || numRows == 1) {
            return s;
        }
        int zhouQi = (numRows - 1) << 1;
        char[] res = new char[n];
        int fill = 0;
        for (int hang = 0; hang < numRows; hang++) {
            for (int lie = hang, nextLieStart = zhouQi; lie < n; lie += zhouQi, nextLieStart += zhouQi) {
                res[fill++] = s.charAt(lie);
                if (hang >= 1 && hang <= numRows - 2 && nextLieStart - hang < n) {
                    res[fill++] = s.charAt(nextLieStart - hang);
                }
            }
        }
        return String.valueOf(res);
    }

}
