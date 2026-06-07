package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")

  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    // Initial Attempt - Also optimal
    // Time & Space complexity - O(n^2)
/*     if (numRows < 1) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    res.add(new ArrayList<>(List.of(1)));

    for (int i = 1; i < numRows; i++) {
      res.add(new ArrayList<>(List.of(1)));   // Leading 1

      for (int j = 0; j < res.get(i-1).size(); j++) {
        if (j + 1 >= res.get(i-1).size()) {
          res.get(i).add(1);     // Trailing 1
        } else {
          int elt = res.get(i-1).get(j) + res.get(i-1).get(j+1);
          res.get(i).add(elt);     // Middle numbers
        }
      }
    } */

    // Textbook sol - Cleaner
    List<List<Integer>> res = new ArrayList<>();

    for (int i = 0; i < numRows; i++) {
      List<Integer> currRow = new ArrayList<>();

      for (int j = 0; j <= i; j++) {
        currRow.add((0 < j && j < i) ? res.get(i-1).get(j-1) + res.get(i-1).get(j)
                                     : 1);
      }
      res.add(currRow);
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
