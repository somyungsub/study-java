package design_pattern.kosta.behavior.memento;

public class Client {

	public static void main(String[] args) {

		Originator originator = new Originator();
		CareTaker careTaker = new CareTaker();

		originator.setState("1");	// 현재상태업데이트
		originator.setState("2");
		Memento memento = originator.saveStateToMemento();
		careTaker.add(memento);	// 2


		originator.setState("3");
		careTaker.add(originator.saveStateToMemento());	// 3
		originator.setState("4");  // 현재상태 : 4

		System.out.println("현재사테" + originator.getState());


		Memento m1 = careTaker.getMemento(0);	// 2
		originator.getStateFromMemento(m1);					// 업데잍 -> 현재상태 4 -> 2
		System.out.println("현재사테" + originator.getState());	// 2

		originator.getStateFromMemento(careTaker.getMemento(1));  // 3, 현재상태 3
		System.out.println("현재사테" + originator.getState());	// 3
	}

}
