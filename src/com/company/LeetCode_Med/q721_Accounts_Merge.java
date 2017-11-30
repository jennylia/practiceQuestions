package com.company.LeetCode_Med;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class q721_Accounts_Merge {
    public static void driver() {
//        accounts = {{"John", "johnsmith@mail.com", "john00@mail.com"}, {"John", "johnnybravo@mail.com"}, {"John", "johnsmith@mail.com", "john_newyork@mail.com"}, {"Mary", "mary@mail.com"}};
//        Output: {{"John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'},  {"John", "johnnybravo@mail.com"}, {"Mary", "mary@mail.com"}}
        List<List<String>> input = new ArrayList<List<String>>();
        ArrayList<String> john1 = new ArrayList<>();
        john1.add("John");
        john1.add("johnsmith@mail.com");
        john1.add("john00@mail.com");

        ArrayList<String> john2 = new ArrayList<>();
        john2.add("John");
        john2.add("johnnybravo@mail.com");

        ArrayList<String> john3 = new ArrayList<>();
        john3.add("John");
        john3.add("johnsmith@mail.com");
        john3.add("john_newyork@mail.com");

        ArrayList<String> mary = new ArrayList<>();
        mary.add("Mary");
        mary.add("mary@mail.com");

        input.add(john1);
        input.add(john2);
        input.add(john3);
        input.add(mary);

        List<List<String>> ans = accountsMerge(input);

        for (List list : ans) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }


    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        for (List li : accounts) {
            // for each account try to merge it with all the others.
            for (List lj : accounts) {
                if (li == lj) {
                    continue;
                }
                // find similarities and attempt merge
                if (li.get(0).equals(lj.get(0)) && (li.get(0).equals("modified") == false) && (lj.get(0).equals("modified") == false)) {
                    List<String> smlList = (li.size() > lj.size()) ? lj : li;
                    List<String> lgList = (li.size() > lj.size()) ? li : lj;
                    boolean merged = false;
                    while (merged == false) {
                        for (int i = 1; i < smlList.size(); i++) {
                            for (int j = 1; j < lgList.size(); j++) {
                                if (smlList.get(i).equals(lgList.get(j))) {
                                    // time to merge
                                    lj.remove(0);
                                    lj.remove(smlList.get(i));
                                    li.addAll(lj);
                                    // get rid of duplicates
                                    lj.add(0, "modified");
                                    merged = true;
                                }
                            }
                        }
                        //after run
                        merged = true;
                    }
//                    accounts.remove(lj);
                }
            }
        }
        for (List fl : accounts) {
            if (fl.get(0).equals("modified")) {
                accounts.remove(fl);
            } else {
                Collections.sort(fl);

            }
        }
        return accounts;
    }
}
