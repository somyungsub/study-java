package design_pattern.chap07_adapter_facade.src;

public class Amplifier {
    DvdPlayer dvdPlayer;
    private int volume;

    public void on() {
        System.out.println("Amp on!!");
    }

    public void setDvd(DvdPlayer dvdPlayer) {
        this.dvdPlayer = dvdPlayer;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void off() {
        System.out.println("Amp off..");
    }
}
