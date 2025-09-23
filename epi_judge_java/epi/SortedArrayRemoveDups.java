package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortedArrayRemoveDups {
  // Returns the number of valid entries after deletion.
  public static int deleteDuplicates(List<Integer> A) {
    // Initial Attempt - Brute force
    // Time: O(n), Space: O(n)
/*     List<Integer> res = new ArrayList<>();

    for (int i = 0; i < (A.size() - 1); i++) {
      // Find duplicates
      int j = i;
      while (j < (A.size() - 1) && A.get(j) != null && A.get(j+1) != null 
      && A.get(j).equals(A.get(j + 1).intValue())) {
        j++;
      }

      // Set duplicates to null
      while (j > i) {
        A.set(j, null);
        j--;
      }
    }

    for (Integer e : A) {
      if (e != null)
        res.add(e);
    }

    A.clear();
    A.addAll(res);
    return A.size(); */

    // Textbook Sol
    // Use a write ptr to record 1st dup & overwrite with 1st element after dups (unique copy)
    // Time: O(n), Space: (1)
    // Invariant: Sublist to left of write ptr (i.e. A[:wp]) has no dups
    if (A.isEmpty())
      return 0;
      
    int wp = 1;

    for (int i = 1; i < A.size(); i++) {
      if (!A.get(wp - 1).equals(A.get(i))) {  // Has i reached a non-dup
        A.set(wp++, A.get(i));
      }
    }

    return wp;
  }

  @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
  public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                      List<Integer> A)
      throws Exception {
    int end = executor.run(() -> deleteDuplicates(A));
    return A.subList(0, end);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
