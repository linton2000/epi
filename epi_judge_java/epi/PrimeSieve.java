package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeSieve {

  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {
    // Initial Attempt - Brute Force (now updated with trial division optimisation)
    // Time: O(n*sqrt(n)), Space: O(m), where m is no. of primes
    // Invariant: End of each iteration i, `res` contains exactly all primes in range [2...i]
/*     List<Integer> res = new ArrayList<>();

    for (int i = 2; i <= n; i++) {
      boolean isPrime = true;
      for (int j = 2; j <= Math.sqrt(i); j++) {
        if (i % j == 0) {
          isPrime = false;
          break;
        }
      }
      if (isPrime)
        res.add(i);
    }
    
    return res; */

    // Textbook Sol - Sieving out prime multiples
    // Time: O(nlog(log(n))), Space: O(n)
    // Invariants:
    //  - At end of iteration i, `isPrimes[0...i]` has true for primes & false for non-primes
    //  - " " ", `isPrimes` has false for all multiples of primes in 0...i
    List<Integer> res = new ArrayList<>();
    boolean[] isPrimes = new boolean[n + 1];
    Arrays.fill(isPrimes, true);  // All nums start as prime
    isPrimes[0] = false;
    isPrimes[1] = false;

    for (int i = 2; i <= n; i++) {
      if (isPrimes[i]) {
        for (int j = 2*i; j <= n; j += i) {
          isPrimes[j] = false;
        }
        res.add(i);
      }
    }

    return res;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
