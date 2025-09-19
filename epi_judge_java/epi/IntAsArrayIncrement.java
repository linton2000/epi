package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
    // Initial Attempt (also most optimal, yay!)
    // Time: O(n) & Space: O(1), n - size of array
    int i = A.size() - 1;

    while (i >= 0) {
      if ((A.get(i) + 1) >= 10) {
        A.set(i, 0);
        i--;
      }
      else {
        A.set(i, A.get(i) + 1);
        break;
      }
    }
    if (i < 0) {
      A.add(0, 1);
    }

    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
