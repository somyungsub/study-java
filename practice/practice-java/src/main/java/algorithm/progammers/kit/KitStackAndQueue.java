package algorithm.progammers.kit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class KitStackAndQueue {

  public int[] solutionStockPrice(int[] prices) {
    int[] answer = new int[prices.length];

    for (int i = 0; i < prices.length; i++) {
      int price = prices[i];
      int count = -1;

      for (int j = i; j < prices.length; j++) {
        int nextPrice = prices[j];
        count++;
        if (nextPrice - price < 0) {
          break;
        }
      }
      answer[i] = count;
    }
    return  answer;
  }

  public int[] solutionFeatureDevelopment(int[] progresses, int[] speeds) {

    List<Integer> list = new ArrayList<>();
    Queue<Integer> queue = new ArrayDeque<>();

    while (true) {
      for (int i = 0; i < progresses.length; i++) {
        int progress = progresses[i];
        if (progress < 100) {
          progresses[i] = progress + speeds[i];
          if (progresses[i] == 100 && !queue.contains(i)) {
            queue.add(i);
          }
        } else {
          if (!queue.contains(i)) {
            queue.add(i);
          }
        }
      }

      if (isComplete(progresses)) {
        break;
      }
    }

    int count = 0;
    int max = queue.peek();
    while (!queue.isEmpty()) {

      Integer present = queue.poll();
      Integer next = queue.isEmpty() ? present : queue.peek();
      count++;
      if (present > max) {
        max = present;
      }

      if (max + 1 == next || queue.isEmpty()) {
        list.add(count);
        count = 0;
      }

    }

    int[] answer = new int[list.size()];
    for (int j = 0; j < list.size(); j++) {
      answer[j] = list.get(j);
    }

    return answer;
  }

  private boolean isComplete(int[] progresses) {
    for (int progress : progresses) {
      if (progress < 100) {
        return false;
      }
    }
    return true;
  }
}
