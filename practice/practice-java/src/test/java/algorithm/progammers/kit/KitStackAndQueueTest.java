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

  @Test
  @DisplayName("기능개발")
  public void test2(){
    int[] progresses = {93, 30, 55};
    int[] speeds = {1, 30, 5};

    int[] ints = kit.solutionFeatureDevelopment(progresses, speeds);
    for (int item : ints) {
      System.out.println("item = " + item);
    }
  }

  @Test
  @DisplayName("기능개발")
  public void test2_1(){
    int[] progresses = {93, 30, 55, 50};
    int[] speeds = {1, 30, 5, 10};

    int[] ints = kit.solutionFeatureDevelopment(progresses, speeds);
    for (int item : ints) {
      System.out.println("item = " + item);
    }
  }

}