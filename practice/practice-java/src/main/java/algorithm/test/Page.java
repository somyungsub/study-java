package algorithm.test;

import java.util.Arrays;
import java.util.stream.Collectors;

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

  public String page2(String contents) {
    String[] lines = contents.split("\n");
    StringBuilder sb = new StringBuilder();

    int size = Integer.parseInt(lines[0]);
    for (int i = 1; i <= size; i++) {
      String input = lines[i];
      String output = transInput(input);
      sb.append(output).append(System.lineSeparator());
    }

    return sb.toString();
  }

  private String transInput(String input) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if (Character.isAlphabetic(c) || Character.isDigit(c)) {
        sb.append(c);
      } else {
        sb.append(" ");
      }
    }

    String result = Arrays.stream(sb.toString().split(" "))
        .filter(s -> !s.isBlank())
        .collect(Collectors.joining(" "));

    return result;
  }

  public int[] page3(int[] arr1, int[] arr2) {
    int[] sort = new int[arr1.length + arr2.length];
    int key = 0;
    int count = 0;

    for (int i = 0; i < arr1.length; i++) {

      for (int j = count; j < arr2.length; j++) {
        if (arr1[i] >= arr2[j]) {
          count++;
          sort[key++] = arr2[j];
        }
      }
      sort[key++] = arr1[i];
    }

    return sort;
  }
}
