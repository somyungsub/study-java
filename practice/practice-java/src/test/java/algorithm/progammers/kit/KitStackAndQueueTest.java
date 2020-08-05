package algorithm.progammers.kit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KitStackAndQueueTest {

  KitStackAndQueue kit = new KitStackAndQueue();

  @Test
  @DisplayName("주식가격")
  public void test(){
    int[] prices = {1, 2, 3, 2, 3};
    int[] items = kit.solutionStockPrice(prices);

    for (int item : items) {
      System.out.println("item = " + item);
    }
  }

}