package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    // Textbook Sol - Add (2^k)y for every 1 bit (k is position of bit)
    long sum = 0;

    while (x != 0){
      if ((x & 1L) == 1L){  // LSB is a 1 bit
        sum = add(sum, y);
      }
      x >>>= 1;  // Right shift to next bit
      y <<= 1;  // Multiply by 2^k
    }
    return sum;
  }

  public static long add(long x, long y){
    return y == 0 ? x : add(x ^ y, (x & y) << 1);
  }

  public static void main(String[] args) {
    //System.out.println(multiply(3, 4));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
