package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class AdvanceByOffsets {

  @EpiTest(testDataFile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    // Initial Attempt
/*     if (maxAdvanceSteps.size() == 1)
      return true;

    boolean res = false;
    for (int i = maxAdvanceSteps.get(0); i > 0; i--) {
      res |= canReachEnd(maxAdvanceSteps.subList(i, maxAdvanceSteps.size()));
      if (res) return res;
    }
    return res; */

    // Textbook Sol - Based on iteratively expanding frontier
    // Time: O(n), n - size of input list
    // Space: O(1)
    int frontier = 0;

    for (int i = 0; i < maxAdvanceSteps.size(); i++) {
      if (i > frontier) // i is unreachable
        return false;
      frontier = Math.max(frontier, i + maxAdvanceSteps.get(i));
    }
    return frontier >= (maxAdvanceSteps.size() - 1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
