package design_pattern.kosta.behavior.nullobject;

public class Client {

	public static void main(String[] args) {

		AbstractCustomer customer1 = CustomerFactory.getCustomer("Rob");
		AbstractCustomer customer2 = CustomerFactory.getCustomer("Job");
		AbstractCustomer customer3 = CustomerFactory.getCustomer("park");

		System.out.println(customer1.getName());
		System.out.println(customer2.getName());
		System.out.println(customer3.getName());


	}

}
