package design_pattern.chap02_observer.src;

import java.util.Observable;
import java.util.Observer;

public class ForcecasDisplay implements Observer, DisplayElemet {
    private float currentPressure =  29.92f;
    private float lastPressure;

    public ForcecasDisplay(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
//        if (o instanceof WeatherData) {
//            WeatherData weatherData = (WeatherData) o;
//            lastPressure = currentPressure;
//            currentPressure = weatherData.getPressure();
//            display();
//        }
    }

    public void display() {
        // 출력코드
    }
}

