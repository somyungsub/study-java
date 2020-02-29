package design_pattern.kosta.structure.decorator;

public class Client {

	public static void main(String[] args) {

		Cookie braveCookie = new CookieBrave();
		Cookie milkCookie = new TappingMilk(braveCookie);
		Cookie chocoCookie = new TappingChocolate(milkCookie);
		Cookie milkCookie2 = new TappingMilk(chocoCookie);
		System.out.println("milkCookie2 = " + milkCookie2.getName());

		Cookie milkCookie3 = new TappingMilk(new TappingChocolate(new TappingMilk(new CookieBrave())));
		System.out.println("milkCookie3 = " + milkCookie3.getName());
		
	}

}
