package Reference;
import javax.swing.*;

public class JSpinnerwtext extends JFrame{
    public JSpinnerwtext() {
        // Create a panel to hold the label and spinner
        JPanel panel = new JPanel();
        JLabel label = new JLabel("My spinner:");
        JSpinner spinner = new JSpinner();
        panel.add(label);
        panel.add(spinner);

        // Add the panel to the frame
        getContentPane().add(panel);

        // Set the frame properties
        setTitle("My Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new JSpinnerwtext();
    }

}
