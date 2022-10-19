package com.xiaoxiaoyi;

import com.xiaoxiaoyi.greedy.MaximumNumberOfMeetings;
import junit.framework.TestCase;

public class MaximumNumberOfMeetingsTest extends TestCase {

    public void testGreedy() {
        MaximumNumberOfMeetings.Project[] projects = new MaximumNumberOfMeetings.Project[10];
        projects[0] = new MaximumNumberOfMeetings.Project(6, 10);
        projects[1] = new MaximumNumberOfMeetings.Project(8, 12);
        projects[2] = new MaximumNumberOfMeetings.Project(11, 13);
        projects[3] = new MaximumNumberOfMeetings.Project(12, 14);
        projects[4] = new MaximumNumberOfMeetings.Project(7, 8);
        projects[5] = new MaximumNumberOfMeetings.Project(13, 15);
        projects[6] = new MaximumNumberOfMeetings.Project(14, 16);
        projects[7] = new MaximumNumberOfMeetings.Project(9, 10);
        projects[8] = new MaximumNumberOfMeetings.Project(7, 12);
        projects[9] = new MaximumNumberOfMeetings.Project(10, 11);
        System.out.println(MaximumNumberOfMeetings.bestArrange(projects, 6));
    }

}
