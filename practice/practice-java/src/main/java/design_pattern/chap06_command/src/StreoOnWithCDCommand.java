package design_pattern.chap06_command.src;

public class StreoOnWithCDCommand implements Command {
    Stereo stereo;

    public StreoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCd();
        stereo.setVolume(11);
    }
}
