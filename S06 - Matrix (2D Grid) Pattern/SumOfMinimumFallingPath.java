package com.company.matrixpattern;

public class SumOfMinimumFallingPath {
    public static void main(String[] args) {
        int[][] grid = {{2,3,1,4},{1,4,2,1},{5,4,1,1},{2,1,0,3},{3,2,1,0}};

        int minSumFallingPath = sumOfMinimumFallingPath(grid);

        System.out.println("Sum of minimum falling path through the grid = " + minSumFallingPath);
    }

    /*
        grid:
        {2,3,1,4}
        {1,4,2,1}
        {5,4,1,1}
        {2,1,0,3}
        {3,2,1,0}

        {0,0,0,0}
        {0,0,0,0}
        {0,0,0,0}
        {0,0,0,0}
        {0,0,0,0}
     */
    private static int sumOfMinimumFallingPath(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;

        int[][] dp = new int[r][c];

        // base case - bottom most row
        for (int j = 0; j < c; j++) {
            dp[r - 1][j] = grid[r - 1][j];
        }

        // recurrence relation - dp[i][j] = Min(dp[i + 1][j], dp[i + 1][j + 1], dp[i + 1][j - 1]) + grid[i][j];
        for (int i = r - 2; i >= 0; i--) {
            for (int j = c - 1; j >= 0; j--) {
                dp[i][j] = dp[i + 1][j];
                if (j + 1 < c) {       // NOT out of bounds from right
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j + 1]);
                }
                if (j - 1 >= 0) {       // NOT out of bounds from left
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
                dp[i][j] += grid[i][j];
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        for (int j = 0; j < c; j++) {
            ans = Math.min(ans, dp[0][j]);
        }

        return ans;
    }
}
