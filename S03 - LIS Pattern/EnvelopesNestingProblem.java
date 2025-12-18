package com.company.lispattern;

import java.util.Arrays;

public class EnvelopesNestingProblem {
    public static void main(String[] args) {
        int[][] envelopes = new int[][] {{3, 4}, {5, 6}, {9, 11}, {3, 8}, {9, 10}, {11, 11}};

        int maxNumberOfNestedEnvelopes = maxNumberOfNestedEnvelopes(envelopes);

        System.out.println("Maximum Number of Nested Envelopes = " + maxNumberOfNestedEnvelopes);
    }

    private static int maxNumberOfNestedEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));

        int maxLengthOfNE = 0;

        int[] mneStartingAtIndex = new int[envelopes.length];

        for (int i = envelopes.length - 1; i >= 0; i--) {
            mneStartingAtIndex[i] = 1;

            for (int j = i + 1; j < envelopes.length; j++) {
                // find envelopes that consume ith envelope
                if (envelopes[j][0] > envelopes[i][0] && envelopes[j][1] > envelopes[i][1]) {
                    mneStartingAtIndex[i] = Math.max(mneStartingAtIndex[i], 1 + mneStartingAtIndex[j]);
                }
            }

            maxLengthOfNE = Math.max(maxLengthOfNE, mneStartingAtIndex[i]);
        }

        return maxLengthOfNE;
    }
}
