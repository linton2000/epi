package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    // Initial Attempt - Brute Force
    // Space: O(n), Time: O(n^2)
/*     List<Color> res = new ArrayList<>();
    int lp = 0; // Less than Pivot ptr (end of 1st subarray)
    int p = 0;  // Equal to Pivot ptr (end of 2nd subaray)

    for (int i = 0; i < A.size(); i++) {
      if (A.get(i) == Color.RED) {
        res.add(lp, A.get(i));
        lp++;
        p++;
      } 
      else if (A.get(i) == Color.WHITE) {
        res.add(p, A.get(i));
        p++;
      }
      else {  // BLUE color
        res.add(A.get(i));
      }
    }
    A.clear();
    A.addAll(res);  // Deep copy into A */

    // Brute Force - In-place (more time, less space)
    // Space: O(1), Time: O(n^2)
    int lp = 0;

    // First pass - Move all elements less than pivot to 1st subarray
    for (int i = 0; i < A.size(); i++) {
      if (A.get(i) == Color.RED) {
        Collections.swap(A, i, lp);
        lp++;
      }
    }

    // Second pass - Move all elements greater than pivot to 2nd subarray
    int i = lp;
    int gp = A.size() - 1;  // 1 element before start of 3rd subarray, i.e. idx to place BLUEs
    while (i < gp) {
      while (A.get(gp) == Color.BLUE) {
        gp--;
      }
      if (A.get(i) == Color.BLUE) {
        Collections.swap(A, i, gp);
        gp--;
      }
      i++;
    }
    return;
  }
  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
