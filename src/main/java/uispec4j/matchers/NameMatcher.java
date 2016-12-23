package uispec4j.matchers;

//import nl.ns.infoplus.automatedtesting.uispec4j.WindowMatcher;
import org.uispec4j.Window;
import uispec4j.WindowMatcher;

/**
 * Swing Gui Window Matcher class.
 */
public class NameMatcher implements WindowMatcher {

    private String name;

    /**
     * Sets the name to match.
     *
     * @param name the name to match
     */
    public NameMatcher(String name) {
        this.name = name;
    }

    /**
     * Verifies if a window name equals {@link #name}.
     *
     * @param window the window.
     * @return boolean
     */
    @Override
    public boolean matches(Window window) {
        if (window.getName() != null) {
            return window.getName().equals(name);
        }
        return false;
    }
}