package design_pattern.kosta.structure.facade;

public class Client {

	public static void main(String[] args) {

		CarFacade carFacade = new CarFacade(new Car());
		carFacade.drive();
		carFacade.park();
		carFacade.stop();
	}

}
