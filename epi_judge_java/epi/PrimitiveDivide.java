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
/*     if (x < y) {
      return 0;
    }
    return divide(x - y, y) + 1; */

    // Optimal 1 (initial approach)
    // Find largest k: (2^k)y <= x. Set x = 2*2^k and q += 2^k. Repeat divide.
    // Time Complexity: O(n^2), where n - num of quotient bits
/*     if (x < y) {
      return 0;
    }
    
    int k = 0;
    while (y < (x >>> (k+1))) {
      k += 1;
    }
    return divide(x - (1 << k)*y, y) + (1 << k); */

    // Optimal 2 (textbook sol) - Simplify k finding
    // Note: Assumes non-negative inputs
    int q = 0;
    int k = 0;
    while (y <= (x >>> (k+1)))
      k += 1;
    
    while (x >= y){
      while (y > (x >>> k))
        k -= 1;
      x -= (1 << k) * y;
      q += 1 << k;
    }
    return q;
  }

  public static void main(String[] args) {
    //System.out.println(divide(123, 3));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
