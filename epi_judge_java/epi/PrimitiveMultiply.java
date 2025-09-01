package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    // Brute Force - Shift & Add + Bitwise Add
    long res = 0;

    for (long i = 0; i < 64; i++){
      long curr = 0;
      long bit = (y >>> i) & 1L;  // Selected bit for multiplation
      
      while (bit != 0){
        curr |= x & bit;
        bit <<= 1;
      }
      curr <<= i;
      res = add(res, curr);
    }
    return res;
  }

  public static long add(long x, long y){
    long res = 0;
    boolean carry = false;
    
    for (long i = 0; i < 64; i++){
      long xBit = (x >>> i) & 1L;
      long yBit = (y >>> i) & 1L;
      if (carry){
        if (xBit == 0L && yBit == 0L){
          carry = false;
          res |= (1 << i);
        }
        else {
          res |= (xBit & yBit) << i;
        }
      } else {
        if (xBit == 1L && yBit == 1L){
          carry = true;
        }
        res |= (xBit ^ yBit) << i;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    //System.out.println(multiply(5, 18));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
