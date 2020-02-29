package design_pattern.chap06_command.src;

public class Stereo {
    private int volume;
    public void on() {
        System.out.println("스트레소 on!");
    }

    public void off() {
        System.out.println("스트레소 off!");
    }

    public void setCd() {
        System.out.println("cd 설정");
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
