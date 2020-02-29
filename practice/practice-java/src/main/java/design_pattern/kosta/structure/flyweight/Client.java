package design_pattern.kosta.structure.flyweight;

public class Client {

	public static void main(String[] args) {

		FlyweightFactory ff = new FlyweightFactory();

		Flyweight f1 = ff.getFlyweight("A");
		System.out.println(f1.getData());
		Flyweight f2 = ff.getFlyweight("B");
		System.out.println(f2);
		Flyweight f3 = ff.getFlyweight("C");
		System.out.println(f3);
		Flyweight f4 = ff.getFlyweight("B");
		System.out.println(f4);
		Flyweight f5 = ff.getFlyweight("A");
		System.out.println(f5);
	}
}
