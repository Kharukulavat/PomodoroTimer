package Reference;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class JColorChooserExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JColorChooser Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.CENTER);

        // Add a JButton to show the JColorChooser dialog
        JButton button = new JButton("Choose Color");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the JColorChooser dialog and get the selected color
                Color selectedColor = JColorChooser.showDialog(frame, "Choose a color", panel.getBackground());

                // Set the selected color as the background of the panel
                if (selectedColor != null) {
                    panel.setBackground(selectedColor);
                }
            }
        });
        frame.add(button, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
