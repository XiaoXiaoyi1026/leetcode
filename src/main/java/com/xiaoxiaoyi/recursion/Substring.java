package com.xiaoxiaoyi.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoxiaoyi
 * 打印字符的所有子串
 */
public class Substring {

    public static void substring(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0, new ArrayList<>());
        process2(chs, 0);
    }

    public static void process2(char[] str, int i) {
        if (i == str.length) {
            System.out.println(String.valueOf(str));
            return;
        }
        process2(str, i + 1);
        char tmp = str[i];
        str[i] = ' ';
        process2(str, i + 1);
        str[i] = tmp;
    }

    /**
     * 暴力递归
     *
     * @param chs 字符集
     * @param i   当前位置
     * @param res 结果数组
     */
    private static void process(char[] chs, int i, List<Character> res) {
        if (i == chs.length) {
            // 如果当前位置走到了最后则直接打印结果数组内容
            printList(res);
            return;
        }
        // 复制当前结果
        List<Character> resInclude = copyList(res);
        // 添加当前字符
        resInclude.add(chs[i]);
        // 递归下一层
        process(chs, i + 1, resInclude);
        // 不添加当前字符
        List<Character> resNotInclude = copyList(res);
        process(chs, i + 1, resNotInclude);
    }

    private static List<Character> copyList(List<Character> from) {
        return new ArrayList<>(from);
    }

    private static void printList(List<Character> res) {
        for (Character character : res) {
            System.out.print(character);
        }
        System.out.println();
    }

}
