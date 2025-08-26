package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    long xi = x & (long) 1 << i;  // Masking with 2^i to get ith bit
    long xj = x & (long) 1 << j;

    if (xi == 0 && xj != 0){
      long val_i = (long) 1 << i;
      return x - xj + val_i;
    } 
    else if (xi != 0 && xj == 0){
      long val_j = (long) 1 << j;
      return x + val_j - xi;
    }
    else {
      return x;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
