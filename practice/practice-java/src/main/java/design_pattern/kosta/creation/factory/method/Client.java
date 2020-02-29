package design_pattern.kosta.creation.factory.method;

public class Client {

	public static void main(String s[]) {

		// TODO
		VehicleFactory carf = new CarFactory();
		Vehicle vehicle = carf.orderVehicle("large", "orange");

		System.out.println("vehicle.color = " + vehicle.color);
		VehicleFactory biket = new VehicleFactory() {
			@Override
			public Vehicle createVehicle(String size) {
				if (size.equals("small")) {
					return new MountainBike();
				} else if (size.equals("large")) {
					return new CityBike();
				}
				return null;
			}
		};

		Vehicle vehicle1 = biket.orderVehicle("small", "red");
		System.out.println("vehicle1.color = " + vehicle1.color);
	}

}

abstract class Bike extends Vehicle {

}

class MountainBike extends Bike {

}

class CityBike extends Bike {

}