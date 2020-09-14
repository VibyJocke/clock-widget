import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class WindowListener extends MouseInputAdapter {
    private Point location;
    private MouseEvent event;

    public void mousePressed(MouseEvent event) {
        this.event = event;
        if (this.event.getButton() != 1) {
            System.exit(0);
        }
    }

    public void mouseDragged(MouseEvent event) {
        Component component = event.getComponent();
        location = component.getLocation(location);
        int x = location.x - this.event.getX() + event.getX();
        int y = location.y - this.event.getY() + event.getY();
        component.setLocation(x, y);
    }
}