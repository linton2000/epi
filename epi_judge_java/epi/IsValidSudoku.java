package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsValidSudoku {
  @EpiTest(testDataFile = "is_valid_sudoku.tsv")

  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
    // Initial Attempt #1 - N^3 brute force
    for (List<Integer> listI : partialAssignment) {   // Dups in rows
      for (int i = 0; i < listI.size(); i++) {
        int j = i + 1;
        while (j < listI.size()) {
          if (listI.get(i) != 0 && listI.get(i) == listI.get(j)) {
            return false;
          }
          j++;
        }
      }
    }

    for (int k = 0; k < partialAssignment.size(); k++) {   // Dups in cols
      for (int i = 0; i < partialAssignment.size(); i++) {
        int j = i + 1;
        while (j < partialAssignment.size()) {
          if (partialAssignment.get(i).get(k) != 0 && 
              partialAssignment.get(i).get(k) == partialAssignment.get(j).get(k)) {
            return false;
          }
          j++;
        }
      }
    }

    Map<Integer, Integer> counts;
    int[][] intervals = {{0, 3, 0, 3}, {0, 3, 3, 6}, {0, 3, 6, 9}, 
                         {3, 6, 0, 3}, {3, 6, 3, 6}, {3, 6, 6, 9}, 
                         {6, 9, 0, 3}, {6, 9, 3, 6}, {6, 9, 6, 9}};
    for (int[] range : intervals) {
      counts = new HashMap<>();
      for (int i = range[0]; i < range[1]; i++) {
        for (int j = range[2]; j < range[3]; j++) {
          int elt = partialAssignment.get(i).get(j);
          if (elt != 0 && counts.containsKey(elt)) {
            return false; 
          } else {
            counts.put(elt, 1);
          }
        }
      }
    }

    return true;
  }

  public static void main(String[] args) {
/*     List<List<Integer>> in0 = Arrays.asList(
        Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0),
        Arrays.asList(7, 0, 0, 5, 9, 8, 0, 2, 1),
        Arrays.asList(0, 1, 0, 4, 0, 0, 9, 0, 3),
        Arrays.asList(3, 0, 6, 7, 0, 0, 4, 0, 8),
        Arrays.asList(8, 2, 0, 1, 5, 0, 0, 0, 0),
        Arrays.asList(0, 0, 0, 0, 0, 3, 0, 0, 0),
        Arrays.asList(0, 8, 4, 3, 0, 7, 0, 5, 0),
        Arrays.asList(6, 9, 0, 0, 0, 0, 2, 0, 0),
        Arrays.asList(1, 3, 0, 0, 0, 2, 8, 0, 7)
    );    */
/*     List<List<Integer>> in1 = Arrays.asList(
        Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0),
        Arrays.asList(0, 0, 0, 0, 0, 6, 0, 0, 0),
        Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0),
        Arrays.asList(0, 0, 0, 0, 8, 0, 0, 0, 0),
        Arrays.asList(9, 0, 0, 0, 7, 5, 0, 0, 0),
        Arrays.asList(0, 0, 0, 0, 5, 0, 0, 8, 0),
        Arrays.asList(0, 0, 9, 0, 0, 0, 0, 0, 0),
        Arrays.asList(2, 0, 6, 0, 0, 0, 0, 0, 0),
        Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0)
    );
    System.out.println(isValidSudoku(in1)); */
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsValidSudoku.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
