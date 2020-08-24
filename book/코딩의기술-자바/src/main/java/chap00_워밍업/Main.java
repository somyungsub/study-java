package chap00_워밍업;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    FizzBuzz fizzBuzz = new ConsoleBasedFizzBuzz();

    fizzBuzz.print(1, scanner.nextInt());
  }
}
