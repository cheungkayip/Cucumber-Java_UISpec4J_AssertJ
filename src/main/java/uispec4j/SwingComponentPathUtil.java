package uispec4j;

import java.awt.*;

public class SwingComponentPathUtil {

    public static String getPath(Component component) {
        return buildPath(component, new StringBuilder()).toString();
    }

    private static StringBuilder buildPath(Component component, StringBuilder b ) {
        if( component != null ) {
            buildPath(component.getParent(), b);
            b.append("/"+component.getClass().getSimpleName() +  getIndex(component.getParent(),component));
        }
        return b;
    }

    private static String getIndex(Container container, Component component) {
        if( container == null ) {
            return "";
        }

        int idx = 0;
        int total = 0;
        if( container.getComponents().length > 1 ) {
            for( Component comp : container.getComponents()) {
                if( comp.getClass().equals(component.getClass())) {
                    total++;
                    if (comp.equals(component)) {
                        idx = total;
                    }
                }
            }
        }

        if( total > 1 ){
            return "[" + idx + "]";
        } else {
            return "";
        }
    }
}
