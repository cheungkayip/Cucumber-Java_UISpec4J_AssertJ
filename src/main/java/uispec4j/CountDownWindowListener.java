package uispec4j;

import org.uispec4j.Window;

import java.util.concurrent.CountDownLatch;

public class CountDownWindowListener  implements GuiListener {

    public enum Event {
        OPEN,CLOSE
    }

    private Window window;
    private WindowMatcher matcher;
    private CountDownLatch latch;
    private Event event;

    public CountDownWindowListener(WindowMatcher matcher, CountDownLatch latch, Event event) {
        this.event = event;
        this.matcher = matcher;
        this.latch = latch;
    }


    @Override
    public void shown(Window window) {
        if( event == Event.OPEN && matcher.matches(window)) {
            this.window = window;
            latch.countDown();
        }
    }

    @Override
    public void hidden(Window window) {
        if( event == Event.CLOSE && matcher.matches(window)) {
            this.window = window;
            latch.countDown();
        }
    }

    public Window getWindow() {
        return window;
    }
}
