package design_pattern.chap08_template_method.src;

import java.applet.Applet;
import java.awt.*;

public class MyApplet extends Applet {

    String message;

    public void init() {
        message = "안녕하세요. 제가 왔어요...";
        repaint();
    }

    public void start() {
        message = "시장 중...";
        repaint();
    }

    public void stop() {
        message = "저를 떠나려 하시는군요...";
        repaint();
    }

    public void destroy() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawString(message, 5, 15);
    }

    public static void main(String[] args) {
        MyApplet applet = new MyApplet();

    }
}
