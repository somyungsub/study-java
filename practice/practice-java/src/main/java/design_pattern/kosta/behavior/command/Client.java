package design_pattern.kosta.behavior.command;

public class Client {

	public static void main(String[] args) {

		// receive
		Light light = new Light();


		// command
		Command onCmd = new TurnOnLightCommand(light);
		Command offCmd = new TurnOffLightCommand(light);

		// invoker
		Switch sw = new Switch(onCmd, offCmd);
		sw.flipUp();
		sw.flipDown();

	}

}
