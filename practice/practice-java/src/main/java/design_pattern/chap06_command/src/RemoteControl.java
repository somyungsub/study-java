package design_pattern.chap06_command.src;

import java.util.ArrayList;
import java.util.List;

public class RemoteControl {
    List<Command> onCommands;
    List<Command> offCommands;

    public RemoteControl() {
        onCommands = new ArrayList<>();
        offCommands = new ArrayList<>();

        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands.add(noCommand);
            offCommands.add(noCommand);
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands.set(slot, onCommand);
        offCommands.set(slot, offCommand);
    }

    public void onButtonWasPushed(int slot) {
        onCommands.get(slot).execute();
    }

    public void offButtonWasPushed(int slot) {
        offCommands.get(slot).execute();
    }

    @Override
    public String toString() {
        return "RemoteControl{" +
                "onCommands=" + onCommands +
                ", offCommands=" + offCommands +
                '}';
    }
}
