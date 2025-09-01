package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    // Brute Force
    // Time Complexity: O(m.k) where m - target distance & k - word length (64 bits)
/*     long d = 1;
    int targetWeight = getWeight(x);
    while (true) {
      if (getWeight(x - d) == targetWeight)
        return x - d;
      if (getWeight(x + d) == targetWeight)
        return x + d;
      d++;
    } */

    // Optimal (textbook sol) - Flip specific bits (k1 & k2)
    // Time Complexity: O(n) where n - integer size
/*     long lsb = x & 1L;
    for (int i = 1; i < 64; i++){
      if (((x >> i) & 1L) != lsb) {
        long bitMask = (1 << i) | (1 << (i - 1));
        return x ^ bitMask;
      }
    }
    return x; */

    // Optimal (variant) - Use bit hacks
    // Time Complexity: O(1)
    long lsb = x & 1L;
    if (lsb == 0){  // Least sig. 1 bit is k1
      long bitMask = x & ~(x-1) | (x & ~(x-1)) >> 1;
      return x ^ bitMask;
    } 
    else {  // Flip all bits & do the same lol
      x ^= -1L; // -1 = (1111...111)2
      long bitMask = x & ~(x-1) | (x & ~(x-1)) >> 1;
      x ^= bitMask;
      return x ^ -1L;  // Flip all bits back & return
    }
  }

  public static int getWeight(long x){
    int weight = 0;
    while (x > 0){
      x &= x - 1;  // Remove least sig. 1 bit (called Brian Kernighan’s trick)
      weight++;
    }
    return weight;
  }

  public static void main(String[] args) {
    //System.out.println(closestIntSameBitCount(184));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
