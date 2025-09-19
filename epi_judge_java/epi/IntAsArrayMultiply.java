package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntAsArrayMultiply {

  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    // Initial Attempt
    List<Integer> resL = new ArrayList<>();
    long res = 0;
    long sign = 1;

    if (num1.get(0) < 0){
      num1.set(0, Math.abs(num1.get(0)));
      sign *= -1;
    }
    if (num2.get(0) < 0){
      num2.set(0, Math.abs(num2.get(0)));
      sign *= -1;
    }

    long mask1 = 1;
    for (int i = num1.size() - 1; i >= 0; i--) {
      long mask2 = 1;

      for (int j = num2.size() - 1; j >= 0; j--) {
        res += num1.get(i) * num2.get(j) * mask1 * mask2;
        mask2 *= 10;
      }
      mask1 *= 10;
    }

    for (char c : String.valueOf(res).toCharArray()) {
      resL.add(c - '0');  // convert char to int
    }

    resL.set(0, (int) (resL.get(0) * sign));
    
    return resL;
  }

  public static void main(String[] args) {
    //System.out.println(multiply(Arrays.asList(-1, 4, 2, 9), Arrays.asList(-2, 2, 9)));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
