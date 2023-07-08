package timer;
import control.DateAndTime;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Pomodoro extends JPanel implements ActionListener,KeyListener{
    private JPanel buttons_panel = new JPanel();
    private JLabel statusLB = new JLabel("FOCUS"); //display status in work session
    private JLabel sessionLB; //display the session
    private JLabel progress_label = new JLabel("Start working today!");
    public JLabel timeDisplay = new JLabel("00:00");
    private JProgressBar progressBar = new JProgressBar(0,100);
    private JButton reset_button = new JButton("Reset");
    private JButton start_button = new JButton("Start");
    private JButton skip_button = new JButton("Skip");

    public Timer timer = new Timer(5,this);
    public int pomodoro_minutes = 25;
    public int pomodoro_seconds = 0;
    public int shortbreak_minutes = 5;
    public int shortbreak_seconds = 0;
    public int longbreak_minutes = 15;
    public int longbreak_seconds = 0;
    public static int currentRounds = 1;
    public int totalRound = 4;
    private boolean isWorkStarted = false;
    private boolean isShortbreakStarted = false;
    private boolean isLongbreakStarted = false;
    private boolean isWorkFinished = false;
    private boolean isShortbreakFinished = false;
    private boolean isLongbreakFinished = false;
    //to keep track of the timer pause state
    boolean isTimerPaused = false;
    private double progress = 0;

    // Add these variables to store the updated values
    public int updated_pomodoro_minutes = pomodoro_minutes;
    public int updated_shortbreak_minutes = shortbreak_minutes;
    public int updated_longbreak_minutes = longbreak_minutes;
    public int updated_totalround = totalRound;
    private DateAndTime dateAndTimePanel;

    public Pomodoro() {
        this.setLayout(null);
        this.setBounds(200, 0, 500, 400);
//        this.setBackground(Color.LIGHT_GRAY);

        statusLB.setFont(new Font("Serif",Font.BOLD,18));
        statusLB.setHorizontalAlignment(SwingConstants.CENTER); // Add this line to align statusLB in the center
        statusLB.setBounds(75,15,150,30);

        String sessionformat = String.format("Session %d of %d",currentRounds,totalRound);
        sessionLB = new JLabel(sessionformat);
        sessionLB.setFont(new Font("Serif",Font.BOLD,16));
        sessionLB.setBounds(105,45,100,20);

        TimerPanel PomodoroGraphics = new TimerPanel();
        PomodoroGraphics.setBounds(0,0,300,270);
        PomodoroGraphics.setOpaque(false); // Add this line to make the TimerPanel transparent

        timeDisplay.setFont(new Font("Serif", Font.BOLD, 32));
        timeDisplay.setBounds(114, 143, 100, 50);
        timeDisplay.setForeground(Color.BLACK);

        buttons_panel.setLayout(new FlowLayout());
        buttons_panel.add(reset_button);
        buttons_panel.add(start_button);
        buttons_panel.add(skip_button);
        buttons_panel.setBounds(25,275,250,30);
        buttons_panel.setOpaque(false);

        progressBar.setValue(0);
        progressBar.setBounds(50,310,200,30);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Serif", Font.BOLD,15));

        progress_label.setFont(new Font("Serif",Font.PLAIN,16));
        progress_label.setHorizontalAlignment(SwingConstants.CENTER); // Add this line to align progress_label in the center
        progress_label.setBounds(50,340,200,20);

        SmileyFace smileyGraphics = new SmileyFace();
        smileyGraphics.setBounds(210,315,60,60);
        smileyGraphics.setOpaque(false);

        this.add(statusLB);
        this.add(sessionLB);
        this.add(PomodoroGraphics);
        this.add(buttons_panel);
        this.add(progressBar);
        this.add(progress_label);
        this.add(timeDisplay);
        this.add(smileyGraphics);

        reset_button.addActionListener(this);
        start_button.addActionListener(this);
        skip_button.addActionListener(this);

        timer.setInitialDelay(0); //the timer will execute the actionPerformed() method immediately when started, eliminate the delay
        updateToInitialValues();

        this.addKeyListener(this);
        this.setFocusable(true);
    }
    public void updateTimers(int pomodoro_minutes, int shortbreak_minutes, int longbreak_minutes, int totalRound) {
        // Update the values of the new variables when the method is called
        this.updated_pomodoro_minutes = pomodoro_minutes;
        this.updated_shortbreak_minutes = shortbreak_minutes;
        this.updated_longbreak_minutes = longbreak_minutes;
        this.updated_totalround = totalRound;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start_button) {
            if (!isWorkStarted && !isShortbreakStarted && !isLongbreakStarted) {
                startWorksession();
                isTimerPaused = false;
            } else {
                if (!isTimerPaused) {
                    stopTimer();
                    isTimerPaused = true;
                } else { //when the timer is already used and not paused (we click the button to resume)
                    // Use the appropriate start method depending on the current session type.
                    if (isWorkStarted) {
                        startWorksession();
                    } else if (isShortbreakStarted) {
                        startShortbreak();
                    } else if (isLongbreakStarted) {
                        startLongbreak();
                    }
                    isTimerPaused = false;
                }
                start_button.setText(isTimerPaused ? "Start" : "Stop"); //Ternary operator to setText of the start_button
            }
        } else if (e.getSource() == timer) {
            if (isWorkStarted) {
                elapsedProcess(pomodoro_minutes, pomodoro_seconds, "FOCUS");
            } else if (isShortbreakStarted) {
                elapsedProcess(shortbreak_minutes, shortbreak_seconds, "SHORT BREAK");
            } else if (isLongbreakStarted) {
                elapsedProcess(longbreak_minutes, longbreak_seconds, "LONG BREAK");
            }
        } else if (e.getSource() == reset_button) {
            reset();
        } else if (e.getSource() == skip_button) {
            skipSession();
        }
    }
    public void elapsedProcess(int runningPomodoroMinutes, int runningPomodoroSeconds, String sessionType) {
        int currentMinutes = runningPomodoroMinutes;
        int currentSeconds = runningPomodoroSeconds;

        if (sessionType.equals("FOCUS")) {
            currentMinutes = pomodoro_minutes;
            currentSeconds = pomodoro_seconds;
        } else if (sessionType.equals("SHORT BREAK")) {
            currentMinutes = shortbreak_minutes;
            currentSeconds = shortbreak_seconds;
        } else if (sessionType.equals("LONG BREAK")) {
            currentMinutes = longbreak_minutes;
            currentSeconds = longbreak_seconds;
        }

        if (currentMinutes > 0 || currentSeconds > 0) {
            String time = String.format("%02d:%02d", currentMinutes, currentSeconds);
            timeDisplay.setText(time);
            fillProgressBar();

            if (sessionType.equals("FOCUS")) {
                pomodoro_seconds--;
                updateProgressLabel("Keep going!");
            } else if (sessionType.equals("SHORT BREAK")) {
                shortbreak_seconds--;
                updateProgressLabel("Time for a break!");
            } else if (sessionType.equals("LONG BREAK")) {
                longbreak_seconds--;
                updateProgressLabel("Good work!;)");
            }

            if (currentSeconds <= 0) { //to set the seconds from 00 -> 59
                if (sessionType.equals("FOCUS")) {
                    pomodoro_seconds = 59;
                    pomodoro_minutes--;
                } else if (sessionType.equals("SHORT BREAK")) {
                    shortbreak_seconds = 59;
                    shortbreak_minutes--;
                } else if (sessionType.equals("LONG BREAK")) {
                    longbreak_seconds = 59;
                    longbreak_minutes--;
                }
            }
        } else { //if the elapsedTime is finished (00:00)
            stopTimer();
            if (sessionType.equals("FOCUS")) {
                if (!isTimerPaused && !isLongbreakStarted) { // Add !isLongbreakStarted condition to prevent currentRounds counting when the timer is paused during a long break session
                    currentRounds++;
                }
                if (currentRounds <= updated_totalround) {
                    startShortbreak();
                } else { //when currentRound >= updated_totalround
                    startLongbreak();
                }
            } else if (sessionType.equals("SHORT BREAK")) {
                isShortbreakFinished = true;
                startWorksession();
            } else if (sessionType.equals("LONG BREAK")) {
                isLongbreakFinished = true;
                updateToInitialValues();
                reset();
            }
        }
    }
    public void startWorksession() {
        isShortbreakStarted = false;
        isLongbreakStarted = false;
        isWorkStarted = true;
        if (!isTimerPaused) { //This change will ensure that the session variables are only reset to their initial values when the timer is not paused.
            // When the timer is resumed after pausing, it will continue counting down from the current time.
            pomodoro_minutes = updated_pomodoro_minutes;
            pomodoro_seconds = 0;
        }
        statusLB.setText("FOCUS");
        statusLB.setForeground(Color.BLACK);
        updateSessionLabel();
        startTimer();
    }
    public void startShortbreak() {
        isWorkStarted = false;
        isLongbreakStarted = false;
        isShortbreakStarted = true;
        if (!isTimerPaused) {
            shortbreak_minutes = updated_shortbreak_minutes;
            shortbreak_seconds = 0;
        }
        statusLB.setText("SHORT BREAK");
        statusLB.setForeground(new Color(102,0,153));
        startTimer();
    }
    public void startLongbreak() {
        isWorkStarted = false;
        isShortbreakStarted = false;
        isLongbreakStarted = true;
        if (!isTimerPaused) {
            longbreak_minutes = updated_longbreak_minutes;
            longbreak_seconds = 0;
        }
        statusLB.setText("LONG BREAK");
        statusLB.setForeground(new Color(51,153,255));
        startTimer();
    }
    public void skipSession() {
        isTimerPaused = false;
        stopTimer();
        updateToInitialValues();
        if (isWorkStarted) { // is workStarted == true
            if (currentRounds <= updated_totalround) {
                startShortbreak();
            } else { // when it's the last round (currentRounds == updated_totalround)
                currentRounds = updated_totalround;
                startLongbreak();
            }
        } else if (isShortbreakStarted) {
            currentRounds++;
            startWorksession();
        } else if (isLongbreakStarted) {
            currentRounds++;
            reset();
        }

        // Update the timeDisplay based on the current session
        if (isWorkStarted) {
            timeDisplay.setText(String.format("%02d:%02d", pomodoro_minutes, pomodoro_seconds));
        } else if (isShortbreakStarted) {
            timeDisplay.setText(String.format("%02d:%02d", shortbreak_minutes, shortbreak_seconds));
        } else if (isLongbreakStarted) {
            timeDisplay.setText(String.format("%02d:%02d", longbreak_minutes, longbreak_seconds));
        }
    }
    public void startTimer() {
        start_button.setText("Stop");
        timer.start();
    }
    public void stopTimer() {
        timer.stop();
        isWorkStarted = false;
        isShortbreakStarted = false;
        isLongbreakStarted = false;
        start_button.setText("Start");
    }
    public void reset() {
        stopTimer();
        updateToInitialValues();
        resetProgressBar();
        isWorkStarted = false;
        isShortbreakStarted = false;
        isLongbreakStarted = false;
        isWorkFinished = false;
        isShortbreakFinished = false;
        isLongbreakFinished = false;
        isTimerPaused = false;
        start_button.setText("Start");
        statusLB.setText("FOCUS");
        statusLB.setForeground(Color.BLACK);
        timeDisplay.setText(String.format("%02d:%02d", updated_pomodoro_minutes, pomodoro_seconds)); // to let it show the timeDisplay the same as the value in the JSpinners
        progress_label.setText("Start working today!"); // Add this line to set the default progress_label text
    }
    public void updateToInitialValues() {
        pomodoro_minutes = updated_pomodoro_minutes;
        totalRound = updated_totalround;
        currentRounds = 1;
        pomodoro_seconds = 0;
        updateSessionLabel();
        timeDisplay.setText(String.format("%02d:%02d", pomodoro_minutes, pomodoro_seconds));
        isWorkStarted = false;
        isShortbreakStarted = false;
        isLongbreakStarted = false;
    }
    public void updateSessionLabel() {
        String sessionformat = String.format("Session %d of %d", currentRounds, updated_totalround);
        sessionLB.setText(sessionformat);
    }
    public void updateProgressLabel(String labelText) {
        progress_label.setText(labelText);
        progress_label.setBounds(50, 340, 200, 20); // Set the bounds of the progress_label to make it fully visible
    }

    public void resetProgressBar() {
        progressBar.setValue(0);
        progressBar.setString("0%");
        progress = 0;
    }
     public void fillProgressBar() {
         int totalSeconds;
         int elapsedSeconds;

         if (isWorkStarted) {
             totalSeconds = updated_pomodoro_minutes * 60;
             elapsedSeconds = (updated_pomodoro_minutes * 60) - ((pomodoro_minutes * 60) + pomodoro_seconds);
         } else if (isShortbreakStarted) {
             totalSeconds = updated_shortbreak_minutes * 60;
             elapsedSeconds = (updated_shortbreak_minutes * 60) - ((shortbreak_minutes * 60) + shortbreak_minutes);
         } else { // isLongbreakStarted
             totalSeconds = updated_longbreak_minutes * 60;
             elapsedSeconds = (updated_longbreak_minutes * 60) - ((longbreak_minutes * 60) + longbreak_minutes);

         }

         //Implicitly cast the int values to double when calculating the progress percentage for the progress bar.

         double progressPercentage = (elapsedSeconds / totalSeconds) * 100;
         //Explicit casting to convert the result of Math.min(progressPercentage, 100) from double to int.
         progressBar.setValue((int) Math.min(progressPercentage, 100));
         // Math.min is used to limit the progress bar value to 100
         progressBar.setString(String.format("%.0f%%", progressPercentage));
     }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == ' ') { // Check if the key event is a space bar press
            // Trigger the start_button action
            startButtonAction();
        }
        setFocusable(true); // Make the Pomodoro panel focusable to receive key events
        requestFocusInWindow(); // Request focus for the Pomodoro panel
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void startButtonAction() { //same as when e.getSource == start_button
        if (!isWorkStarted && !isShortbreakStarted && !isLongbreakStarted) {
            startWorksession();
            isTimerPaused = false;
        } else {
            if (!isTimerPaused) {
                stopTimer();
                isTimerPaused = true;
            } else { //when the timer is already used and not paused (we click the button to resume)
                // Use the appropriate start method depending on the current session type.
                if (isWorkStarted) {
                    startWorksession();
                } else if (isShortbreakStarted) {
                    startShortbreak();
                } else if (isLongbreakStarted) {
                    startLongbreak();
                }
                isTimerPaused = false;
            }
            start_button.setText(isTimerPaused ? "Start" : "Stop"); //Ternary operator to setText of the start_button
        }
    }
    //to change the background color of the Pomodoro panel
    public void changeBackgroundColor(Color newColor) {
        this.setBackground(newColor);
        if (dateAndTimePanel != null) {
            dateAndTimePanel.changeBackgroundColor(newColor);
        }
    }
}
