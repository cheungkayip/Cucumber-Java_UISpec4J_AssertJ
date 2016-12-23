package uispec4j.matchers;

//import nl.ns.infoplus.automatedtesting.uispec4j.SwingComponentPathUtil;
import org.uispec4j.finder.ComponentMatcher;
import uispec4j.SwingComponentPathUtil;

import java.awt.*;

/**
 * Swing Gui Component Matcher class.
 */
public class PathComponentMatcher implements ComponentMatcher {

    private String path;

    /**
     * Sets the component path to match.
     *
     * @param path the path to match
     */
    public PathComponentMatcher(String path) {
        this.path = path;
    }

    /**
     * Verifies if a component path equals {@link #path}.
     *
     * @param component the component.
     * @return boolean
     */
    @Override
    public boolean matches(Component component) {
        return path.equals(SwingComponentPathUtil.getPath(component));
    }
}