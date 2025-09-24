package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    // Initial Attempt (textbook sol) - Alr. looked at the textbook lol
    // Using two (forward + backward) passes to compute two sets of profits
    // Time: O(n), Space: O(n)
    // Invariants: 
    // - `firstProfits[i]` is always max. profit for prices[0:i]
    // - `maxP` at iteration i is always max. total profit for prices[i:]
    List<Double> firstProfits = new ArrayList<>(Arrays.asList(0.0));
    Double lowest = prices.get(0);

    // Forward pass
    for (int i = 1; i < prices.size(); i++) {
      lowest = Math.min(lowest, prices.get(i));
      firstProfits.add(Math.max(firstProfits.get(i - 1), prices.get(i) - lowest));
    }

    // Backward pass
    Double highest = Double.MIN_VALUE;
    Double maxP = 0.0;
    for (int i = prices.size() - 1; i >= 0; i--) {
      highest = Math.max(highest, prices.get(i));
      maxP = Math.max(maxP, highest - prices.get(i) + firstProfits.get(i));
    }
    return maxP;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
