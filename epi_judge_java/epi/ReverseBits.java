package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;


public class ReverseBits {
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    // Brute Force
    // Time Complexity: O(n) where n = no. of bits in x
/*     long y = 0;  // Reversed x
    long i = 63;  // Bit index starting from MSB

    while (x > 0){
      if ((x & 1) == 1){
        y += 1L << i;
      }
      x >>>= 1;
      i--;
    }

    return y; */

    // Better Sol 1
    // Same time complexity but 1/2 constant
    long lo = 0;
    long hi = 63;

    while (lo < hi){
      if(((x >>> hi) & 1L) != ((x >>> lo) & 1L)){
        long bitMask = (1L << hi) | (1L << lo);
        x ^= bitMask;
      }
      lo++;
      hi--;
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
