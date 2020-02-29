package design_pattern.kosta.behavior.chain;

public class Client {

	public static void main(String[] args) {

		Boundary nomal = new NormalVoltage(230, 210);

		Boundary warn = new WarningVoltage(240, 220);

		Boundary fault = new FaultVoltage(Integer.MAX_VALUE, Integer.MIN_VALUE);

		// nomal -> warn -> fault
		nomal.setNested(warn);
		warn.setNested(fault);

		// action
		nomal.action(220);
		nomal.action(235);
		nomal.action(245);
		warn.action(220);
	}

}
