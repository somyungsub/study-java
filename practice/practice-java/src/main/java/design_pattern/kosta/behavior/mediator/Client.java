package design_pattern.kosta.behavior.mediator;

public class Client {

	public static void main(String[] args) {

		ApplicationMediator mediator = new ApplicationMediator();
		Colleague colleague = new MobileColleague(mediator);
		Colleague colleague2 = new ConcreteColleague(mediator);


		// 수신할 colleague 등록
		mediator.addColleague(colleague);
//		mediator.addColleague(colleague2);


		// 메세지 보내기 mediator -> colleague
		colleague.sendMessage("머야머야");
		colleague2.sendMessage("hihihihihi");


//		mediator.send("메롱메롱 ~~", colleague);
//		mediator.send("모바일 하이하이하이", colleague2);
	}

}
