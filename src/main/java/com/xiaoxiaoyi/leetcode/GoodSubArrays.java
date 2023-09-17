package com.xiaoxiaoyi.leetcode;

/**
 * 好子集的定义是: 集合中每个数的乘积拆分出的质数因子无重复
 * 比如集合[1, 2, 3, 4]
 * 存在好子集: [2], [3], [2, 3], [1, 2], [1, 3], [1, 2, 3]
 * 集合的数字数量在[1, 10^8]
 * 每个数字的范围是[0, 30]
 */
public class GoodSubArrays {

    private GoodSubArrays() {
    }

    public static int getGoodSubArrays(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // [0, 30]范围内的质数因子有: 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 一共10个, 可以用一个int类型的数字记录分布情况, 比如数字30的质数因子有: 2, 3, 5, 对应10位二进制数就是00000000111 = 7, 如果数字含有重复质数(比如4 = 2 * 2), 则为0
        int[] primeNumbers = {0, 0, 1, 2, 0, 4, 3, 8, 0, 0, 5, 16, 0, 32, 9, 6, 0, 64, 0, 128, 0, 10, 17, 256, 0, 0, 33, 0, 0, 512, 7};
        // 记录[0, 30]范围内的已选质数分布情况
        int[] primeNumberStatus = new int[(1 << 10)];
        // 一个号码都没选中的情况(空集), 开始认为是一个有效的
        primeNumberStatus[0] = 1;
        // 先统计每个数的出现次数
        int[] numberCount = new int[31];
        for (int num : nums) {
            numberCount[num]++;
        }
        int mod = 1000000007;
        // 单独计算1的出现次数情况
        for (int i = 0; i < numberCount[1]; i++) {
            // 集合中每出现一个1, 结果就要 * 2
            primeNumberStatus[0] = (primeNumberStatus[0] << 1) % mod;
        }
        for (int num = 2; num < 31; num++) {
            if (primeNumbers[num] != 0 && numberCount[num] > 0) {
                // 如果数字本身不包含重复质数因子, 且在原始数组中出现过, 才能选
                for (int curPrimeStatus = 0; curPrimeStatus < primeNumberStatus.length; curPrimeStatus++) {
                    if ((curPrimeStatus & primeNumbers[num]) == 0) {
                        // 当前数的质数分布不能和已选质数状态有交集
                        primeNumberStatus[curPrimeStatus | primeNumbers[num]] = (int) (primeNumberStatus[curPrimeStatus | primeNumbers[num]] + ((long) primeNumberStatus[curPrimeStatus] * numberCount[num])) % mod;
                    }
                }
            }
        }
        int ans = 0;
        for (int curPrimeStatus = 1; curPrimeStatus < primeNumberStatus.length; curPrimeStatus++) {
            // 计算至少包含一个质数因子的每种状态的总和
            ans = (ans + primeNumberStatus[curPrimeStatus]) % mod;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(getGoodSubArrays(nums));
    }

}
