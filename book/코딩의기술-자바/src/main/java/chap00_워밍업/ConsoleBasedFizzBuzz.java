package chap00_워밍업;

public class ConsoleBasedFizzBuzz implements FizzBuzz {

  @Override
  public void print(int from, int to) {
    for (int i = from; i < to; i++) {
      if (isThree(i) && isFive(i)) {
        System.out.println("FizzBuzz");
      } else if (isThree(i)) {
        System.out.println("Fizz");
      } else if (isFive(i)) {
        System.out.println("Buzz");
      }
    }
  }

  private boolean isThree(int i) {
    return i % 3 == 0;
  }

  private boolean isFive(int i) {
    return i % 5 == 0;
  }
}
