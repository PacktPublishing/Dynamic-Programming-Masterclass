package com.company.lcspattern;

public class MinimumASCIIDeleteSum {
    public static void main(String[] args) {
        String s1 = "axqrt";
        String s2 = "xaqtr";

        int minimumASCIIDeleteSum = findMinimumASCIIDeleteSum(s1, s2);

        System.out.println("Min ASCII Sum of deleted characters to the strings equal = " + minimumASCIIDeleteSum);
    }

    // Assuming s1 and s2 are not null
    private static int findMinimumASCIIDeleteSum(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();

        // +1 for empty string append to both the strings
        int[][] mads = new int[length1 + 1][length2 + 1];

        mads[length1][length2] = 0;

        // bottomost row - s1 is empty => delete all the chars of s2
        for (int j = length2 - 1; j >= 0; j--) {
            mads[length1][j] = s2.charAt(j) + mads[length1][j + 1];
        }

        // right column = s2 is empty => delete all the chars of s1
        for (int i = length1 - 1; i >= 0; i--) {
            mads[i][length2] = s1.charAt(i) + mads[i + 1][length2];
        }

        for (int i = length1 - 1; i >= 0; i--) {
            for (int j = length2 - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    mads[i][j] = mads[i + 1][j + 1];
                }
                else {
                    mads[i][j] = Math.min(s1.charAt(i) + mads[i + 1][j], s2.charAt(j) + mads[i][j + 1]);
                }
            }
        }

        for (int i = 0; i < mads.length; i++) {
            for (int j = 0; j < mads[0].length; j++) {
                System.out.print(mads[i][j] + " ");
            }
            System.out.println();
        }

        return mads[0][0];
    }
}
