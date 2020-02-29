package design_pattern.chap11_proxy.src;

import java.rmi.Naming;

public class GumballMonitorTestDrive {
  public static void main(String[] args) {
    String[] location = {
        "rmi://santafe.mightygumball.com/gumballmachine",
        "rmi://boulder.mightygumball.com/gumballmachine",
        "rmi://seattle.mightygumball.com/gumballmachine"
    };

    GumballMachineMonitor[] monitor = new GumballMachineMonitor[location.length];

    for (int i = 0; i < location.length; i++) {
      try {
        GumballMachineRemote gumballMachineRemote = (GumballMachineRemote) Naming.lookup(location[i]);
        monitor[i] = new GumballMachineMonitor(gumballMachineRemote);
        System.out.println(monitor[i]);
      } catch (Exception e) {
        e.printStackTrace();
      }

      for (GumballMachineMonitor gumballMachineMonitor : monitor) {
        gumballMachineMonitor.report();
      }

    }
  }
}
