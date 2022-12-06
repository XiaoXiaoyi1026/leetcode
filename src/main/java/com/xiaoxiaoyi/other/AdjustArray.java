package com.xiaoxiaoyi.other;

/**
 * @author xiaoxiaoyi
 * 给定一个数组 arr, 要求你进行调整, 使得任意相邻的2个数相乘等于4的倍数
 * 返回该数组可不可以达成目标
 */
public class AdjustArray {

    public static boolean can(int[] arr) {
        int[] types = getTypeNumbers(arr);
        int oddNumber = types[0];
        int divisibleByFour = types[1];
        int evenNumberButNotDivisibleByFour = types[2];
        if (evenNumberButNotDivisibleByFour == 0) {
            // 如果偶数且不能被4整除的数为0个
            if (oddNumber == 0) {
                // 如果不存在奇数
                return true;
            } else if (oddNumber == 1) {
                // 如果奇数有1个, 那么能被4整除的数至少要有1个
                return divisibleByFour >= 1;
            } else {
                // 如果奇数的个数 > 1, 那么能被4整除的数至少要有奇数的个数-1个
                return divisibleByFour >= oddNumber - 1;
            }
        } else {
            if (evenNumberButNotDivisibleByFour == 1) {
                // 如果只有1个2因子, 那么这个2因子可以看成一个奇数
                oddNumber++;
            }
            /*
            如果偶数但不能被4整除的数不为0个, 那么把它们全部挪到一起
            它们在一起两两之间一定可以被4整除, 其边界处不能放奇数, 因为相乘不能被4整除
            所以边界处只能放4的倍数, 然后4奇4奇...这样放
             */
            if (oddNumber == 0) {
                // 如果此情况下没有奇数, 那么4的倍数的个数可以>=0
                return true;
            } else {
                // 1个或者多个奇数, 那么至少要有奇数个数的4的倍数
                return divisibleByFour >= oddNumber;
            }
        }
    }

    /**
     * 遍历数组, 获取3种类型数字的个数
     * type1: 奇数
     * type2: 偶数但能被4整除
     * type3: 偶数但不能被4整除
     */
    private static int[] getTypeNumbers(int[] arr) {
        int[] types = new int[3];
        for (int num : arr) {
            if ((num & 1) == 1) {
                // 奇数
                types[0]++;
            } else {
                // 偶数
                if (num % 4 == 0) {
                    // 能被4整除
                    types[1]++;
                } else {
                    types[2]++;
                }
            }
        }
        return types;
    }

}
