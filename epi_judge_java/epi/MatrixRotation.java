package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
public class MatrixRotation {

  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    // Initial Attempt - Brute force
    List<List<Integer>> res = new ArrayList<>();

    for (int i = 0; i < squareMatrix.size(); i++) {
      res.add(new ArrayList<>());
      for (int j = squareMatrix.size() - 1; j >= 0; j--) {
        res.get(i).add(squareMatrix.get(j).get(i));
      }
    }

    for (int i = 0; i < squareMatrix.size(); i++) {
        squareMatrix.set(i, res.get(i));
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
/*     List<List<Integer>> in1 = List.of(List.of(1, 2, 3, 4), 
                                      List.of(5, 6, 7, 8), 
                                      List.of(9, 10, 11, 12), 
                                      List.of(13, 14, 15, 16));
    rotateMatrix(in1);
    System.out.println(in1); */
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixRotation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
