package uispec4j;

import org.uispec4j.Window;

public interface GuiListener {

    void shown(Window window);

    void hidden(Window window);
}
