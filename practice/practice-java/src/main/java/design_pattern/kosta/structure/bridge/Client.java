package design_pattern.kosta.structure.bridge;

public class Client {

	public static void main(String[] args) {
		new Warrior(new Bow()).handle();
		new Warrior(new Sword()).handle();
		new Warrior(new Gun()).handle();
	}

}
