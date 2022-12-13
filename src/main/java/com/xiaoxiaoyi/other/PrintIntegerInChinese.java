package com.xiaoxiaoyi.other;

/**
 * @author xiaoxiaoyi
 * 用中文打印整数
 */
public class PrintIntegerInChinese {

    public static String print(int number) {
        if (number == 0) {
            return "零";
        }
        StringBuilder res = new StringBuilder();
        if (number < 0) {
            res.append('负');
            if (number == Integer.MIN_VALUE) {
                String max = print(Integer.MAX_VALUE);
                return String.valueOf(
                        res.append(max, 0, max.length() - 2)
                                .append('八')
                );
            } else {
                number = -number;
            }
        }
        int oneHundredMillions = number / 100000000;
        int rest = number % 100000000;
        if (oneHundredMillions == 0) {
            // rest必不为0
            res.append(print1To99999999(rest));
        } else {
            // 最多21亿, 对于21来说, 百位为0
            res.append(print1To99(oneHundredMillions, true))
                    .append('亿');
            if (rest > 0) {
                // 如果rest小于1千万, 要在亿后面加一个'零'
                res.append(rest < 10000000 ? '零' : "")
                        .append(print1To99999999(rest));
            }
        }
        return String.valueOf(res);
    }

    public static String print1To9(int number) {
        if (number < 1 || number > 9) {
            return "";
        }
        char[] names = {
                '一', '二', '三', '四', '五', '六', '七', '八', '九'
        };
        return String.valueOf(names[number - 1]);
    }

    public static String print1To99(int number, boolean hundredsIsZero) {
        if (number < 1 || number > 99) {
            return "";
        }
        if (number < 10) {
            return print1To9(number);
        }
        // 十位数字
        int decade = number / 10;
        StringBuilder res = new StringBuilder();
        return String.valueOf(
                // 当十位为1且百位为0时, 百位不为0时十位一定要打印
                res.append(decade == 1 && hundredsIsZero ?
                                "" : print1To9(decade))
                        .append('十')
                        .append(print1To9(number % 10))
        );
    }

    public static String print1To999(int number) {
        if (number < 1 || number > 999) {
            return "";
        }
        if (number < 100) {
            return print1To99(number, true);
        }
        StringBuilder res = new StringBuilder();
        // 百位数字
        int hundreds = number / 100;
        res.append(print1To9(hundreds)).append('百');
        int rest = number % 100;
        if (rest > 0) {
            if (rest > 9) {
                res.append(print1To99(rest, false));
            } else {
                res.append('零').append(print1To9(rest));
            }
        }
        return String.valueOf(res);
    }

    public static String print1To9999(int number) {
        if (number < 1 || number > 9999) {
            return "";
        }
        if (number < 1000) {
            return print1To999(number);
        }
        StringBuilder res = new StringBuilder();
        int thousands = number / 1000;
        res.append(print1To9(thousands)).append('千');
        int rest = number % 1000;
        if (rest > 0) {
            if (rest > 99) {
                res.append(print1To999(rest));
            } else {
                res.append('零').append(print1To99(rest, true));
            }
        }
        return String.valueOf(res);
    }

    public static String print1To99999999(int number) {
        if (number < 1 || number > 99999999) {
            return "";
        }
        if (number < 10000) {
            return print1To9999(number);
        }
        StringBuilder res = new StringBuilder();
        int tenThousands = number / 10000;
        res.append(print1To9999(tenThousands)).append('万');
        int rest = number % 10000;
        if (rest > 0) {
            if (rest < 1000) {
                res.append(print1To999(rest));
            } else {
                res.append(print1To9999(rest));
            }
        }
        return String.valueOf(res);
    }
}
