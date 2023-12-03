package com.xiaoxiaoyi.homework;

/**
 * 获取字符串并输出
 */
public class GetStringAndOutput {

    /**
     * @param inputString   输入字符串
     * @param outputStrings 输出字符串数组
     */
    public static void getAndOutput(String inputString, String... outputStrings) {
        if (inputString == null || inputString.isEmpty()) {
            return;
        }
        int indexOfOutputString; // 输出字符串在输入字符串中的位置
        int lengthOfOutputString; // 输出字符串的长度
        for (String outputString : outputStrings) {
            indexOfOutputString = inputString.indexOf(outputString);
            if (indexOfOutputString != -1) {
                lengthOfOutputString = outputString.length();
                System.out.println(inputString.substring(indexOfOutputString, indexOfOutputString + lengthOfOutputString));
            }
        }
    }

    public static void main(String[] args) {
        String inputString = "我精通的编程语言时JavaScript。我已经学习了这门语言有5年，并且和它用PHP联合做开发也有5年。";
        String[] outputStrings = {"JavaScript", "PHP"};
        getAndOutput(inputString, outputStrings);
    }

}
