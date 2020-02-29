package design_pattern.kosta.structure.proxy;

public class Client {

	public static void main(String[] args) {
		
		// TODO

		IService proxy = new Proxy();
//		IService proxy = new Service();
		System.out.println(proxy.runSomething());
	}

}
