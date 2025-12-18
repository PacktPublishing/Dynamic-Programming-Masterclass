package com.company.lispattern;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] array = new int[] {3, 8, 2, 7, 12, 1, 13, 5, 9, 10};

	    int lengthOfLIS = findLengthOfLIS(array);

	    System.out.println("Length of the longest increasing subsequence = " + lengthOfLIS);
    }

    private static int findLengthOfLIS(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }

        int[] lisStartingAtIndex = new int[array.length];

        int lengthOfLIS = 0;

        for (int i = array.length - 1; i >= 0; i--) {
            lisStartingAtIndex[i] = 1;

            // look ahead >= array[i], out of these we find the maximum LIS value + 1
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] >= array[i]) {
                    lisStartingAtIndex[i] = Math.max(lisStartingAtIndex[i], lisStartingAtIndex[j] + 1);
                }
            }

            lengthOfLIS = Math.max(lengthOfLIS, lisStartingAtIndex[i]);
        }

        for (int i = 0; i < lisStartingAtIndex.length; i++) {
            System.out.print(lisStartingAtIndex[i] + " ");
        }
        System.out.println();

        return lengthOfLIS;
    }
}
