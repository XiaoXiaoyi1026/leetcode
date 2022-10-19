package com.xiaoxiaoyi;

import com.xiaoxiaoyi.greedy.Greedy;
import junit.framework.TestCase;

public class GreedyTest extends TestCase {

    public void testGreedy() {
        Greedy.Project[] projects = new Greedy.Project[10];
        projects[0] = new Greedy.Project(6, 10);
        projects[1] = new Greedy.Project(8, 12);
        projects[2] = new Greedy.Project(11, 13);
        projects[3] = new Greedy.Project(12, 14);
        projects[4] = new Greedy.Project(7, 8);
        projects[5] = new Greedy.Project(13, 15);
        projects[6] = new Greedy.Project(14, 16);
        projects[7] = new Greedy.Project(9, 10);
        projects[8] = new Greedy.Project(7, 12);
        projects[9] = new Greedy.Project(10, 11);
        System.out.println(Greedy.bestArrange(projects, 6));
    }

}
