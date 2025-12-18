package com.company.lcspattern;

public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        String s = "abghbgbha";

        int lengthOfLPS = findLengthOfLongestPalindromicSubsequence(s);

        System.out.println("Length of longest palindromic subsequence = " + lengthOfLPS);
    }

    private static int findLengthOfLongestPalindromicSubsequence(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int length = s.length();

        int[][] lps = new int[length][length];

        // size = 1, 2 ..... n
        for (int size = 1; size <= length; size++) {
            for (int i = 0, j = i + size - 1; j < length; i++, j++) {
                if (i == j) {
                    lps[i][j] = 1;
                }
                else {
                    lps[i][j] = (s.charAt(i) == s.charAt(j)) ? 2 + lps[i + 1][j - 1] : Math.max(lps[i + 1][j], lps[i][j - 1]);
                }
            }
        }

        for (int i = 0; i < lps.length; i++) {
            for (int j = 0; j < lps[0].length; j++) {
                System.out.print(lps[i][j] + " ");
            }
            System.out.println();
        }

        return lps[0][length - 1];
    }
}
