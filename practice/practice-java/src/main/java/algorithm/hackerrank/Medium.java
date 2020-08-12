package algorithm.hackerrank;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Medium {

  /*
    최소 변환 값 구하기
   */
  public int formingMagicSquare(int[][] s) {
    int size = s.length;

    IntStream genNumber = IntStream.rangeClosed(1, size * size);
    int sum = genNumber.sum();
    int rowAvg = sum / size;


    return Arrays.stream(s)
        .map(ints -> Arrays.stream(ints).sum())
        .mapToInt(integer -> Math.abs(rowAvg - integer.intValue()))
        .sum();
  }

  /*
    BigInteger 팩토리얼
   */
  public void extraLongFactorials(int n) {
    System.out.println(factorial(BigInteger.valueOf(n)));
  }

  private BigInteger factorial(BigInteger n) {
    if (n.intValue() == 1) {
      return BigInteger.ONE;
    }
    return n.multiply(factorial(BigInteger.valueOf(n.intValueExact() - 1)));
  }

  /*
    Bigger is Greater
   */
  public String biggerIsGreater(String w) {

    int length = w.length();
    int count = 0;
    for (int i = 0; i < length-1; i++) {
      char c1 = w.charAt(i);
      char c2 = w.charAt(i + 1);

      if (c2 >= c1) {
        count++;
      }
    }
    if (count + 1 == length) {
      return "no answer";
    }


    String recur = recur(w);
    if (!w.equals(recur)) {
      return recur;
    } else {
      return biggerIsGreater(w.substring(1).concat(String.valueOf(w.charAt(0))));
    }
  }

  public String recur(String w) {
    if (w.length() == 2) {
      char c1 = w.charAt(0);
      char c2 = w.charAt(1);
      return c1 <= c2
          ? String.valueOf(w.charAt(1)).concat(String.valueOf(w.charAt(0)))
          : w;
    }
    System.out.println("w = " + w);
    return w.charAt(0) + recur(w.substring(1));
  }

  /*
    시간 -> 문자열 변환
   */
  public String timeInWords(int h, int m) {
    String[] hours = {"one", "two", "three", "four", "five", "six", "seven", "eight", "night", "ten", "eleven", "twelve"};
    String[] tenUnit = {"ten", "twenty", "half"};
    String[] minutes = {"quarter", "one", "two", "three", "four", "five", "six", "seven", "eight", "night", "ten", "eleven", "twelve", "thirteen", "fourteen"};

    String hour = hours[h - 1];

    if (m == 0) {
      return hour + " o' clock";
    } else if (m % 10 == 0) {
      return m <= 30 ? tenUnit[(m / 10) - 1] + " minutes past " + hour
          : tenUnit[(60-m / 10) - 1] + " minutes to " + hour;
    } else if (m < 30) {
      String minute = minutes[m % 15];
      return m % 15 == 0 ? minute + " past " + hour : minute + " minutes past " + hour;
    } else {
      int i = 60 - m;
      String minute = minutes[i % 15];
      hour = hours[h];
      return i % 15 == 0 ? minute + " to " + hour : minute + " minutes to " + hour;
    }
  }

  /////////////
  public long test1(String s) {
    int sum = 0;
    int start = 'A';
    for (int i = 0; i < s.length(); i++) {
      int value = s.charAt(i);
      int min = 0;

      if (value > start) {
        min = Math.min(value - start, 26 - (value - start));
      } else {
        min = Math.min(value - start + 26, start - value);
      }
      sum += min;
      start = value;
    }


//      int min = Math.min(c - start, 26 + start - c);
//      sum += min;

    return sum;
  }

  static class BinaryStore {
    int index;
    String value;

    public BinaryStore(int index, String value) {
      this.index = index;
      this.value = value;
    }
  }


  public List<Integer> test22(List<List<Integer>> arr) {
    List<Integer> resultList = new ArrayList<>();
    List<BinaryStore> binaryStoreList = new ArrayList<>();

    for (int i = 0; i < arr.size(); i++) {
      List<Integer> list = arr.get(i);
      list.sort(Integer::compareTo);
      Integer max = list.get(list.size() - 1);

      int[] binary = new int[max + 1];
      for (Integer integer1 : list) {
        int index = max - integer1;
        binary[index] = 1;
      }

      String resultString = Arrays.stream(binary)
          .mapToObj(String::valueOf)
          .collect(Collectors.joining());

      binaryStoreList.add(new BinaryStore(i, resultString));

    }

    for (int i = 0; i < binaryStoreList.size(); i++) {
      BinaryStore binaryStore = binaryStoreList.get(i);
      for (int j = i + 1; j < binaryStoreList.size(); j++) {
        BinaryStore binaryStore2 = binaryStoreList.get(j);
        String value = binaryStore.value;
        String value2 = binaryStore2.value;
        int length1 = value.length();
        int length2 = value2.length();

        int size = Math.max(length1, length2);
        int max = 0;

        for (int k = 0; k < size; k++) {
          char c1 = length1 > k ? value.charAt(length1 - k - 1) : '0';
          char c2 = length2 > k ? value2.charAt(length2 - k - 1) : '0';

          max = c1 > c2 ? i : j;
        }
        if (max == j) {
          BinaryStore temp = new BinaryStore(binaryStore.index, binaryStore.value);
          BinaryStore temp2 = new BinaryStore(binaryStore2.index, binaryStore2.value);

          binaryStoreList.set(i, temp2);
          binaryStoreList.set(j, temp);
          binaryStore = binaryStoreList.get(i);
        }

      }
    }

    for (BinaryStore binaryStore : binaryStoreList) {
      resultList.add(binaryStore.index);
    }

    return resultList;
  }
  public List<Integer> test22_backup(List<List<Integer>> arr) {
    List<Integer> resultList = new ArrayList<>();
//    Map<String, Integer> map = new HashMap<>();
    List<BinaryStore> binaryStoreList = new ArrayList<>();

    for (int i = 0; i < arr.size(); i++) {
      List<Integer> list = arr.get(i);
      list.sort(Integer::compareTo);
      Integer max = list.get(list.size() - 1);

      int[] binary = new int[max + 1];
      for (Integer integer1 : list) {
        int index = max - integer1;
        binary[index] = 1;
      }

      String resultString = Arrays.stream(binary)
          .mapToObj(String::valueOf)
          .collect(Collectors.joining());

      binaryStoreList.add(new BinaryStore(i, resultString));

//      int result = Integer.parseInt(resultString, 2);
//      map.put(i, resultString);
    }
//    System.out.println("map = " + map);
    System.out.println(binaryStoreList);
    for (int i = 0; i < binaryStoreList.size(); i++) {
      BinaryStore binaryStore = binaryStoreList.get(i);
      for (int j = i + 1; j < binaryStoreList.size(); j++) {
        BinaryStore binaryStore2 = binaryStoreList.get(j);
        String value = binaryStore.value;
        String value2 = binaryStore2.value;
        int length1 = value.length();
        int length2 = value2.length();

        int size = Math.max(length1, length2);
        int max = 0;

        for (int k = 0; k < size; k++) {
          System.out.println("=-=============");
          char c1 = length1 > k ? value.charAt(length1 - k - 1) : '0';
          char c2 = length2 > k ? value2.charAt(length2 - k - 1) : '0';
          System.out.println("c1 = " + c1);
          System.out.println("c2 = " + c2);

          max = c1 > c2 ? i : j;
        }
        System.out.println("max = " + max);
        System.out.println("=================================");
        if (max == j) {
          System.out.println("swap");
          BinaryStore temp = new BinaryStore(binaryStore.index, binaryStore.value);
          BinaryStore temp2 = new BinaryStore(binaryStore2.index, binaryStore2.value);

          binaryStoreList.set(i, temp2);
          binaryStoreList.set(j, temp);
          binaryStore = binaryStoreList.get(i);
          System.out.println("swap : " + binaryStoreList);
        }

      }
    }

    System.out.println(binaryStoreList);

//    for (int i = 0; i < map.size(); i++) {
//      String value = map.get(i);
//      for (int j = i + 1; j < map.size(); j++) {
//        String value2 = map.get(j);
//        int length1 = value.length();
//        int length2 = value2.length();
//
//        int size = Math.max(length1, length2);
//
//        int max = 0;
//
//        for (int k = 0; k < size; k++) {
//
//          System.out.println("=-=============");
//
//          char c1 = length1 > k ? value.charAt(length1 - k - 1) : '0';
//          char c2 = length2 > k ? value2.charAt(length2 - k - 1) : '0';
//          System.out.println("c1 = " + c1);
//          System.out.println("c2 = " + c2);
//
//          max = c1 > c2 ? i : j;
//        }
//
//        swap(i, j);
//        System.out.println("max = " + max);
//
//
//      }
//
//
//    }

//    Stream<String> sorted = map.keySet().stream().sorted(Comparator.reverseOrder());
//    sorted.forEach(key -> resultList.add(map.get(key)));
    for (BinaryStore binaryStore : binaryStoreList) {
      resultList.add(binaryStore.index);
    }

    return resultList;
  }


  public List<Integer> test222(List<List<Integer>> arr) {
    List<Integer> resultList = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();

    System.out.println("before : " + arr);
    for (int i = 0; i < arr.size(); i++) {
      List<Integer> list = arr.get(i);
      System.out.println("==============");
      System.out.println(list);
      list.sort(Integer::compareTo);
      System.out.println(list);

      Integer max = list.get(list.size() - 1);
      System.out.println("max = " + max);

      int[] re = new int[max + 1];
      for (Integer integer1 : list) {
        int index = max - integer1;
        re[index] = 1;
      }

      System.out.println("result : " + Arrays.toString(re));
      String collect = Arrays.stream(re)
          .mapToObj(value -> String.valueOf(value))
          .collect(Collectors.joining());
      System.out.println("collect = " + collect);

      int result = Integer.parseInt(collect, 2);

      System.out.println("result = " + result);
      map.put(result, i);
    }

    System.out.println("after : " + arr);

    System.out.println("map = " + map);
    Stream<Integer> sorted = map.keySet().stream().sorted(Comparator.reverseOrder());
    sorted.forEach(key -> resultList.add(map.get(key)));

    return resultList;
  }

  public List<Integer> test3(int k, List<Integer> arrival, List<Integer> load) {
    Stack<Integer> stack = new Stack();

    for (int i = 0; i < arrival.size(); i++) {
      Integer arrivalTime = arrival.get(i);
      Integer loadTime = load.get(i);
      int finish = arrivalTime + loadTime - 1;
      System.out.println("finish = " + finish);

      if (stack.size() == k) {
        System.out.println("dropped");
      }
    }

    return null;
  }

}
