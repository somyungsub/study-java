package design_pattern.chap11_proxy.src;

import java.rmi.Naming;

public class GumballMachineTestDrive {
  public static void main(String[] args) {
    GumballMachineRemote gumballMachineRemote = null;
    int count = 0;

    try {
      count = 5;
      gumballMachineRemote = new GumballMachine(args[0], count);
      Naming.rebind("//" + args[0] + "/gumballmachine", gumballMachineRemote);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
