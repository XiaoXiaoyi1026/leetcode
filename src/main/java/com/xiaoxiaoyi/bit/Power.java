package com.xiaoxiaoyi.bit;

/**
 * @author xiaoxiaoyi
 * 判断一个数是否是2/4的幂
 */
public class Power {

    public static boolean is2Power(int num) {
        return (num & (num - 1)) == 0;
    }

    public static boolean is4Power(int num) {
        //       首先要是2的幂(即只有1个1)   其次与01010101....01相与不为0
        return (num & (num - 1)) == 0 && (num & 0x55555555) != 0;
    }

}
