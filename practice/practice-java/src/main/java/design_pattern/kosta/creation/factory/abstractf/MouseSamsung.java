package design_pattern.kosta.creation.factory.abstractf;

public class MouseSamsung implements Mouse {
	public MouseSamsung() {
		System.out.println("Samsung 생성자");
	}

	@Override
	public String getName() {
		return "Samsung Mouse";
	}
}
