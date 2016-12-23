package uispec4j.matchers;

//import nl.ns.infoplus.automatedtesting.uispec4j.WindowMatcher;
import org.uispec4j.Window;
import uispec4j.WindowMatcher;

/**
 * Swing Gui Window Matcher class.
 */
public class ClassMatcher implements WindowMatcher {

    private Class clazz;

    /**
     * Sets the class to match.
     *
     * @param clazz the class to match
     */
    public ClassMatcher(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * Verifies if a window class name equals {@link #clazz}.
     *
     * @param window the window.
     * @return boolean
     */
    @Override
    public boolean matches(Window window) {
        return window.getAwtComponent().getClass().getName().equals(clazz.getName());
    }
}