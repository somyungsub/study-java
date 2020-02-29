package design_pattern.kosta.creation.factory.abstractf;

public class KeyboardSamsung implements Keyboard {
	public KeyboardSamsung() {
		System.out.println("Samsung 키보드 생성자");
	}

	@Override
	public String getName() {
		return "Samsung Keyboard";
	}
}
