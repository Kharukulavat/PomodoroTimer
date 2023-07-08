package Reference;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class SliderExample extends JFrame implements ChangeListener {
    private JSlider slider;
    private JLabel valueLabel;

    public SliderExample() {
        // Set up the JFrame
        setTitle("JSlider Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create a JSlider with a range from 0 to 100 and an initial value of 50
        slider = new JSlider(0, 100, 50);
        slider.setMajorTickSpacing(10); // Set the major tick spacing to 10
        slider.setMinorTickSpacing(1);  // Set the minor tick spacing to 1
        slider.setPaintTicks(true);     // Show tick marks on the slider
        slider.setPaintLabels(true);    // Show numeric labels on the slider
        slider.addChangeListener(this); // Add a ChangeListener to the slider

        // Create a JLabel to display the current value of the slider
        valueLabel = new JLabel("Value: " + slider.getValue());

        // Add the slider and the label to the JFrame
        add(slider);
        add(valueLabel);

        // Display the JFrame
        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // Update the valueLabel text when the slider value changes
        valueLabel.setText("Value: " + slider.getValue());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SliderExample());
    }
}
