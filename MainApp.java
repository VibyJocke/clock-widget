import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainApp extends JFrame {

    private static final int RADIUS = 50;

    public static void main(String[] args) {
        new MainApp();
    }

    public MainApp() {
        initGui();
        setContentPane(new Panel(RADIUS));
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(this::repaint, 0, 1000 / 60, TimeUnit.MILLISECONDS);
        pack();
    }

    private void initGui() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        autoSetFrame(RADIUS * 3, RADIUS * 3);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        WindowListener drag = new WindowListener();
        addMouseListener(drag);
        addMouseMotionListener(drag);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void autoSetFrame(int x, int y) {
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(x, y));
        setLocation((dim.width - x) / 2, (dim.height - y) / 2);
    }
}
