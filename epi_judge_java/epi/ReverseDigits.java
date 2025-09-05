package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")
  public static long reverse(int x) {
    // Brute Force - Repeated div & mod by 10
/*     long sign = 1;
    if (x < 0){
      sign = -1;
      x = -x;
    }

    long res = 0;
    int i = (int) Math.log10(x);  // Starting from MSB

    while (x > 0) {
      res += (x % 10) * Math.pow(10, i);
      x /= 10;
      i -= 1;
    }
    return res * sign; */

    // Optimal - Same sol but simplified code
    long res = 0;
    while (x != 0) {  // Repeated div by 10 always results in 0
      res = (res * 10) + (x % 10);  // Very elegant!
      x /= 10;
    }
    return res;
  }

  public static void main(String[] args) {
    //System.out.println(reverse(1799113645));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
