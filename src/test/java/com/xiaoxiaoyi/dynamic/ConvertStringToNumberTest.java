package com.xiaoxiaoyi.dynamic;

import junit.framework.TestCase;

public class ConvertStringToNumberTest extends TestCase {

    private String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int r = (int) (Math.random() * 10);
            if (r < 1) {
                stringBuilder.append('-');
            } else if (r < 2) {
                stringBuilder.append(".");
            } else {
                stringBuilder.append((int) (Math.random() * 10));
            }
        }
        return String.valueOf(stringBuilder);
    }

    public void testIsNumber() {
        String str;
        for (int i = 0; i < 20; i++) {
            str = generateRandomString((int) (Math.random() *10));
            System.out.println(str);
            boolean ans = ConvertStringToNumber.isNumber(str);
            System.out.println("ans: " + ans);
            if (ans) {
                try {
                    System.out.println("convert: " + ConvertStringToNumber.convertToInt(str));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(ConvertStringToNumber.convertToInt(String.valueOf(Integer.MIN_VALUE)));
        System.out.println(ConvertStringToNumber.convertToInt("2147483648"));
    }

}
