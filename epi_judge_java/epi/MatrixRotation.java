package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
public class MatrixRotation {

  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    // Initial Attempt - Brute force
/*     List<List<Integer>> res = new ArrayList<>();

    for (int i = 0; i < squareMatrix.size(); i++) {
      res.add(new ArrayList<>());
      for (int j = squareMatrix.size() - 1; j >= 0; j--) {
        res.get(i).add(squareMatrix.get(j).get(i));
      }
    }

    for (int i = 0; i < squareMatrix.size(); i++) {
        squareMatrix.set(i, res.get(i));
    } */

    // Textbook Sol - Same O(N^2) time but O(1) space
    int N = squareMatrix.size();
    for (int i = 0; i < N/2; i++) {                            // No. of layers
      for (int j = i; j < (N - i - 1); j++) {                  // 1 iteration = 4 elts displaced
        int tmp1 = squareMatrix.get(i).get(j);
        int tmp2 = squareMatrix.get(j).get(N - 1 - i);
        int tmp3 = squareMatrix.get(N - 1 - i).get(N - 1 - j);
        int tmp4 = squareMatrix.get(N - 1 - j).get(i);

        squareMatrix.get(j).set(N - 1 - i, tmp1);
        squareMatrix.get(N - 1 - i).set(N - 1 - j, tmp2);
        squareMatrix.get(N - 1 - j).set(i, tmp3);
        squareMatrix.get(i).set(j, tmp4);
      }
    }
    return;
  }
  
  @EpiTest(testDataFile = "matrix_rotation.tsv")
  public static List<List<Integer>>
  rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
    rotateMatrix(squareMatrix);
    return squareMatrix;
  }

  public static void main(String[] args) {
/*     List<List<Integer>> in1 = new ArrayList<>(Arrays.asList(
                                new ArrayList<>(Arrays.asList(1, 2, 3, 4)), 
                                new ArrayList<>(Arrays.asList(5, 6, 7, 8)), 
                                new ArrayList<>(Arrays.asList(9, 10, 11, 12)), 
                                new ArrayList<>(Arrays.asList(13, 14, 15, 16))));
    rotateMatrix(in1);
    System.out.println(in1); */
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixRotation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
