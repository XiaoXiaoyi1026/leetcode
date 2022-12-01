package com.xiaoxiaoyi.greedy;

/**
 * @author xiaoiaoyi
 * 洗衣机问题, 假设给你一个数组, 其中每个元素代表每个洗衣机拥有的衣服件数
 * 一个洗衣机同一时间只能往外扔一件衣服, 但是可以同时接收多件衣服, 且接收衣服和扔出衣服的操作可以同时进行
 * 问: 至少需要多少轮操作可以让每一个洗衣机的衣服数量一样多
 * 分析: 大条件是衣服总数 total = sum(all) 可以整除洗衣机数量 n (total % n == 0), 如果不行则无法达成目标, 返回无穷大
 * 可以整除(total % n == 0)则可以知道最终每个洗衣机应该有self = total / n件衣服
 * 讨论 i 位置的洗衣机情况: 左边需要的衣服总数leftNeed = sum(left)  - (i * self), 右边需要的衣服总数rightNeed = sum(righht)-((n-i-1)*self)
 * case 1: leftNeed < 0 && rightNeed < 0, 则说明i位置上的衣服过多, 因为i位置1次只能扔出1件衣服
 * 所以i位置需要向外扔至少 abs(leftNeed) + abs(rightNeed)次
 * case 2: leftNeed > 0 && rightNeed > 0, 由于可以同时接受左右两边的衣服
 * 所以i位置至少需要接收max(leftNeed, rightNeed)次
 * case 3: leftNeed < 0 && rightNeed > 0 || leftNeed > 0 && rightNeed < 0
 * i位置在这两种情况下都必须在接收衣服的同时向外扔, 所以至少需要max(leftNeed, rightNeed)次, 同case2
 * 根据上述情况可以计算出每个位置至少需要的轮数, 其中的最大值就是答案
 * 因为当最大值被满足时, 其它比它更小的需求也一定可以的得到满足
 */
public class PackingMachine {

    public static int minimumNumberOfAdjustments(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % nums.length != 0) {
            return -1;
        }
        int n = nums.length, ans = 0, self = sum / n;
        // 左侧已经遍历过的部分累加和leftSum
        int leftNeed, leftSum = 0, rightNeed;
        for (int i = 0; i < n; i++) {
            //  左侧需要的 = 左侧本来有的 - 目标, 负数代表需要接收, 正数代表要往外扔
            leftNeed = leftSum - (i * self);
            // 右侧需要的, rightSum = sum - leftSum - nums[i];
            rightNeed = (sum - leftSum - nums[i]) - ((n - i - 1) * self);
            int tmp;
            if (leftNeed < 0 && rightNeed < 0) {
                // case 1, 左右都需要衣服
                tmp = Math.abs(leftNeed) + Math.abs(rightNeed);
            } else {
                // case 2 3
                tmp = Math.max(Math.abs(leftNeed), Math.abs(rightNeed));
            }
            ans = Math.max(ans, tmp);
            // 下一轮的左侧和
            leftSum += nums[i];
        }
        return ans;
    }

}