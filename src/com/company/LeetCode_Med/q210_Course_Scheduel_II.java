package com.company.LeetCode_Med;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class q210_Course_Scheduel_II {

    public static void driver() {

        int num = 4;
        int[][] prereq = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] ans = findOrder(num, prereq);
        System.out.println(ans);
    }


    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // build the graph
        HashMap<Integer, LinkedList<Integer>> graph = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int src = prerequisites[i][1];
            int dst = prerequisites[i][0];
            LinkedList<Integer> children;
            if (graph.containsKey(src)) {
                children = graph.get(src);
            } else {
                children = new LinkedList<>();
            }
            children.add(dst);
            graph.put(src, children);
        }

        // recurse
        HashSet<Integer> seen = new HashSet<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (Integer i : graph.keySet()) {
            //current graph
            // haven't been here
            buildlist(i, seen, ans, graph);
        }
        int[] ans2 = new int[numCourses];

        for (int i = 0; i < ans.size() - 1; i++) {
            ans2[numCourses - 1 - i] = ans.get(i);
        }
        return ans2;
    }

    public static void buildlist(Integer current, HashSet<Integer> seen, ArrayList<Integer> ans, HashMap<Integer, LinkedList<Integer>> graph) {
        //current graph
        if (seen.contains(current)) {
            return;
        } else {
            // haven't been here
            seen.add(current);
            LinkedList<Integer> children = graph.get(current);
            if (children != null) {
                for (Integer c : children) {
                    buildlist(c, seen, ans, graph);
                }
            }
        }
        ans.add(current);
    }
}



/*Notes
* https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/TopologicalSort.java
* https://www.youtube.com/watch?v=ddTC4Zovtbc
*
* Mr Tushar Roy has this information very well done, with a deque(childless nodes) and a set (visited)
*
* https://leetcode.com/problems/course-schedule-ii/description/
*
* Original question.*/