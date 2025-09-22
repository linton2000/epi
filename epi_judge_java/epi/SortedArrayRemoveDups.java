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
    // Initial Attempt
/*     for (int i = 0; i < (A.size() - 1); i++) {
      int j = i;
      while (A.get(j) == A.get(j + 1))
        j++;

      // Shuffle left & overwrite duplicates
      int k = 1;
      while (k <= (j - i)) {
        A.set(i + k, A.get(j + k));
        k++;
      }
    }

    return A.size(); */
  }
  @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
  public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                      List<Integer> A)
      throws Exception {
    int end = executor.run(() -> deleteDuplicates(A));
    return A.subList(0, end);
  }

  public static void main(String[] args) {
    List<Integer> A = new ArrayList<>(Arrays.asList(2, 3, 5, 5, 7, 11, 11, 11, 13));
    deleteDuplicates(A);
    System.out.println(A);

/*     System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal()); */
  }
}
