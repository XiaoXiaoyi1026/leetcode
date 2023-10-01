package com.xiaoxiaoyi.leetcode;

import com.xiaoxiaoyi.utils.RandomUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * 给定怪兽血量为HP
 * 第i回合如果用刀砍, 则立即对怪兽造成cuts[i]的伤害
 * 第i回合如果用毒, 则会让怪兽中poisons[i]的毒, 怪兽后续每回合都会掉poisons[i]的血
 * 返回你能杀死怪兽的最少回合数
 * 回合数从1开始, 如果当前回合 > cuts.length, 则你无法行动,
 * 此时如果怪兽身上存在毒, 则怪兽会被慢慢毒死
 */
public class CutOrPoison {

    static class Monster {
        int hp;
        int poison;

        Monster(int hp, int poison) {
            this.hp = hp;
            this.poison = poison;
        }

        Monster(int hp) {
            this(hp, 0);
        }

        Monster copyAndPoison(int poison) {
            return new Monster(hp, this.poison + poison);
        }

        Monster copyAndCut(int cut) {
            return new Monster(hp - cut, poison);
        }
    }

    public static int minimumRounds(int hp, int[] cuts, int[] poisons) {
        if (hp <= 0) {
            return 0;
        }
        Monster monster = new Monster(hp);
        return process(monster, cuts, poisons, 1);
    }

    private static int process(@NotNull Monster monster, int[] cuts, int[] poisons, int curRound) {
        if (monster.hp <= 0) {
            return curRound - 1;
        }
        monster.hp -= monster.poison;
        if (monster.hp <= 0) {
            return curRound;
        }
        int ans;
        if (curRound <= cuts.length) {
            // 选择1: 直接砍怪兽
            ans = process(monster.copyAndCut(cuts[curRound - 1]), cuts, poisons, curRound + 1);
            // 选择2: 用毒毒怪兽
            ans = Math.min(ans, process(monster.copyAndPoison(poisons[curRound - 1]), cuts, poisons, curRound + 1));
        } else {
            if (monster.poison > 0) {
                // 选择3: 等待怪兽自己被毒死
                ans = process(monster, cuts, poisons, curRound + 1);
            } else {
                ans = Integer.MAX_VALUE;
            }
        }
        return ans;
    }

    public static int minimumRounds2(int hp, int[] cuts, int[] poisons) {
        if (hp <= 0) {
            return 0;
        }
        int minRound = 1;
        int maxRound = hp + 1;
        int ans = Integer.MAX_VALUE;
        int mid;
        while (minRound <= maxRound) {
            mid = minRound + ((maxRound - minRound) >> 1);
            if (canKill(hp, cuts, poisons, mid)) {
                maxRound = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                minRound = mid + 1;
            }
        }
        return ans;
    }

    private static boolean canKill(int hp, int[] cuts, int[] poisons, int targetRound) {
        if (targetRound <= 0) {
            return false;
        }
        if (hp <= 0) {
            return true;
        }
        int n = Math.min(cuts.length, targetRound);
        for (int curRound = 1; curRound <= n; curRound++) {
            hp -= Math.max(cuts[curRound - 1], poisons[curRound - 1] * (targetRound - curRound));
            if (hp <= 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int maxHP = 100;
        int maxRounds = 40;
        int hp;
        int rounds;
        int[] cuts;
        int[] poisons;
        int ans1;
        int ans2;
        System.out.println("测试开始!!!");
        for (int i = 0; i < testTimes; i++) {
            hp = 1 + RandomUtils.nextInt(maxHP);
            rounds = 1 + RandomUtils.nextInt(maxRounds);
            cuts = RandomUtils.array(rounds, maxHP, 1);
            poisons = RandomUtils.array(rounds, maxHP, 1);
            ans1 = minimumRounds(hp, cuts, poisons);
            ans2 = minimumRounds2(hp, cuts, poisons);
            if (ans1 != ans2) {
                System.out.println("出错了!!!");
                System.out.println("hp = " + hp);
                System.out.println("rounds = " + rounds);
                System.out.println("cuts = " + Arrays.toString(cuts));
                System.out.println("poisons = " + Arrays.toString(poisons));
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                break; // 不再继续测试了!!!
            }
        }
        System.out.println("测试结束!!!");
    }

}
