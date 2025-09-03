package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveDivide {
  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {
    // Brute Force using recursion: x/y = (x - y)/y + 1. Base case: x < y
    // Time Complexity: O(q) - q is quotient
    // Space Complexity: O(q) - call stack frames
    // Results in stack overflow due to large size of test cases
    if (x < y) {
      return 0;
    }
    return divide(x - y, y) + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
