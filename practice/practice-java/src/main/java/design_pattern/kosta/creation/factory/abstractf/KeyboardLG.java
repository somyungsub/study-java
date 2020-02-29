package design_pattern.kosta.creation.factory.abstractf;

public class KeyboardLG implements Keyboard {
	public KeyboardLG() {
		System.out.println("LG 키보드 생성자");
	}

	@Override
	public String getName() {
		return "LG Keyboard";
	}
}
