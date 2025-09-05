package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    // Initial Attempt - Repeated div by 10 & reverse digits, i.e. 1331: 1331/100 == rev(31)?
    if (x < 0) return false;  // The sign makes it non-palindromic
    if (x < 10) return true;  // All single digits are palindromes

    int i = 0;
    int mid = ((int) Math.log10(x) + 1) / 2;  // Index of int midpoint
    int rev = 0;

    while (i < mid) {
      rev = (rev * 10) + (x % 10);
      x /= 10;
      i++;
    }
      
    if (hasEvenDigits(x) != hasEvenDigits(rev))  // Shave off the odd middle digit
      x /= 10;
    
    return x == rev;
  }

  public static boolean hasEvenDigits(int x){
    if (x < 10) return false;
    return (((int) (Math.log10(x)) + 1) % 2) == 0;
  }

  public static void main(String[] args) {
    System.out.println(isPalindromeNumber(1810));
/*     System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal()); */
  }
}
