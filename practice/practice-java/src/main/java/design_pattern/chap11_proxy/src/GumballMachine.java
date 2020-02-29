package design_pattern.chap11_proxy.src;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote {

  String location;

  State state;
  int count;

  public GumballMachine(String location, int number) throws RemoteException {
    this.location = location;
    this.count = number;
  }


  @Override
  public int getCount() throws RemoteException {
    return count;
  }

  @Override
  public String getLocation() throws RemoteException {
    return location;
  }

  @Override
  public State getState() throws RemoteException {
    return state;
  }
}
