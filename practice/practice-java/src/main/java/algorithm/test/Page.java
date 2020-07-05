package algorithm.test;

public class Page {

  int[] memo = new int[60];

  public String page1(String contents) {
    String[] lines = contents.split("\n");
    for (String line : lines) {
      System.out.println("line = " + line);
    }
    int size = Integer.parseInt(lines[0]);
    String answer = "";


    for (int i = 0; i < 60; i++) {
      memo[i] = 0;
    }

    for (int i = 0; i < size; i++) {
      int input = Integer.parseInt(lines[i + 1]);
      if (input == 1) {
        answer += "2 ";
        continue;
      }
      for (int j = 0; j < 59; j++) {
        int first = fibonacci(j);
        int last = fibonacci(j + 1);
        if (input > first && input <= last) {
          answer += last + " ";
          break;
        }
      }
    }
    return answer;
  }

  private int fibonacci(int n) {
    if (memo[n] != 0) {
      return memo[n];
    }

    if (n == 0 || n == 1) {
      memo[n] = 1;
    } else {
      memo[n] = fibonacci(n - 1) + fibonacci(n - 2);
    }
    return memo[n];
  }
}
