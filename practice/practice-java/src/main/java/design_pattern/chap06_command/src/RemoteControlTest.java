package design_pattern.chap06_command.src;

public class RemoteControlTest {
    public static void main(String[] args) {
//        SimpleRemoteControl simpleRemoteControl = new SimpleRemoteControl();
        RemoteControl control = new RemoteControl();

        Light light = new Light();
        Light kitchenLight = new Light();
        Stereo stereo = new Stereo();
        GarageDoor garageDoor = new GarageDoor();

        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        LightOnCommand kitLightOnCommand = new LightOnCommand(kitchenLight);
        LightOffCommand kitLightOffCommand = new LightOffCommand(kitchenLight);
        GarageDoorUpCommand garageDoorUpCommand = new GarageDoorUpCommand(garageDoor);
        GarageDoorDownCommand garageDoorDownCommand = new GarageDoorDownCommand(garageDoor);

//        simpleRemoteControl.setCommand(lightOnCommand);
//        simpleRemoteControl.buttonWasPressed();
//
//        simpleRemoteControl.setCommand(garageDoorUpCommand);
//        simpleRemoteControl.buttonWasPressed();

        control.setCommand(0, lightOnCommand, lightOffCommand);
        control.setCommand(1, kitLightOnCommand, kitLightOffCommand);
        control.setCommand(2, garageDoorUpCommand, garageDoorDownCommand);

        System.out.println(control);

        System.out.println("============");
        control.onButtonWasPushed(0);
        control.offButtonWasPushed(0);
        control.onButtonWasPushed(1);
        control.offButtonWasPushed(1);
        control.onButtonWasPushed(2);
        control.offButtonWasPushed(2);
    }
}
