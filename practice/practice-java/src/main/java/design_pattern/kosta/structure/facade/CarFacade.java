package design_pattern.kosta.structure.facade;

public class CarFacade {

	private Car car;

	public CarFacade(Car car) {
		this.car = car;
	}

	public void drive() {
		car.enginStart();
		car.doorLock();
		car.wheelsRoll();
	}

	public void stop() {
		car.doorUnlock();
		car.enginStop();
	}

	public void park() {
		car.doorLock();
		car.wheelsStop();
		car.doorUnlock();
	}

}

class Car {

	public void enginStop() {
		System.out.println("engine stop");
	}

	public void enginStart() {
		System.out.println("engine start");
	}

	public void doorLock() {
		System.out.println("door locked");
	}

	public void doorUnlock() {
		System.out.println("door unlocked");
	}

	public void wheelsRoll() {
		System.out.println("wheels roll");
	}

	public void wheelsStop() {
		System.out.println("wheels stop");
	}

}