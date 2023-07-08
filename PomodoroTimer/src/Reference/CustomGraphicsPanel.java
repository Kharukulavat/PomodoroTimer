package Reference;

import javax.swing.*;
import java.awt.*;

public class CustomGraphicsPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw a filled rectangle
        g.setColor(Color.BLUE);
        g.fillRect(20, 20, 100, 50);

        // Draw a circle
        g.setColor(Color.RED);
        g.fillOval(150, 20, 50, 50);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Graphics Panel");
        CustomGraphicsPanel customGraphicsPanel = new CustomGraphicsPanel();
        frame.add(customGraphicsPanel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

