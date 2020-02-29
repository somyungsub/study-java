package design_pattern.chap11_proxy.src;

public class GumballMachineMonitor {
  GumballMachineRemote gumballMachineRemote;

  public GumballMachineMonitor(GumballMachineRemote gumballMachineRemote) {
    this.gumballMachineRemote = gumballMachineRemote;
  }

  public void report() {
    try {
      System.out.println("뽑기 기계 위치 : " + gumballMachineRemote.getLocation());
      System.out.println("햔재 재고 = " + gumballMachineRemote.getCount() + " 개");
      System.out.println("현재 상태 = " + gumballMachineRemote.getState());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
