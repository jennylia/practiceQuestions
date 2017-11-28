package com.company.LeetCode_Med;

public class q210_Course_Scheduel_II {

    public static void driver() {

        int num = 2;
        int[][] prereq = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] ans = findOrder(num, prereq);
        System.out.println(ans);
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] courseList = new int[1000];
        return courseList;

    }
}
