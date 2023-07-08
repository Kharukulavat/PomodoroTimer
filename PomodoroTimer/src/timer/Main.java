package timer;
import control.DateAndTime;
import control.PomodoroControl;
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        ImageIcon PomodoroIcon = new ImageIcon("PomodoroTimerIcon.png");
        JFrame frame = new JFrame("PomodoroTimer");
        frame.setIconImage(PomodoroIcon.getImage());
        frame.setLayout(null);

        DateAndTime dateAndTime = new DateAndTime();
        dateAndTime.setBounds(0, 0, 200, 400);
        frame.add(dateAndTime);

        Pomodoro pomodoro = new Pomodoro();
        pomodoro.setBounds(200, 0, 300, 400); // Add this line to set the bounds of the Pomodoro panel
        frame.add(pomodoro);
        // Set the DateAndTime panel in the Pomodoro instance
//        pomodoro.setDateAndTimePanel(dateAndTime); //unnecessary

        PomodoroControl pomodoroControl = new PomodoroControl(pomodoro,dateAndTime);
        pomodoroControl.setBounds(500, 0, 200, 400); // Set the bounds for the PomodoroControl panel
        frame.add(pomodoroControl);

        frame.setSize(700, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
