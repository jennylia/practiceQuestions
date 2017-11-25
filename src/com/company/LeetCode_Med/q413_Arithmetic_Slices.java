package com.company.LeetCode_Med;

public class q413_Arithmetic_Slices {
    public static void driver() {
        int[] arr1 = {1, 2, 3, 4};
        int test1 = numberOfArithmeticSlices(arr1);
        System.out.println(test1);

        int[] arr2 = {1, 2, 3, 8, 9, 10};
        int test2 = numberOfArithmeticSlices(arr2);
        System.out.println(test2);

    }

    public static int numberOfArithmeticSlices(int[] arr) {
        int[] subsquence = new int[arr.length];
        //0,1,2
        for (int i = 2; i < arr.length; i++) {
            if ((arr[i] - arr[i - 1]) == (arr[i - 1] - arr[i - 2])) {
                //subsequence.
                //if the number before you is 0, then that means you are the first one
                if (subsquence[i - 1] == 0) {
                    subsquence[i] = 3;
                } else {
                    subsquence[i] = subsquence[i - 1] + 1;
                }
            }
        }

        int acc = 0;

        for (int j : subsquence) {
            if (j >= 3) {
                acc += j - 3 + 1;
            }
        }

        return acc;
    }
}

//    https://leetcode.com/problems/arithmetic-slices/discuss/
//A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
//
//    For example, these are arithmetic sequence:
//
//            1, 3, 5, 7, 9
//            7, 7, 7, 7
//            3, -1, -5, -9
//    The following sequence is not arithmetic.
//
//            1, 1, 2, 5, 7
//
//    A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
//
//            A slice (P, Q) of array A is called arithmetic if the sequence:
//    A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
//
//    The function should return the number of arithmetic slices in the array A.
//
//
//            Example:
//
//    A = [1, 2, 3, 4]
//
//            return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
/*
* The idea is that if it is an arithmetic slice, it will contribute to all (n - 3) children to make them longer, and + 1 on itself...
* I.e.
* [1,2,3] -> spins of 1 new sequence, when we add 4
* [1,2,3,4] -> contribute to (4-3) = 1 child
*   [2,3,4] -> spin off a new one + 1, so n - 3 + 1
*
*   adding 5
* [1,2,3,4,5] -> contribute to (5-3) of the children, 2 of them
*   [2,3,4,5]
*     [3,4,5] -> spin off a new one
*
*   adding 6
* [1,2,3,4,5,6] -> contribute to (6-3) of its children, 3 of them
*   [2,3,4,5,6]
*     [3,4,5,6]
*       [4,5,6] -> spin off a new one
*
* The algorithm then becomes O(n).
* All we need to do is to traverse the array, find all the arithmetic slices, and calculate their length
* The second swipe to add their contribution to the total.
* */