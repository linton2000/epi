package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplyPermutation {
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    // Initial Attempt - Brute Force
    List<Integer> res = new ArrayList<>(Collections.nCopies(A.size(), null));
    
    for (int i = 0; i < A.size(); i++) {
      res.set(perm.get(i), A.get(i));
    }

    A.clear();
    A.addAll(res);
    return;
  }
  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
