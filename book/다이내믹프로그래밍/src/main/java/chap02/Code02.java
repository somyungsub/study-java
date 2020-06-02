package chap02;

public class Code02 {

  public int 연습문제2_1_(int n) {
    if (n == 1) {
      return 0;
    }

    return 2 * (연습문제2_1_(n - 1) + 1);
  }
}
