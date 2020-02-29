package design_pattern.kosta.creation.factory.simple;

public class Client {

	public static void main(String[] args) {
		
		// TODO
		Vehicle vehicle = VehicleFactory.createVehicle(VehicleFactory.VehicleType.Bike);
		vehicle.doSomething();
		Vehicle vehicle2 = VehicleFactory.createVehicle(VehicleFactory.VehicleType.Car);
		vehicle2.doSomething();

	}

}
