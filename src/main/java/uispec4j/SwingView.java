package uispec4j;

import org.uispec4j.Window;
import org.uispec4j.finder.ComponentMatcher;

import javax.swing.*;
import java.awt.*;

public abstract class SwingView {

    protected static final Long WAIT_ON_VIEW_TIMEOUT = 20000L;
    protected String componentTree;
    protected SwingGui swingGui;
    protected Window window;

    /**
     * Sets the swingGui and window variable.
     *
     * @param parentSwingGui swingGui that the view is a child of.
     */
    public SwingView(SwingGui parentSwingGui) {
        swingGui = parentSwingGui;
        window = swingGui.waitOnWindowOpen(getWindowMatcher(), WAIT_ON_VIEW_TIMEOUT);
    }

    /**
     * Returns the WindowMatcher used to lookup the window
     *
     * @return the window matcher
     */
    protected abstract WindowMatcher getWindowMatcher();

    /**
     * Indicates that the view is visible
     *
     * @return boolean
     */
    public boolean isVisible() {
        if (isOpen()) {
            return window.isVisible().isTrue();
        } else {
            return false;
        }
    }

    /**
     * Indicates that the window is instantiated, it could not be visible yet though
     *
     * @return boolean
     */
    public Boolean isOpen() {
        return window != null;
    }

    /**
     * Returns the window object that this view represents
     *
     * @return the window
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Returns when the window is closed
     */
    public void waitOnClose() {
        swingGui.waitOnWindowClose(getWindowMatcher(), WAIT_ON_VIEW_TIMEOUT);
    }

    /**
     * @param milliSeconds the milli seconds to sleep.
     */
    public void sleep(Long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {

        } finally {

        }
    }

    public <T extends JComponent> T findComponent(final Class<T> clazz, final MatchTypes matchType,final String matchValue) {
        return (T) window.findSwingComponent(getMatcher(clazz, matchType, matchValue));
    }

    public <T extends JComponent> T findComponent(final Class<T> clazz) {
        return (T) window.findSwingComponent(clazz);
    }

    public <T extends JComponent> ComponentMatcher getMatcher(final Class clazz, final MatchTypes matchType, final String matchValue) {
        return new ComponentMatcher() {
            @Override
            public boolean matches(Component component) {
                if (clazz.isInstance(component)) {
                    if (matchType == MatchTypes.NAME) {
                        return component.getName().equals(matchValue);
                    } else{
                        return ((AbstractButton) component).getText().equals(matchValue);
                    }
                }
                return false;
            }
        };
    }

    public enum MatchTypes{
        TEXT,
        TITLE,
        NAME
    }
}