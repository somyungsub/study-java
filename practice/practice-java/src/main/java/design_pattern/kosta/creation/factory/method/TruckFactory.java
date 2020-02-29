package design_pattern.kosta.creation.factory.method;

public class TruckFactory extends VehicleFactory{

	// TODO


  @Override
  public Vehicle createVehicle(String size) {

    if (size.equals("small")) {
      return new SmallTruck();
    } else if (size.equals("large")) {
      return new LargeTruck();
    }
    return null;
  }
}

abstract class Truck extends Vehicle {

}

class SmallTruck extends Truck {

}

class LargeTruck extends Truck {

}
