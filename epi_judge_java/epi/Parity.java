package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    // Brute Force
/*     short numOnes = 0;
    while (x > 0){
      numOnes += (short) x & 1;
      x = x >> 1;
    }
    return (short) (numOnes % 2); */

    // Optimal #1 - See _parity()

    // Optimal #2
    final int MASK_SIZE = 16;   // Masking four 16-bit subwords
    final int BIT_MASK = 0xFFFF;   // 16-bit mask containing all 1s

    short[] lookupTable = precomputeParity(16);
    short p1 = lookupTable[(int) (x >>> (3 * MASK_SIZE))];   // Highest 16 bits shifted to end
    short p2 = lookupTable[(int) ((x >>> (2 * MASK_SIZE)) & BIT_MASK)];   // 2nd highest 16 bits shifted to end & masked (to remove bits before it)
    short p3 = lookupTable[(int) ((x >>> MASK_SIZE) & BIT_MASK)];
    short p4 = lookupTable[(int) (x & BIT_MASK)];   // No shifting as it's already at the end

    return (short) (p1 ^ p2 ^ p3 ^ p4);
  }

  /**
   * Optimal parity function used to create lookup table.
   * @return 0 if number of 1 bits is even, 1 otherwise
   */
  public static short _parity(int x){
    short parity = 0;
    while (x != 0){
      x &= x - 1;    // Removes lowest 1 bit from x
      parity ^= 1;   // Keeps flipping between 0 & 1 every iteration
    }
    return parity;
  }

  /**
   * Creates a parity lookup table of 16-bit integers (from 0 to 2^16)
   * @return lookup table
   */
  public static short[] precomputeParity(int bound){
    short[] lookupTable = new short[1 << 16];  // Efficient way to compute 2^16
    for (int i = 0; i < lookupTable.length; i++){
      lookupTable[i] = _parity(i);
    }
    return lookupTable;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
