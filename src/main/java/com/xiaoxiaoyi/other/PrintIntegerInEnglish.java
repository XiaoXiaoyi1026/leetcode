package com.xiaoxiaoyi.other;

/**
 * @author xiaoxiaooyi
 * 用英文打印数字
 */
public class PrintIntegerInEnglish {

    public static String print(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder res = new StringBuilder();
        if (num < 0) {
            res.append("Navigate ");
            if (num == Integer.MIN_VALUE) {
                // 如果是系统最小值
                String max = print(Integer.MAX_VALUE);
                return String.valueOf(
                        // 把最大值的最后一个Seven替换成Eight即可
                        res.append(max, 0, max.length() - "Seven ".length() - 1)
                                .append("Eight ")
                );
            }
            // 将num变为正数
            num = -num;
        }
        // java最大整型21亿, billion = 10亿
        int high = 1000000000, highIndex = 0;
        String[] names = {"Billion", "Million", "Thousand", ""};
        while (num != 0) {
            int cur = num / high;
            num %= high;
            if (cur != 0) {
                res.append(print1To999(cur))
                        .append(names[highIndex])
                        .append(num == 0 ? " " : ", ");
            }
            high /= 1000;
            highIndex++;
        }
        return String.valueOf(res);
    }

    public static String print1To19(int num) {
        if (num < 1 || num > 19) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        String[] names = {
                "One", "Two", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Eleven",
                "Twelve", "Thirteen", "Fourteen", "Fifteen",
                "Sixteen", "Seventeen", "Eighteen", "Nineteen"
        };
        return String.valueOf(res.append(names[num - 1])
                .append(" "));
    }

    public static String print1To99(int num) {
        if (num < 1 || num > 99) {
            return "";
        }
        if (num < 20) {
            return print1To19(num);
        }
        StringBuilder res = new StringBuilder();
        // 数字的十位
        int decade = num / 10;
        String[] decadeNames = {
                "Twenty", "Thirty", "Forty", "Fifty",
                "Sixty", "Seventy", "Eighty", "Ninety"
        };
        return String.valueOf(res.append(decadeNames[decade - 2])
                .append(" ").append(print1To19(num % 10)));
    }

    public static String print1To999(int num) {
        if (num < 1 || num > 999) {
            return "";
        }
        if (num < 100) {
            return print1To99(num);
        }
        StringBuilder res = new StringBuilder();
        int hundreds = num / 100;
        return String.valueOf(res.append(print1To19(hundreds))
                .append("Hundred and ")
                .append(print1To99(num % 100)));
    }
}
