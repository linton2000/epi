package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    // Initial Attempt - Brute force optimisation
    // Time: O(n^2), Space: O(1)
    // Invariant: p is max profit for all prices 0...i, at end of iteration
    Double p = 0.0;

    for (int i = 0; i < prices.size(); i++) {
      for (int j = (i + 1); j < prices.size(); j++) {
        if ((prices.get(j) - prices.get(i)) > p)
          p = prices.get(j) - prices.get(i);
      }
    }
    return p;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
