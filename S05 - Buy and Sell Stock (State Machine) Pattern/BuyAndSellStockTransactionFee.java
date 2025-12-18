package com.company.stockmarket;

public class BuyAndSellStockTransactionFee {
    public static void main(String[] args) {
        // int[] prices = new int[] {3, 8, 2, 7, 15, 9, 5};

        int[] prices = new int[] {-4, -3, -1, -9, 102};

        int fee = 3;

        int maxProfit = maxProfitWithTransactionFee(prices, fee);

        System.out.println("Max Profit a trader can make (with transaction fee) = " + maxProfit);

        int maxProfitWithBuyingFee = maxProfitWithBuyingFee(prices, fee);

        System.out.println("Max Profit a trader can make (with buying fee) = " + maxProfitWithBuyingFee);
    }

    private static int maxProfitWithTransactionFee(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        int[] hold = new int[prices.length];
        int[] ignore = new int[prices.length];

        buy[buy.length - 1] = -prices[prices.length - 1];
        sell[sell.length - 1] = prices[prices.length - 1] - fee;

        for (int i = prices.length - 2; i >= 0; i--) {
            buy[i] = Math.max(sell[i + 1], hold[i + 1]) - prices[i];
            sell[i] = Math.max(buy[i + 1], ignore[i + 1]) + prices[i] - fee;
            hold[i] = Math.max(hold[i + 1], sell[i + 1]);
            ignore[i] = Math.max(ignore[i + 1], buy[i + 1]);
        }

        return Math.max(buy[0], ignore[0]);
    }

    private static int maxProfitWithBuyingFee(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        int[] hold = new int[prices.length];
        int[] ignore = new int[prices.length];

        buy[buy.length - 1] = -prices[prices.length - 1] - fee;
        sell[sell.length - 1] = prices[prices.length - 1];

        for (int i = prices.length - 2; i >= 0; i--) {
            buy[i] = Math.max(sell[i + 1], hold[i + 1]) - prices[i] - fee;
            sell[i] = Math.max(buy[i + 1], ignore[i + 1]) + prices[i];
            hold[i] = Math.max(hold[i + 1], sell[i + 1]);
            ignore[i] = Math.max(ignore[i + 1], buy[i + 1]);
        }

        return Math.max(buy[0], ignore[0]);
    }
}
