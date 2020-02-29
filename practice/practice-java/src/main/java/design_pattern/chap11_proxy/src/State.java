package design_pattern.chap11_proxy.src;

import java.io.Serializable;

public interface State extends Serializable {

  void insertQuarter();

  void ejectQuarter();

  void turnCrank();

  void dispense();
}
