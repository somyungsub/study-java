package algorithm.codility;

public class Lesson3 {

  public int frog(int X, int Y, int D) {
    int count = 0;
    while (X < Y) {
      Y = Y - D;
      count++;
    }

    return count;
  }

  public int permMissingElem(int[] A) {

    int sum = 0, sum2 = 0;
    for (int i = 1; i <= A.length + 1; i++) {
      sum += i;
    }

    for (int i = 0; i < A.length ; i++) {
      sum2 += A[i];
    }

    return sum - sum2;
  }
}
