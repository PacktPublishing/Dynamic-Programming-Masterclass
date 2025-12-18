package com.company.stockmarket;

public class BuyAndSellStockCooldown {
    public static void main(String[] args) {
        int[] prices = new int[] {7, 2, 9, 0, 1, 15, 5};

        int maxProfit = maxProfitWithCooldown(prices);

        System.out.println("Max Profit a trader can make (with cooldown) = " + maxProfit);

        int maxProfitAfterBuyCooldown = maxProfitWithCooldownAfterBuy(prices);

        System.out.println("Max Profit a trader can make (with cooldown after buy) = " + maxProfitAfterBuyCooldown);
    }

    private static int maxProfitWithCooldown(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        int[] ignore = new int[prices.length];
        int[] hold = new int[prices.length];

        buy[buy.length - 1] = -prices[prices.length - 1];
        sell[sell.length - 1] = +prices[prices.length - 1];

        for (int i = prices.length - 2; i >= 0; i--) {
            buy[i] = Math.max(sell[i + 1], hold[i + 1]) - prices[i];
            sell[i] = ignore[i + 1] + prices[i];
            ignore[i] = Math.max(ignore[i + 1], buy[i + 1]);
            hold[i] = Math.max(hold[i + 1], sell[i + 1]);
        }

        return Math.max(buy[0], ignore[0]);
    }

    private static int maxProfitWithCooldownAfterBuy(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        int[] ignore = new int[prices.length];
        int[] hold = new int[prices.length];

        buy[buy.length - 1] = -prices[prices.length - 1];
        sell[sell.length - 1] = +prices[prices.length - 1];

        for (int i = prices.length - 2; i >= 0; i--) {
            buy[i] = hold[i + 1] - prices[i];
            sell[i] = Math.max(buy[i + 1], ignore[i + 1]) + prices[i];
            ignore[i] = Math.max(ignore[i + 1], buy[i + 1]);
            hold[i] = Math.max(hold[i + 1], sell[i + 1]);
        }

        return Math.max(buy[0], ignore[0]);
    }
}
