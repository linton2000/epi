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

    // Optimal - In-place
    // Space: O(1), Time: O(n)
/*     int lp = 0;

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
      if (A.get(i) == Color.BLUE && i < gp) {
        Collections.swap(A, i, gp);
        gp--;
      }
      i++;
    }
    return; */

    // Optimal - Textbook sol (same complexity, less code)
    // Split into 4 subarrays: 
    // A[:lo] (less than pivot) + A[lo:mid] (equal to pivot) + A[mid:hi] (unclassified) + A[hi:] (greater than pivot)
    int lo = 0, mid = 0, hi = A.size();
    Color pvt = A.get(pivotIndex);

    while (mid < hi) {  // Unclassified subarr not empty
      if (A.get(mid).ordinal() < pvt.ordinal()){
        Collections.swap(A, lo++, mid++); // Swap unclass. with 1st element equal to pivot
      }
      else if (A.get(mid).ordinal() == pvt.ordinal()) {
        mid++;  // Keep it where it is and move unclassified subarr ptr
      }
      else {  // Greater than pivot
        Collections.swap(A, mid, --hi); // Swap with last unclassified element (hi - 1)
      }
    }
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

  public static List<Color> createColors() {
        int[] arr = {
            0, 2, 2, 2, 2, 0, 1, 2, 0, 2, 0, 1, 1, 1, 2, 2, 2, 2, 2,
            1, 1, 2, 0, 2, 2, 1, 0, 0, 1, 1, 2, 2, 1, 2, 0, 1, 0, 1, 2,
            1, 0, 2, 2, 2, 0, 1, 0, 0, 2, 2, 0, 0, 0, 2, 1, 2, 1, 0, 1,
            2, 2, 2, 2, 1, 0, 2, 2, 2, 2, 0, 1, 2, 1, 2, 2, 1, 2, 1, 0,
            0, 1, 1, 1, 2, 2, 1, 0, 0, 0, 2, 1, 0, 2
        };

        List<Color> colors = new ArrayList<>();
        Color[] palette = Color.values();

        for (int val : arr) {
            colors.add(palette[val]);
        }

        return colors;
    }

  public static void main(String[] args) {
/*     List<Color> A = createColors();
    dutchFlagPartition(0, A);
    System.out.println(A); */
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
