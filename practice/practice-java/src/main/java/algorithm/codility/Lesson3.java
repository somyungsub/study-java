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
}
