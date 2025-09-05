package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")
  public static double power(double x, int y) {
    // Optimal 1 - Recusrive products of squares
    // Time Complexity - O(y); Space - O(log y) [recursion depth]
    if (y < 0) {  // Hanlde neg. args
      x = 1 / x;
      y = y < 0 ? -y : y;
    }

    if (y == 0)
      return 1;

    if (y == 1)
      return x;

    if ((y & 1) == 0)
      return power(x, y/2) * power(x, y/2);
    
    return power(x, y/2) * power(x, y/2) * x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
