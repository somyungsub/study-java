package algorithm.codility;

public class Lesson2 {

  public int[] cyclicRotation(int[] A, int K) {

    int[] result = new int[A.length];

    for (int i = 0; i < A.length; i++) {
      int rotateIndex = (i + K) % A.length;
      result[rotateIndex] = A[i];
    }

    return result;
  }
}
