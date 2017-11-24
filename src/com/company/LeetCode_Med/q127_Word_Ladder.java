package com.company.LeetCode_Med;


import java.util.*;

public class q127_Word_Ladder {

    public static void driver() {
        // TODO Auto-generated method stub
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log", "cog"};
        HashSet<String> words = new HashSet<String>(Arrays.asList(wordList));

        int ans = ladderLength(beginWord, endWord, wordList);
        int ans_o = ladderLength(beginWord, endWord, words);

        System.out.println(ans);
        System.out.println(ans_o);


        //Example 2
//        String beginWord = "TOON";
//        String endWord = "PLEA";
//        String[] wordList = {"POON", "KOON", "MOON", "PLEE", "SAME", "POIE", "PLEA", "PLIE", "POIN"};
//
//        HashSet<String> words = new HashSet<String>(Arrays.asList(wordList));
//
//        int ans2 = ladderLength(beginWord, endWord, wordList);
//        int ans3 = ladderLength(beginWord, endWord, words);
//
//        System.out.println(ans2);
//        System.out.println(ans3); // 8?


    }


    public static int ladderLength(String start, String end, HashSet<String> dict) {
        int acc = -1; // not sure if neede
        // visited elements
        HashSet<String> visited = new HashSet<String>();
        // To visit queue
        LinkedList<Element> queue = new LinkedList<Element>();
        // add the start element to the queue;
        queue.add(new Element(start, 0));

        // while it's not empty
        while (queue.isEmpty() == false) {
            Element current = queue.poll(); // get the first one off the queue
            if (current.value.equals(end)) return current.count + 1;
            visited.add(current.value); // just visited

            // try to add other ones in the queue
            char[] arr = current.value.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                // copy the original value at this index
                char init = arr[i];
                for (char c = 'A'; c <= 'z'; c++) {
                    arr[i] = c;
                    String tmp = new String(arr);
                    if (visited.contains(tmp) == false && dict.contains(tmp)) {
                        queue.add(new Element(new String(arr), current.count + 1));
                    }
                }
                // after
                arr[i] = init;
            }

        }

        return acc;

    }

    static class Element {
        //string
        String value;
        //the counts from start string to current string
        int count;

        public Element(String string, int count) {
            this.value = string;
            this.count = count;
        }
    }


    public static int ladderLength(String beginWord, String endWord, String[] wordList) {
        int acc = 0;
        List<String> visited = new ArrayList<String>();
        List<String> candidates = findCandidates(beginWord, visited, wordList);

        String currentWord = beginWord;
        // find candidate
        while (currentWord.equals(endWord) == false && visited.size() != wordList.length) {
            acc++;
            if (visited.contains(currentWord) == false) {
                visited.add(currentWord);
            }

            // add the next available words
            List<String> nextWords = findCandidates(currentWord, visited, wordList);
            if (nextWords.isEmpty()) {
                // no future steps, back track
                acc--;
            } else {
                candidates.addAll(nextWords);
                // dedup
                Set<String> hs = new HashSet<>();
                hs.addAll(candidates);
                candidates.clear();
                candidates.addAll(hs);
            }
            currentWord = candidates.remove(0);
        }

        return acc;
    }

    public static List<String> findCandidates(String beginWord, List<String> visited, String[] wordList) {
        // TODO Auto-generated method stub
        List<String> candidates = new ArrayList<String>();

        // check if 1 character apart
        for (String w : wordList) {
            int diff = 0;
            if (visited.contains(w) == false) {
                for (int i = 0; i < w.length(); i++) {
                    if (beginWord.charAt(i) != w.charAt(i)) {
                        diff++;
                    }
                }
                if (diff <= 1) {
                    candidates.add(w);
                }
            }
        }
        return candidates;
    }
}

/*
* Resources:
* https://github.com/paopao2/Algorithm-Practice/blob/master/Word%20Ladder.java
* Video: https://www.youtube.com/watch?v=6pIC20wCm20
* Good explaination of O(N * M * N) for string[] and O(N * M * 1) for set
*
* - Queue can be up to N words long
* - For each iteration of the queues, we have to check for
*   M possibilities of next words. for each m of these M possibilties, check if they exist in dic.
*       using a list, we traverse through each N words in the list, see if it exist so (N)
*       using a set, constant time, does the dictionary contain this new word? (1)
* */