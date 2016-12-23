package uispec4j;

import org.parboiled.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uispec4j.Button;
import org.uispec4j.ItemNotFoundException;
import org.uispec4j.Trigger;
import org.uispec4j.finder.ComponentMatchers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class SwingGui extends Gui {
    private static final Logger LOG = LoggerFactory.getLogger(SwingGui.class);

    public org.uispec4j.Window waitOnWindowOpen(final WindowMatcher windowMatcher, long timeout) {
        LOG.debug("Waiting for swing window open: " + windowMatcher.toString());
        try {
            return findWindow(windowMatcher);
        } catch (ItemNotFoundException infe) {
            // ignored, lets wait for it.
        }

        final CountDownLatch latch = new CountDownLatch(1);
        CountDownWindowListener cdwl = new CountDownWindowListener(windowMatcher, latch, CountDownWindowListener.Event.OPEN);
        addGuiListener(cdwl);
        try {
            latch.await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ie) {
            // ignored.
        } finally {
            removeGuiListener(cdwl);
        }
        return cdwl.getWindow();
    }

    public void waitOnWindowClose(final WindowMatcher windowMatcher, long timeout) {
        LOG.debug("Waiting for swing window to close: " + windowMatcher.toString());
        if (isWindowOpen(windowMatcher)) {
            final CountDownLatch latch = new CountDownLatch(1);
            CountDownWindowListener cdwl = new CountDownWindowListener(windowMatcher, latch, CountDownWindowListener.Event.CLOSE);
            addGuiListener(cdwl);
            try {
                latch.await(timeout, TimeUnit.MILLISECONDS);
            } catch (InterruptedException ie) {
                // ignored.
            } finally {
                removeGuiListener(cdwl);
            }
        }
    }

    public void runLater(final Trigger trigger) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    trigger.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void clickLater(final org.uispec4j.MenuItem menuItem) {
        runLater(menuItem.triggerClick());
    }

    public void clickLater(final Button button) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.out.println("Clicking btn " + button.getAwtComponent());
                button.click();
            }
        });
    }

    public void clickLaterButtonWithText(org.uispec4j.Window window, final String label) {
        org.uispec4j.Button btn = window.getButton(ComponentMatchers.displayedNameIdentity(label));
        if (btn != null) {
            clickLater(btn);
        }
    }

    protected String printComponentTree(org.uispec4j.Window window) {
        String componentTree = buildComponentTreeString(new StringBuilder(), window.getAwtComponent(), "").toString();
        LOG.debug("Swing ComponentTree: " + componentTree);
        return componentTree;
    }

    protected void interceptWindows() {
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTWindowInterceptor(new WindowMatcher() {

            @Override
            public boolean matches(org.uispec4j.Window window) {
                return true;
            }
        }, new AWTWindowHandler() {

            @Override
            public void shown(final org.uispec4j.Window window) {
                registerWindow(window);
            }

            @Override
            public void hidden(org.uispec4j.Window window) {
                unregisterWindow(window);
            }
        }), AWTWindowInterceptor.EVENT_MASK);
    }

    private boolean isWindow(Component component) {
        if (JDialog.class.isInstance(component)
                || JFrame.class.isInstance(component)
                || JInternalFrame.class.isInstance(component)
                || Frame.class.isInstance(component)
                || Window.class.isInstance(component)) {
            return true;
        }
        return false;
    }

    private org.uispec4j.Window asWindow(Component component) {
        if (isWindow(component)) {
            if (JDialog.class.isInstance(component)) {
                return new org.uispec4j.Window((JDialog) component);
            } else if (JFrame.class.isInstance(component)) {
                return new org.uispec4j.Window((JFrame) component);
            } else if (JInternalFrame.class.isInstance(component)) {
                return new org.uispec4j.Window((JInternalFrame) component);
            } else if (Frame.class.isInstance(component)) {
                return new org.uispec4j.Window((Frame) component);
            } else if (Window.class.isInstance(component)) {
                return new org.uispec4j.Window((Window) component);
            }
        }
        return null;
    }

    private StringBuilder buildComponentTreeString(StringBuilder builder, Component component, String ident) {
        builder.append(ident + createComponentString(component) + "\n");
        if (Container.class.isInstance(component)) {
            for (Component child : ((Container) component).getComponents()) {
                buildComponentTreeString(builder, child, ident + "  ");
            }
        }
        return builder;
    }

    private String createComponentString(Component component) {
        String[] methods = {"getName", "getText", "getTitle"};
        java.util.List<String> values = new ArrayList<String>();

        for (int i = 0; i < methods.length; i++) {
            String value = callMethod(component, methods[i]);
            if (value != null) {
                values.add(value);
            }
        }
        values.add("path=\"" + SwingComponentPathUtil.getPath(component) + "\"");

        StringBuilder b = new StringBuilder(component.getClass().getName());
        b.append("[");
        for (int i = 0; i < values.size(); i++) {
            b.append(values.get(i));
            if (i + 1 < values.size()) {
                b.append(", ");
            }
        }
        b.append("]");
        return b.toString();
    }

    private String callMethod(Component comp, String name) {
        Class clazz = comp.getClass();
        try {
            Method m = clazz.getMethod(name, null);
            if (m != null) {
                Object obj = m.invoke(comp, null);
                if (obj != null) {
                    String value = obj.toString();
                    if (StringUtils.isNotEmpty(value)) {
                        return name.toLowerCase().substring(3) + "=\"" + obj.toString() + "\"";
                    }
                }
            }
        } catch (Exception e) {
            // ignored
        }
        return null;
    }

    interface AWTWindowHandler {

        void shown(org.uispec4j.Window window);

        void hidden(org.uispec4j.Window window);
    }

    class AWTWindowInterceptor implements AWTEventListener {

        public static final long EVENT_MASK = AWTEvent.WINDOW_EVENT_MASK;
        private WindowMatcher matcher;
        private AWTWindowHandler windowHandler;

        public AWTWindowInterceptor(WindowMatcher matcher, AWTWindowHandler windowHandler) {
            this.matcher = matcher;
            this.windowHandler = windowHandler;
        }

        @Override
        public void eventDispatched(AWTEvent event) {
            if (WindowEvent.class.isInstance(event)) {
                Component component = ((WindowEvent) event).getComponent();
                org.uispec4j.Window window = asWindow(component);
                LOG.debug(String.format("Swing window '%1$s' fired event '%2$s'", ((WindowEvent) event).getWindow().toString(), event.getID()));

                if (window != null && matcher.matches(window)) {
                    if (WindowEvent.WINDOW_OPENED == event.getID()) {
                        windowHandler.shown(window);
                    } else if (WindowEvent.WINDOW_ACTIVATED == event.getID()) {
                        windowHandler.shown(window);
                    } else if (WindowEvent.WINDOW_CLOSED == event.getID()) {
                        windowHandler.hidden(window);
                    }
                }
            }
        }
    }
}
