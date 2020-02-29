package design_pattern.kosta.structure.composite;

public class Client {

	public static void main(String[] args) {

		Directory directory = new Directory();
		directory.add(new File());
		directory.add(new Directory());
		Directory directory1 = new Directory();
		directory1.add(directory);


	}

}
