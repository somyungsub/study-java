package chap01;

public class Code01 {

  public int sumRecursion(int n) {
    if (n <= 0) {
      return  0;
    }

    if (n == 1) {
      return 1;
    }

    return n + sumRecursion(n - 1);
  }

  public int sum(int n) {
    int sum = 0;

    for (int i = 1; i <= n; i++) {
      sum += i;
    }

    return sum;
  }

  public int 연습문제1_1_factorial_recursion(int n) {
    if (n <= 1) {
      return 1;
    }
    return n * 연습문제1_1_factorial_recursion(n - 1);
  }

  public int 연습문제1_1_factorial(int n) {
    if (n <= 1) {
      return 1;
    }

    int fact = 1;
    for (int i = 1; i <= n; i++) {
      fact *= i;
    }
    return fact;
  }

  public int[] 연습문제1_2_recursion() {
    int[] arr = {1, 2, 3, 4, 5, 6};
    for (int i = 0; i < arr.length; i++) {
      arr[i] = acc(arr[i]);
    }

    return arr;
  }

  private int acc(int n) {
    if (n <= 1) {
      return 1;
    }
    return n + acc(n - 1);
  }

  public int power(int x, int n) {
    if (n == 0) {
      return 1;
    } else if (x == 1) {
      return x;
    } else {
      return x * power(x, n - 1);
    }
  }

}
