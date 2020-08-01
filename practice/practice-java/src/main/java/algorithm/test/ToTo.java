package algorithm.test;

import java.util.*;
import java.util.stream.Collectors;

public class ToTo {
  public void test(String input) {
    int data = Integer.parseInt(input);
    if (data % 2 == 0) {
      System.out.println("even");
    } else {
      System.out.println("odd");
    }
  }


  public void test1(String input) {
    for (int i = 0; i < input.length() - 1; i++) {
      char c = input.charAt(i);
      char next = input.charAt(i + 1);

      if (String.valueOf(c).equals("1")) {
        if (String.valueOf(next).equals("1")) {
          System.out.println("false");
          return;
        }
      }

      if (input.length() - 1 == i+1 && String.valueOf(next).equals("1")) {
        System.out.println("false");
        return;
      }
    }
    System.out.println("true");
  }

  public void test2(String input) {
    String[] lotto = input.split("\\s");
    if (lotto.length != 6) {
      System.out.println("false");
      return;
    }

    for (int i = 0; i < lotto.length; i++) {
      int prev = Integer.parseInt(lotto[i]);

      for (int j = i+1; j < lotto.length; j++) {
        int next = Integer.parseInt(lotto[j]);

        if (prev < 0 || prev > 45) {
          System.out.println("false");
          return;
        }
        if (prev == next) {
          System.out.println("false");
          return;
        }
        if (prev > next) {
          System.out.println("false");
          return;
        }
      }
    }
    System.out.println("true");

  }

  public void test3(String input) {
    String[] arr = input.split("\\s");
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
//      map.putIfAbsent(Integer.parseInt(arr[i]), Function.compute(arr[i]));
//      map.putIfAbsent(Integer.parseInt(arr[i]), Function.compute(Integer.parseInt(arr[i])));
      map.putIfAbsent(Integer.parseInt(arr[i]), Integer.parseInt(arr[i]));
    }

    for (int i = 0; i < arr.length; i++) {
      map.get(Integer.parseInt(arr[i]));
    }

    String collect = Arrays.stream(arr)
        .map(i -> String.valueOf(map.get(Integer.parseInt(i))))
        .collect(Collectors.joining(" "));
    System.out.println(collect);
  }

  public void test4(String input) {
    String[] split = input.split("\\s");
    Stack<String> stack = new Stack<>();

    for (int i = 0; i < split.length; i++) {
      List<String> list = new ArrayList<>();
      String s = split[i];
      if (!stack.contains(s)) {
        stack.push(s);
      } else {
        stack.remove(s);
        stack.push(s);
      }

      if (stack.size() > 5) {
        stack.remove(stack.firstElement());
      }

      Stack<String> clone = (Stack<String>) stack.clone();
      int size = clone.size();
      for (int i1 = 0; i1 < size; i1++) {
        list.add(clone.pop());
      }
      String collect = list.stream()
          .collect(Collectors.joining(" "));
      System.out.println(collect);
    }
  }


  public void test5(String input) {
    String[] split = input.split("\\n");
    String[] kim = split[0].split(" ");
    String[] lee = split[1].split(" ");
    List<String> result = new ArrayList<>();

    int sum = 0;

    for (int i = 0; i < kim.length; i++) {
      int kimMoney = Integer.parseInt(kim[i]);
      int leeMoney = Integer.parseInt(lee[i]);

      int resultMoney = kimMoney - leeMoney;
      sum += resultMoney;
      if (resultMoney > 0 && sum > 0) {
        result.add(String.valueOf(resultMoney));
      } else {
        result.add("0");
      }
    }
  }

}
