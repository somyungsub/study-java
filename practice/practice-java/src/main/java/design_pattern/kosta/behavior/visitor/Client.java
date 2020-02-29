package design_pattern.kosta.behavior.visitor;

public class Client {

	public static void main(String[] args) {

		ComputerPart cp = new Computer();
		cp.accept(new ComputerPartDisplayVisitor());
		cp.accept(new ComputerPartAVisitor());

	}

}
