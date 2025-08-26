package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {
    // Brute Force
    long d = 1;
    int targetWeight = getWeight(x);
    while (true) {
      if (getWeight(x - d) == targetWeight)
        return x - d;
      if (getWeight(x + d) == targetWeight)
        return x + d;
      d++;
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
    //System.out.println(getWeight(0));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
