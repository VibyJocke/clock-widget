import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Panel extends JPanel {

    private static final Color COLOR_TRANSPARENT = new Color(0, 0, 0, 0);
    private static final Color COLOR_DARK = new Color(224, 107, 5);
    private static final Color COLOR_MEDIUM = new Color(222, 173, 40);
    private static final Color COLOR_LIGHT = new Color(222, 219, 40);

    private final int radius;
    private final int hourHandRadius;
    private final int minuteHandRadius;
    private final int secondHandRadius;

    public Panel(int radius) {
        this.radius = radius;
        hourHandRadius = (int) (radius * 0.5);
        minuteHandRadius = (int) (radius * 0.7);
        secondHandRadius = (int) (radius * 0.85);

        setBackground(COLOR_TRANSPARENT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        enableAntiAliasing((Graphics2D) g);

        var now = LocalTime.now();

        drawWatchface(g);
        drawHands(g, now);
        drawTimeText(g, now);
    }

    private void enableAntiAliasing(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    private void drawWatchface(Graphics g) {
        g.setColor(COLOR_MEDIUM);
        g.fillOval(0, 0, radius * 2, radius * 2);
        g.setColor(COLOR_DARK);
        g.drawOval(0, 0, radius * 2, radius * 2);
    }

    private void drawHands(Graphics g, LocalTime now) {
        double hourHandDegrees = (Math.PI * 2) / 12 * (now.getHour() - 15);
        double minuteHandDegrees = (Math.PI * 2) / 60 * (now.getMinute() - 15);
        double secondHandDegrees = (Math.PI * 2) / 60 * (now.getSecond() - 15);

        drawHand(g, hourHandRadius, hourHandDegrees);
        drawHand(g, minuteHandRadius, minuteHandDegrees);
        g.setColor(COLOR_LIGHT);
        drawHand(g, secondHandRadius, secondHandDegrees);
    }

    private void drawHand(Graphics g, double handRadius, double handDegrees) {
        g.drawLine(
                radius,
                radius,
                (int) (handRadius * Math.cos(handDegrees)) + radius,
                (int) (handRadius * Math.sin(handDegrees)) + radius
        );
    }

    private void drawTimeText(Graphics g, LocalTime now) {
        g.setFont(new Font("Default", Font.PLAIN, 14));
        g.setColor(Color.BLACK);
        g.drawString(now.truncatedTo(ChronoUnit.SECONDS).toString(), radius-24, radius * 2 + 16);
        g.setColor(COLOR_DARK);
        g.drawString(now.truncatedTo(ChronoUnit.SECONDS).toString(), radius-25, radius * 2 + 15);
    }
}
