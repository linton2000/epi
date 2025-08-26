package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    // Brute Force
    long y = 0;  // Reversed x
    long i = 63;  // Bit index starting from MSB

    while (x > 0){
      if ((x & 1) == 1){
        y += 1L << i;
      }
      x >>>= 1;
      i--;
    }

    return y;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
