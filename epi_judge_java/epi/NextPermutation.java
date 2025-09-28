package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    // Initial Attempt - Swapping & sorting to get next perm
    // Time: O(nlogn), Space: O(1)
    for (int i = perm.size() - 1; i > 0; i--) {
      if (perm.get(i-1) < perm.get(i)) {
        int di = i - 1;   // Index of 1st non-conforming (decreasing) digit from the right
        int dk = i;       // Index of smallest element bigger & to the right of perm[di]
        for (int j = i; j < perm.size(); j++) {
          if (perm.get(j) > perm.get(di) && perm.get(j) < perm.get(dk))
            dk = j;
        }
        Collections.swap(perm, di, dk);
        Collections.sort(perm.subList(di + 1, perm.size()));  // Sort all elements after perm[di]
        return perm;
      }
    }
    return new ArrayList<>();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
