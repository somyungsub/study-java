package design_pattern.kosta.creation.factory.simple;

public class VehicleFactory {

	// TODO
	public enum VehicleType {Bike, Car, Truck}

	public static Vehicle createVehicle(VehicleType type) {
		if (type.equals(VehicleType.Bike)) {
			return new Bike();
		} else if (type.equals(VehicleType.Car)) {
			return new Car();
		} else if (type.equals(VehicleType.Truck)) {
			return new Truck();
		}

		return null;
	}

}

abstract class Vehicle {
	abstract void doSomething();
}

class Bike extends Vehicle {
	public void doSomething() {
		System.out.println("go Bike");
	}
}

class Car extends Vehicle {
	public void doSomething() {
		System.out.println("go Car");
	}
}

class Truck extends Vehicle {
	public void doSomething() {
		System.out.println("go Truck");
	}
}