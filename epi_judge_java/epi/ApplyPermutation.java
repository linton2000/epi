package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApplyPermutation {
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    // Initial Attempt - Brute Force
    // Time: O(n), Space: O(n)
    // Invariant: At end of iteration i, all elements in A[0...i] have been permuted and added to `res`
/*     List<Integer> res = new ArrayList<>(Collections.nCopies(A.size(), null));
    
    for (int i = 0; i < A.size(); i++) {
      res.set(perm.get(i), A.get(i));
    }

    A.clear();
    A.addAll(res); */

    // 2nd Attempt - Perform cycle chasing for an in-place solution
    // Time: O(n), Space: O(1)
    // Invariant: At end of iteration i, all elements in the permutation cycle starting at A[i] have been
    // correctly permuted in A.
/*     for (int i = 0; i < A.size(); i++) {
      int j = i;
      if (perm.get(j) != null) {
        int start = A.get(j);   // Start of cycle
        int tmp = start;

        do {  // Cycle chasing
          int dis = A.get(perm.get(j));
          int k = perm.get(j);
          A.set(k, tmp);   // Overwrite displaced element
          perm.set(j, null);  // Mark as performed
          j = k;  // Set next Permutation index
          tmp = dis;
        } while (start != tmp);
      }
    } */

    // Textbook Sol
    for (int i = 0; i < A.size(); i++) {
      while (perm.get(i) != i) {
        Collections.swap(A, i, perm.get(i));
        Collections.swap(perm, i, perm.get(i));
      }
    }

    return;
  }
  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
/*     List<Integer> A = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
    List<Integer> perm = new ArrayList<>(Arrays.asList(2, 0, 1, 3));
    applyPermutation(perm, A);
    System.out.println(A); */
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
