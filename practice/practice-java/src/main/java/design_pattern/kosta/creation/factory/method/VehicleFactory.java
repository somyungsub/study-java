package design_pattern.kosta.creation.factory.method;

public abstract class VehicleFactory {

	public abstract Vehicle createVehicle(String size);
	// TODO

	public Vehicle orderVehicle(String size, String color) {
		Vehicle vehicle = createVehicle(size);
		vehicle.testVehicle();
		vehicle.setColor(color);
		return vehicle;
	}
}

abstract class Vehicle {
	String color;

	// abstract Vehicle class
	public void testVehicle() {
		// implementation here
		System.out.println("vehicle test");

	}

	public void setColor(String color) {
		this.color = color;
	}

}

