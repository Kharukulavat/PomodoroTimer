package Reference;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Stopwatch implements ActionListener {
    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JLabel timeLabel = new JLabel();
    int elapsedTime = 0;
    int seconds =0;
    int minutes =0;
    int hours =0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);
    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) { //what our timer is going to every 1000 milliseconds (1 second)
            elapsedTime = elapsedTime + 1000;
            hours = (elapsedTime/3600000); //divided by numebr of milliseconds in 1 hour
            minutes = (elapsedTime/60000) % 60; //we don't want it to be 61 62 minutes,
            // and when it's 59 -> 60 -> 61 the hours will already change
            seconds = (elapsedTime/1000) % 60; // %60 because we don't want it to display anything more than 60
            seconds_string = String.format("%02d", seconds); //We're gonna update this string
            minutes_string = String.format("%02d",minutes);
            hours_string = String.format("%02d",hours);
            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        }
    });
    public Stopwatch() {
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN,35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Ink Free", Font.PLAIN,20));
        startButton.setFocusable(false); //because things that is focusable are annoying
        startButton.addActionListener(this);

        resetButton.setBounds(200,200,100,50);
        resetButton.setFont(new Font("Ink Free", Font.PLAIN,20));
        resetButton.setFocusable(false); //because things that is focusable are annoying
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true); //make sure we put this at the end of the constructor because
        // if we add components after setVisible() sometimes it doesn't work
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (started == false) {
                started = true;
                startButton.setText("STOP");
                start();
            }
            else {
                started = false;
                startButton.setText("START");
                stop();
            }
        }
        if (e.getSource() == resetButton) {
            started = false;
            startButton.setText("START");
            reset();
        }
    }
    void start() {
        timer.start();
    }
    void stop() {
        timer.stop();
    }
    void reset() { //set everything back to zero and update timeLabel string
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        seconds_string = String.format("%02d", seconds); //We're gonna update this string
        minutes_string = String.format("%02d",minutes);
        hours_string = String.format("%02d",hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }


    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
    }
}
