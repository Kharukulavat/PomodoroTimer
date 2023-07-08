package Reference;
import javax.swing.*;
import java.awt.*;
public class JSpinnerExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JSpinner Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new FlowLayout());

        // Create a JSpinner with an initial value and a range
        SpinnerModel spinnerModel = new SpinnerNumberModel(25, 5, 60, 5);
        JSpinner spinner = new JSpinner(spinnerModel);

        // Add the JSpinner to the JFrame
        frame.add(spinner);

        // Add a JButton to retrieve the value from the JSpinner
        JButton button = new JButton("Get Value");
        button.addActionListener(e -> {
            int value = (Integer) spinner.getValue();
            System.out.println("Selected value: " + value);
        });
        frame.add(button);

        frame.setVisible(true);

    }
}
