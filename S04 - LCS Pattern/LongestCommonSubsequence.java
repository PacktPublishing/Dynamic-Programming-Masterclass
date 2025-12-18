package com.company.lcspattern;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String s1 = "acdfrtx";
        String s2 = "aftdrx";

        int lengthOfLCS = findLengthOfLongestCommonSubsequence(s1, s2);

        System.out.println("Length of longest common subsequence = " + lengthOfLCS);
    }

    private static int findLengthOfLongestCommonSubsequence(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return 0;
        }

        int m = s1.length();
        int n = s2.length();

        int[][] lcs = new int[m + 1][n + 1];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    lcs[i][j] = 1 + lcs[i + 1][j + 1];
                }
                else {
                    lcs[i][j] = Math.max(lcs[i][j + 1], lcs[i + 1][j]);
                }
            }
        }

        for (int i = 0; i < lcs.length; i++) {
            for (int j = 0; j < lcs[0].length; j++) {
                System.out.print(lcs[i][j] + " ");
            }
            System.out.println();
        }

        return lcs[0][0];
    }
}
