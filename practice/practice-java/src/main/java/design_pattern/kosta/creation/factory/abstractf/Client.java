package design_pattern.kosta.creation.factory.abstractf;

public class Client {

	public static void main(String[] args) {


		// TODO

		FactoryOfComputerFactory f = new FactoryOfComputerFactory();
		Computer c = f.createComputer("LG");
		System.out.println(c.getKeyboard().getName());
		System.out.println(c.getMouse().getName());

		Computer computer = f.createComputer("Samsung");
		System.out.println("computer.getKeyboard().getName() = " + computer.getKeyboard().getName());
		System.out.println("computer.getMouse().getName() = " + computer.getMouse().getName());

	}

}
