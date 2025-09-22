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
    List<Integer> res = new ArrayList<>();

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
    return A.size();
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
