package design_pattern.kosta.creation.prototype;

public class Client {

	public static void main(String[] args) throws CloneNotSupportedException {
		
		// TODO

		Users origin = new Users(null);
		origin.load();

		Users copy = (Users) origin.clone();
		System.out.println("copy = " + copy);

		origin.getUserList().set(0, "aasdada");


		if (origin.getUserList() == copy.getUserList()) {
			System.out.println("shallow");
		} else {
			System.out.println("Deep");
		}


		if (origin == copy) {
			System.out.println(" none Copy ");
		} else{
			System.out.println("copy!");
		}



	}

}
