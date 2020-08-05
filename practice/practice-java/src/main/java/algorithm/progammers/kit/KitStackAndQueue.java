package algorithm.progammers.kit;

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
}
