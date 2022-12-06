package com.xiaoxiaoyi.kmp;

/**
 * @author xiaoxiaoyi
 * 判断一个字符串是否是另一个字符串的旋转词
 */
public class SpinWords {

    public static boolean spinWords(String str1, String str2) {
        if (str1.length() != str2.length()) {
            // 如果两个字符串长度不相等, 那么一定不是
            return false;
        }
        // 否则看str2是否是str1+str1的子串即可
        return Kmp.getIndexOf(str1 + str1, str2) != -1;
    }

}
