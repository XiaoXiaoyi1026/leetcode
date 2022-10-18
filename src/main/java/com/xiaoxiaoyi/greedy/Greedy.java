package com.xiaoxiaoyi.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author xiaoxiaoyi
 * 贪心
 */
public class Greedy {

    public static class Project {
        public int startTime;
        public int endTime;

        public Project(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static class ProjectComparator implements Comparator<Project> {

        @Override
        public int compare(Project o1, Project o2) {
            // 按照结束时间升序排序
            return o1.endTime - o2.endTime;
        }
    }

    /**
     * 贪心算法
     *
     * @param projects  所有项目
     * @param timePoint 时间指针
     * @return 最优情况下能够执行多少项目
     */
    public static int bestArrange(Project[] projects, int timePoint) {
        Project[] thisProjects = new Project[projects.length];
        System.arraycopy(projects, 0, thisProjects, 0, projects.length);
        // 按照endTime升序排序
        Arrays.sort(thisProjects, new Greedy.ProjectComparator());
        int result = 0;
        for (Project project : thisProjects) {
            // 如果当前时间小于等于项目的开始时间
            if (timePoint <= project.startTime) {
                // 安排这个项目
                result++;
                // 当前时间变为当前项目的结束时间
                timePoint = project.endTime;
            }
        }
        return result;
    }

}
