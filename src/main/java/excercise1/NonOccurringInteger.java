package excercise1;

import java.util.HashSet;
import java.util.Set;

public class NonOccurringInteger {

  /**
   *
   * Write a simple Java >= 7 program that returns the smallest non occurring integer in a given
   * Array.
   * E.g Given an Array1 = [1,3,6,4,1,2] returns 5, and Array2 = [5, -1, -3] returns 1
   *
   */

  public static int findSmallestNonOccurringInteger(int[] array) {
    Set<Integer> arrayValues = new HashSet<>();

    for (int num : array) {
      if (num > 0) {
        arrayValues.add(num);
      }
    }

    int smallestNonOccurring;
    for (smallestNonOccurring = 1; arrayValues.contains(smallestNonOccurring); smallestNonOccurring++) {
      // The loop condition does all the work, no body needed
    }

    return smallestNonOccurring;
  }


  public static void main(String[] args) {
    int[] array1 = {1, 3, 6, 4, 1, 2};
    int[] array2 = {5, -1, -3};

    System.out.println("The smallest non-occurring integer in array1 is: " + findSmallestNonOccurringInteger(array1)); // Output: 5
    System.out.println("The smallest non-occurring integer in array2 is: " + findSmallestNonOccurringInteger(array2)); // Output: 1
  }
}
