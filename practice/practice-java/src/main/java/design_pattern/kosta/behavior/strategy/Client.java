package design_pattern.kosta.behavior.strategy;

public class Client {

	public static void main(String[] args) {

		Solider solider = new Solider();


		// 전략을 주입해서 실행
		solider.setStrategy(new StrategyGrenade());
		solider.runStrategy();

		// 전략을 변경하여 주입하여 실행
		solider.setStrategy(new StrategyGun());
		solider.runStrategy();


	}

}
