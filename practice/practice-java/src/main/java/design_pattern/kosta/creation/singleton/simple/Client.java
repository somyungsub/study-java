package design_pattern.kosta.creation.singleton.simple;

public class Client {

	public static void main(String[] args) {
		
		// TODO
		Singleton singleton = Singleton.getInstance();
		System.out.println("singleton = " + singleton);
		Singleton singleton2 = Singleton.getInstance();
		System.out.println("singleton2 = " + singleton2);

		if (singleton == singleton2) {
			System.out.println("동일");
		} else {
			System.out.println("동일 안함");
		}

		singleton.doSomething();
		singleton2.doSomething();
	}

}
