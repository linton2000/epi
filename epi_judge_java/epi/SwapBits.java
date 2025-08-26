package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {

    // Brute Force (same time complexity but less succinct/efficient than textbook sol)
/*     long xi = x & (long) 1 << i;  // Masking with 2^i to get ith bit
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
    } */

    // Textbook sol (if bits differ, toggle them with XOR using bitmask)
    if ((x >>> i & 1) != (x >>> j & 1)){     // Isolate 0/1 bits at i & j to compare
      long bitMask = (1L << i) | (1L << j);  // Create long var with i & j bits at appropriate positions
      x ^= bitMask;                          // Toggle x's bits at i & j by XORing with bit mask (0 becomes 1 & vice versa)
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
