package design_pattern.chap06_command.src;

public class GarageDoorUpCommand implements Command {
    GarageDoor garageDoor;


    public GarageDoorUpCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        garageDoor.up();
    }
}
