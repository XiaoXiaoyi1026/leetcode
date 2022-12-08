package com.xiaoxiaoyi.other;

import java.util.*;

/**
 * @author xiaoxiaoyi
 * 给定一个工作数组, 每个工作包含2个属性, 一个是能力要求, 一个是薪资报酬
 * 现在给定几个人的工作能力, 帮他们选最优的工作(能力 >= 工作能力要求 && 薪资报酬最高)
 * 返回他们每个人能够得到的最大薪水
 */
public class ChooseJob {

    public static class Job implements Comparable<Job> {
        public int abilityRequirements;
        public int salary;

        public Job(int abilityRequirements, int salary) {
            this.abilityRequirements = abilityRequirements;
            this.salary = salary;
        }

        @Override
        public int compareTo(Job o) {
            if (this.abilityRequirements == o.abilityRequirements) {
                // 如果能力要求一样, 则按工资降序排序
                return o.salary - this.salary;
            }
            // 能力要求不一样则按能力要求升序
            return this.abilityRequirements - o.abilityRequirements;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "abilityRequirements=" + abilityRequirements +
                    ", salary=" + salary +
                    '}';
        }
    }

    public static int[] chooseJob(Job[] jobs, int[] ability) {
        // 排序
        Arrays.sort(jobs);
        // 有序表, 相同的工作要求能力只保留第一个
        TreeMap<Integer, Integer> jobMap = new TreeMap<>();
        jobMap.put(jobs[0].abilityRequirements, jobs[0].salary);
        Job lastAddJob = jobs[0];
        for (Job job : jobs) {
            if (job.abilityRequirements != lastAddJob.abilityRequirements) {
                // 只保留每一个能力要求相同的工作中薪资最高的
                jobMap.put(job.abilityRequirements, job.salary);
                lastAddJob = job;
            }
        }
        // jobList中的工作是难度递增薪资也递增的, 只有这些工作需要考虑
        int[] ans = new int[ability.length];
        for (int i = 0; i < ans.length; i++) {
            // 从map中找到小于等于我能力的工作
            Integer jobRequirements = jobMap.floorKey(ability[i]);
            ans[i] = jobRequirements == null ? 0 : jobMap.get(jobRequirements);
        }
        return ans;
    }

}
