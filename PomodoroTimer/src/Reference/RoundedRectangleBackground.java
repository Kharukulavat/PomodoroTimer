package Reference;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RoundedRectangleBackground extends JPanel {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
    private static final int ARC_WIDTH = 20;
    private static final int ARC_HEIGHT = 20;
    private static final Color BACKGROUND_COLOR = Color.PINK;

    public RoundedRectangleBackground() {
        // Set the preferred size of the panel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the rounded rectangle background
        g.setColor(BACKGROUND_COLOR);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
    }

    public static void main(String[] args) {
        // Create a new JFrame and add the RoundedRectangleBackground panel
        JFrame frame = new JFrame("Rounded Rectangle Background");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RoundedRectangleBackground roundedRect = new RoundedRectangleBackground();
        frame.add(roundedRect);
        frame.pack();
        frame.setVisible(true);
    }
}
