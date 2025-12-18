package com.company.matrixpattern;

public class UniqueWaysToReachDestination {

    public static void main(String[] args) {
        int r = 4;
        int c = 5;

        int totalWays = countUniqueWaysToReachDestination(r, c);

        System.out.println("Total number Of unique ways to reach the destination = " + totalWays);
    }

    // assuming r and c are at least 1 both
    private static int countUniqueWaysToReachDestination(int r, int c) {
        int[][] dp = new int[r][c];

        // dp[i][j] = dp[i + ][j] + dp[i][j + 1]
        // bottom to top, right to left
        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 0; j--) {
                // base case (i == r - 1), (j == c - 1)
                if (i == r - 1 || j == c - 1) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }

        return dp[0][0];
    }
}

