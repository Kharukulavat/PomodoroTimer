package control;

import timer.Pomodoro;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class PomodoroControl extends JPanel implements ChangeListener, ActionListener{
    private Pomodoro pomodoro; //to create instances of Pomodoro in order to access its properties/methods
    private JPanel controlPanel = new JPanel();

    // Create a JSpinner with an initial value and a range
    private SpinnerModel pomodoroModel = new SpinnerNumberModel(25, 5, 60, 5);
    private SpinnerModel shortbreakModel = new SpinnerNumberModel(5,1,30,5);
    private SpinnerModel longbreakModel = new SpinnerNumberModel(15,1,45,5);
    private SpinnerModel roundModel = new SpinnerNumberModel(4,2,15,1);
    //implitcitly cast SpinnerModel Object to create JSpinner
    private JSpinner pomodoro_duration = new JSpinner(pomodoroModel);
    private JSpinner shortbreak_duration = new JSpinner(shortbreakModel);
    private JSpinner longbreak_duration = new JSpinner(longbreakModel);
    private JSpinner round_duration = new JSpinner(roundModel);
    private JLabel pomodoro_lb = new JLabel("Pomodoro:");
    private JLabel shortbreak_lb = new JLabel("Short break:");
    private JLabel longbreak_lb = new JLabel("Long break:");
    private JLabel round_lb = new JLabel("Rounds:");
    public int updated_pomodoro_minutes;
    public int updated_shortbreak_minutes;
    public int updated_longbreak_minutes;
    public int updated_totalround;
    private DateAndTime dateAndTime;
    private JButton changecolor_button = new JButton("Change Color");

    public PomodoroControl(Pomodoro pomodoro, DateAndTime dateAndTime) {
        this.pomodoro = pomodoro;
        this.dateAndTime = dateAndTime;
        this.setLayout(null);
        this.setBounds(500, 0, 200, 400);// Set the bounds for the PomodoroControl panel
        //unnecessary, if we already set the bound of this panel in the main class
        controlPanel.setLayout(new GridLayout(4,2));
        controlPanel.add(pomodoro_lb);
        controlPanel.add(pomodoro_duration);
        controlPanel.add(shortbreak_lb);
        controlPanel.add(shortbreak_duration);
        controlPanel.add(longbreak_lb);
        controlPanel.add(longbreak_duration);
        controlPanel.add(round_lb);
        controlPanel.add(round_duration);

        controlPanel.setBounds(15, 265, 175, 100);
        controlPanel.setOpaque(false);
        this.add(controlPanel);

        // Add the changecolor_button to the top-right corner
        // Add the JColorChooser button to the top-right corner
        changecolor_button.setBounds(45, 10, 120, 25);
        this.add(changecolor_button);

        // Add an ActionListener to the colorChooserButton
        changecolor_button.addActionListener(this);

        pomodoro_duration.addChangeListener(this);
        shortbreak_duration.addChangeListener(this);
        longbreak_duration.addChangeListener(this);
        round_duration.addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        //Explicit casting ; we need to cast it to int because it's an object but required integer
        updated_pomodoro_minutes = (Integer) pomodoro_duration.getValue();
        updated_shortbreak_minutes = (Integer) shortbreak_duration.getValue();
        updated_longbreak_minutes = (Integer) longbreak_duration.getValue();
        updated_totalround = (Integer) round_duration.getValue();

        pomodoro.updateTimers(updated_pomodoro_minutes, updated_shortbreak_minutes, updated_longbreak_minutes, updated_totalround);
        pomodoro.updateToInitialValues();
        pomodoro.reset();
        pomodoro.stopTimer(); //to stop the timer when we change the JSpinner's value

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color selectedColor = JColorChooser.showDialog(null, "Choose Color", Color.WHITE);

        if (selectedColor != null) {
            // Set the background color of all panels
            pomodoro.changeBackgroundColor(selectedColor);
            dateAndTime.changeBackgroundColor(selectedColor); // Set the background color of the DateAndTime panel
            setBackground(selectedColor); //to change the background color of the PomodoroControl panel
        }
    }
}
