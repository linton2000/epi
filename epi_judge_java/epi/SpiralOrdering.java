package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrdering {
  @EpiTest(testDataFile = "spiral_ordering.tsv")

  public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    // Initial Attempt 
    // Time & Space Complexity = O(N^2), for a NxN matrix
    List<Integer> res = new ArrayList<>();

    for (int k = 0; k <= (squareMatrix.size() / 2); k++) {
      int start = k;
      int end = squareMatrix.size() - (k + 1);

      int topRow = start;
      for (int i = start; i <= end; i++) {
        res.add(squareMatrix.get(topRow).get(i));
      }

      int rightCol = end;
      for (int i = start + 1; i <= end; i++) {
        res.add(squareMatrix.get(i).get(rightCol));
      }

      int bottomRow = end;
      for (int i = end - 1; i >= start; i--) {
        res.add(squareMatrix.get(bottomRow).get(i));
      }

      int leftCol = start;
      for (int i = end - 1; i > start; i--) {
        res.add(squareMatrix.get(i).get(leftCol));
      }
    }

    return res;
  }

  public static void main(String[] args) {
    /*
     * List<List<Integer>> in1 = List.of(List.of(1, 2), List.of(3, 4));
     * List<List<Integer>> in2 = List.of(List.of(1, 2, 3), List.of(4, 5, 6),
     * List.of(7, 8, 9));
     * System.out.println(matrixInSpiralOrder(in2));
     */

    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());

  }
}
