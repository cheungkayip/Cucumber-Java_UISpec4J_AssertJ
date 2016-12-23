package uispec4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uispec4j.ItemNotFoundException;
import org.uispec4j.Window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Gui {

    private static final Logger LOG = LoggerFactory.getLogger(Gui.class);
    private Map<String, Window> windows;
    private List<GuiListener> listeners;

    /**
     * Sets the fields {@link #windows} and {@link #listeners}.
     */
    public Gui() {
        windows = new HashMap<String, Window>();
        listeners = new CopyOnWriteArrayList<GuiListener>();
    }

    /**
     * Launch the swing gui application.
     */
    public abstract void launchApplication();

    /**
     * Stop the swing gui application.
     */
    public abstract void shutdownApplication();

    /**
     * Print the gui components of a swing gui window.
     *
     * @param window the window to print the components from
     * @return all the components in the specified swing gui window
     */
    protected abstract String printComponentTree(Window window);

    /**
     * Add a listener to the swing gui application.
     *
     * @param listener the listener.
     */
    public void addGuiListener(GuiListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    /**
     * Remove a listener from the swing gui.
     *
     * @param listener the listener.
     * @return boolean
     */
    public boolean removeGuiListener(GuiListener listener) {
        return listeners.remove(listener);
    }

    /**
     * Find a swing gui window by name.
     *
     * @param name the name of the swing gui window to find.
     * @return the window
     */
    public Window findWindow(String name) {
        return windows.get(name);
    }

    /**
     * Find a swing gui window by {@link WindowMatcher}
     *
     * @param windowMatcher the window matcher.
     * @return the window
     * @throws ItemNotFoundException
     */
    public Window findWindow(WindowMatcher windowMatcher) {
        for (Window window : windows.values()) {
            if (windowMatcher.matches(window)) {
                return window;
            }
        }
        throw new ItemNotFoundException("No window matched using matcher.");
    }

    /**
     * Check if a window is open.
     *
     * @param name the name of the window.
     * @return boolean
     */
    public boolean isWindowOpen(String name) {
        return windows.containsKey(name);
    }

    /**
     * Check if a window is open.
     *
     * @param windowMatcher the window matcher.
     * @return boolean
     */
    public boolean isWindowOpen(WindowMatcher windowMatcher) {
        for (Window window : windows.values()) {
            try {
                if (windowMatcher.matches(window)) {
                    return true;
                }
            } catch (NullPointerException e) {
            }
        }
        return false;
    }

    /**
     * Method to notify a listener.
     *
     * @param window the window to notify.
     * @param shown  true -> used to register a window.
     *               false -> used to unregister a window.
     */
    private void notifyListeners(Window window, boolean shown) {
        for (GuiListener listener : listeners) {
            if (shown) {
                listener.shown(window);
            } else {
                listener.hidden(window);
            }
        }
    }

    /**
     * The window to register.
     *
     * @param window the window.
     */
    protected void registerWindow(Window window) {
        if (window != null && !windows.containsKey(window.getName())) {
            windows.put(window.getName(), window);
            notifyListeners(window, true);
            LOG.debug(String.format("New window '%1$s' opened: %2$s", window.getTitle(), printComponentTree(window)));
        }
    }

    /**
     * The window to unregister.
     *
     * @param window the window.
     */
    protected void unregisterWindow(Window window) {
        if (window != null && windows.containsKey(window.getName())) {
            windows.remove(window.getName());
            notifyListeners(window, false);
            LOG.debug(String.format("Window '%1$s' closed: %2$s", window.getTitle(), printComponentTree(window)));
        }
    }
}