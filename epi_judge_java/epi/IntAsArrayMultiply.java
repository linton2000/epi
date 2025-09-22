package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class IntAsArrayMultiply {

  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    // Initial Attempt
/*     List<Integer> resL = new ArrayList<>();
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
    
    return resL; */

    // Textbook Sol - Elegant sol using arrays to avoid overflow
    // Time: O(nm), n - no. of partial products, m - no. of digits in each product
    // Space: O(n), n - no. of digits in res
    int sign = (num1.get(0) < 0) ^ (num2.get(0) < 0) ? -1 : 1;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));

    // Product has at most n + m digits for n & m digit operands
    List<Integer> res = new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0));
    for (int i = num1.size() - 1; i >= 0; i--) {
      for (int j = num2.size() - 1; j >= 0; j--) {
        int prod = res.get(i + j + 1) + (num1.get(i) * num2.get(j));
        res.set(i + j + 1, prod % 10);
        res.set(i + j, res.get(i + j) + (prod / 10));
      }
    }

    // Remove leading zeroes
    int firstNonZero = 0;
    while (firstNonZero < res.size() && res.get(firstNonZero) == 0)
      firstNonZero++;
    res = res.subList(firstNonZero, res.size());

    if (res.isEmpty())
      return List.of(0);
    res.set(0, res.get(0) * sign);

    return res;
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
