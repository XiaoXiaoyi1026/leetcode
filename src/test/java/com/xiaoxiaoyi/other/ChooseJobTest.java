package com.xiaoxiaoyi.other;

import junit.framework.TestCase;

import java.util.Arrays;

public class ChooseJobTest extends TestCase {

    public void testChooseJob() {
        ChooseJob.Job[] jobs = new ChooseJob.Job[]{
                new ChooseJob.Job(3, 5),
                new ChooseJob.Job(2, 7),
                new ChooseJob.Job(9, 100),
                new ChooseJob.Job(1, 4),
                new ChooseJob.Job(2, 6),
                new ChooseJob.Job(3, 3),
                new ChooseJob.Job(1, 1),
                new ChooseJob.Job(2, 8)
        };
        int[] ability = {0, 1, 2, 3};
        System.out.println(Arrays.toString(ChooseJob.chooseJob(jobs, ability)));
    }

}
