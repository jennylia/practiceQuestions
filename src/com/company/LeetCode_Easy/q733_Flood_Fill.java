package com.company.LeetCode_Easy;

public class q733_Flood_Fill {
    public static void driver() {

        int[][] image = [[1,1,1],[1,1,0],[1,0,1]];

    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        int oldColor = image[sr][sc];
        if (oldColor == newColor) {
            // nothing to change
            return image;
        }
        helper(image, sr, sc, oldColor, newColor);
        return image;
    }

    public static void helper(int[][] image, int sr, int sc, int oldColor, int newColor) {
        // if they are within the bounds
        if (sr < image.length - 1 && sr < image[0].length - 1) {
            if (image[sr][sc] == oldColor) {
                // recurse
                image[sr][sc] = newColor;
                helper(image, sr + 1, sc, oldColor, newColor);
                helper(image, sr, sc + 1, oldColor, newColor);
                helper(image, sr - 1, sc, oldColor, newColor);
                helper(image, sr, sc - 1, oldColor, newColor);
            }
        }
    }
}
