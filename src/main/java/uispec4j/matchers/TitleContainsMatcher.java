package uispec4j.matchers;

//import nl.ns.infoplus.automatedtesting.uispec4j.WindowMatcher;
import org.uispec4j.Window;
import uispec4j.WindowMatcher;


/**
 * Swing Gui Window Matcher class.
 */
public class TitleContainsMatcher implements WindowMatcher {

    private String titlepart;

    /**
     * Sets the title/part of title to match.
     *
     * @param titlepart the title/part of title to match
     */
    public TitleContainsMatcher(String titlepart) {
        this.titlepart = titlepart;
    }

    /**
     * Verifies if a window title contains {@link #titlepart}.
     *
     * @param window the window.
     * @return boolean
     */
    @Override
    public boolean matches(Window window) {
        if (window.getTitle() != null) {
            return window.getTitle().contains(titlepart);
        }
        return false;
    }
}
