package com.company.stockmarket;

public class BuyAndSellStock {
    public static void main(String[] args) {
        int[] array = new int[] {8, 3, 9, 2, 1, 15, 7};

        int maxProfit = maxProfit(array);

        System.out.println("Max Profit a trader can make = " + maxProfit);
    }

    private static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        int[] hold = new int[prices.length];
        int[] ignore = new int[prices.length];

        buy[prices.length - 1] = -prices[prices.length - 1];
        sell[prices.length - 1] = prices[prices.length - 1];

        for (int i = prices.length - 2; i >= 0; i--) {
            buy[i] = Math.max(sell[i + 1], hold[i + 1]) - prices[i];
            sell[i] = Math.max(buy[i + 1], ignore[i + 1]) + prices[i];
            hold[i] = Math.max(hold[i + 1], sell[i + 1]);
            ignore[i] = Math.max(ignore[i + 1], buy[i + 1]);
        }

        return Math.max(buy[0], ignore[0]);
    }
}
