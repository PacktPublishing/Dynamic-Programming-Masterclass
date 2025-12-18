package com.company.lispattern;

import java.util.Arrays;

public class LongestLengthOfChainOfPairs {

    public static void main(String[] args) {
        int[][] pairs = new int[][] {{15, 22}, {1, 7}, {32, 38}, {49, 55}, {44, 50}, {20, 58}, {0,30}};

        int lengthOfLC = lengthOfLongestChainOfPairs(pairs);

        System.out.println("Length of the longest chain of pairs = " + lengthOfLC);
    }

    private static int lengthOfLongestChainOfPairs(int[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return 0;
        }

        // convenient for look ahead
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));

        int lengthOfLC = 0;

        int[] llc = new int[pairs.length];

        for (int i = pairs.length - 1; i >= 0; i--) {
            llc[i] = 1;

            // look ahead, find all the potential next candidates, choose the best candidate (highest LLC value)
            for (int j = i + 1; j < pairs.length; j++) {
                if (pairs[j][0] > pairs[i][1]) {
                    llc[i] = Math.max(llc[i], 1 + llc[j]);
                }
            }

            lengthOfLC = Math.max(lengthOfLC, llc[i]);
        }

        return lengthOfLC;
    }
}
