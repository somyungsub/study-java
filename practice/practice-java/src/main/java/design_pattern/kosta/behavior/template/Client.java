package design_pattern.kosta.behavior.template;

public class Client {

	public static void main(String[] args) {


		Car car = new ManualOperationCar();
		car.playWithOwner();


		car = new AutomaticOperationCar();
		car.playWithOwner();
	}

}
