package design_pattern.chap11_proxy.src;

import design_pattern.chap10_state.src.GumballMachine;

public class NoQuarterState implements State {

  transient GumballMachine gumballMachine;  // 직렬화하지 않도록 설정 (transient)

  @Override
  public void insertQuarter() {
    System.out.println("1");
  }

  @Override
  public void ejectQuarter() {
    System.out.println("2");
  }

  @Override
  public void turnCrank() {
    System.out.println("3");
  }

  @Override
  public void dispense() {
    System.out.println("4");
  }
}
