package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    // Initial Attempt - Swapping & sorting to get next perm
    // Time: O(nlogn), Space: O(1)
    /*
     * for (int i = perm.size() - 1; i > 0; i--) {
     * if (perm.get(i-1) < perm.get(i)) {
     * int di = i - 1; // Index of 1st non-conforming (decreasing) digit from the
     * right
     * int dk = i; // Index of smallest element bigger & to the right of perm[di]
     * for (int j = i; j < perm.size(); j++) {
     * if (perm.get(j) > perm.get(di) && perm.get(j) < perm.get(dk))
     * dk = j;
     * }
     * Collections.swap(perm, di, dk);
     * Collections.sort(perm.subList(di + 1, perm.size())); // Sort all elements
     * after perm[di]
     * return perm;
     * }
     * }
     * return new ArrayList<>();
     */

    // 2nd Attempt - Swap the same as above but reverse instead of sort (as suffix
    // is alr. in desc. order)
    // Time: O(n), Space: O(1)
    for (int i = perm.size() - 1; i > 0; i--) {
      if (perm.get(i - 1) < perm.get(i)) {
        // Swap to get correct element in prefix
        int di = i - 1; // Index of 1st non-conforming (decreasing) digit from the right
        int dk = 0; // Index of successor
        int successor = Integer.MAX_VALUE; // Successor (smallest element bigger & to the right of perm[di])
        for (int j = perm.size() - 1; j > di; j--) {
          if (perm.get(j) > perm.get(di) && perm.get(j) < successor) {
            dk = j;
            successor = perm.get(dk);
          }
        }
        Collections.swap(perm, di, dk);

        // Reverse suffix (get it in asc. order)
        int lo = di + 1;
        int hi = perm.size() - 1;
        while (lo < hi) {
          if (perm.get(lo) > perm.get(hi))
            Collections.swap(perm, lo, hi);
          lo++;
          hi--;
        }
        return perm;
      }
    }
    return new ArrayList<>();
  }

  public static void main(String[] args) {
    // System.out.println(nextPermutation(Arrays.asList(8, 1, 4, 2, 6, 7, 7, 3)));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
