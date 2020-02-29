package design_pattern.kosta.behavior.observer;

public class Client {

	public static void main(String[] args) {

		NewsMachine newsMachine = new NewsMachine();	// subject
		AnnualSubscriber as = new AnnualSubscriber();
		EventSubscriber es = new EventSubscriber();

		// subject -> 관찰자 등록
		newsMachine.add(as);
		newsMachine.add(es);


		// 상태를 변경
		newsMachine.setNews("오늘 교육", "꿀잼쓰~");
		newsMachine.delete(es);
		newsMachine.setNews("오늘 추워", "밥꿀맛");
	}

}
