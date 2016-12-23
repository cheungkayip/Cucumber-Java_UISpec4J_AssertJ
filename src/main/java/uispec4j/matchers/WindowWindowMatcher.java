package uispec4j.matchers;

//import nl.ns.infoplus.automatedtesting.uispec4j.WindowMatcher;
import org.uispec4j.Window;
import uispec4j.WindowMatcher;

/**
 * Swing Gui Window Matcher class.
 */
public class WindowWindowMatcher implements WindowMatcher {

    private Window window;

    /**
     * Sets the window to match.
     *
     * @param window the window to match
     */
    public WindowWindowMatcher(Window window) {
        this.window = window;
    }

    /**
     * Verifies if a window equals {@link #window}.
     *
     * @param window the window.
     * @return boolean
     */
    @Override
    public boolean matches(Window window) {
        return this.window.getName().equals(window.getName());
    }
}