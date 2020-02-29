package design_pattern.kosta.creation.factory.method;

public class CarFactory extends VehicleFactory {

  // TODO


  @Override
  public Vehicle createVehicle(String size) {
    if (size.equals("small")) {
      return new SportCar();
    } else if (size.equals("large")) {
      return new SedanCar();
    }
    return  null;
  }
}

abstract class Car extends Vehicle {

}

class SportCar extends Car {

}

class SedanCar extends Car {

}
