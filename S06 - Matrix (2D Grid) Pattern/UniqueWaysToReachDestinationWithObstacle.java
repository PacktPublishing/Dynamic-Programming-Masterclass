package com.company.matrixpattern;

public class UniqueWaysToReachDestinationWithObstacle {

    public static void main(String[] args) {
        int[][] grid = {{0,0,0,0,0},{0,0,1,0,0},{1,0,0,0,0},{0,0,0,0,0}};

        int totalWays = countUniqueWaysToReachDestinationWithObstacle(grid);

        System.out.println("Total number of unique ways to reach the destination (with obstacle) = " + totalWays);
    }

    /*
        Grid
        {0,0,0,0,0}
        {0,0,1,0,1}
        {1,0,0,0,0}
        {0,0,1,0,0}

        dp
        {0,0,0,0,0}
        {0,0,1,0,0}
        {1,0,0,0,1}
        {0,0,0,1,1}
     */

    // assumption - no obstacle at the destination, no obstacle at the source
    private static int countUniqueWaysToReachDestinationWithObstacle(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        // base case
        dp[m - 1][n - 1] = 1;

        // base case - bottom most row
        int j = n - 2;
        int ansBottomRow = 1;
        while (j >= 0) {
            // obstacle
            if (grid[m - 1][j] == 1) {
                ansBottomRow = 0;
            }
            dp[m - 1][j] = ansBottomRow;
            j--;
        }

        // base case - right most column
        int i = m - 2;
        int ansRightColumn = 1;
        while (i >= 0) {
            if (grid[i][n - 1] == 1) {
                ansRightColumn = 0;
            }
            dp[i][n - 1] = ansRightColumn;
            i--;
        }

        // recurrence relation
        for (i = m - 2; i >= 0; i--) {
            for (j = n - 2; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    dp[i][j] = 0;
                }
                else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }

        return dp[0][0];
    }
}
