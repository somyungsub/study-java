package design_pattern.chap07_adapter_facade.src;

public class DvdPlayer {
    public void on() {
        System.out.println("Dvd on!!");
    }

    public void play(String movie) {
        System.out.println("Dvd play.." + movie);
    }

    public void stop() {
        System.out.println("Dvd stop");
    }

    public void off() {
        System.out.println("Dvd off");
    }
}
