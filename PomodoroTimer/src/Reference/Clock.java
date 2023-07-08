package Reference;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Clock extends JFrame{
//    Calendar calendar; //get the current date and time ,we can call it directly, we don't need this line
    SimpleDateFormat timeFormat; //use to arrange the time we want
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;
    JLabel timeLabel; //use to display the time
    JLabel dayLabel;
    JLabel dateLabel;
    String time;
    String day;
    String date;
    public Clock() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Current Time");
        this.setLayout(new FlowLayout());
        this.setSize(350,200);
//        this.setResizable(false);

        timeFormat = new SimpleDateFormat("hh:mm:ss a"); //from Date and Time patterns from Oracle8, 'a' is Am/pm marker
        dayFormat = new SimpleDateFormat("EEEE"); //E is day name in the week (abbreviated), 'EEEE' for full form
        dateFormat = new SimpleDateFormat("dd MMMMM, yyyy"); //'MMM' is month name(abbreviated), 'EEEEE' for full form

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Vernada", Font.PLAIN, 50));
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setBackground(Color.PINK);
        timeLabel.setOpaque(true); //to display the background

        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Ink Tree",Font.PLAIN, 35));

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Tree",Font.PLAIN, 25));


        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);
        this.setVisible(true);

        setTime();
    }
    public void setTime() {
        while(true) {
            time = timeFormat.format(Calendar.getInstance().getTime());
            timeLabel.setText(time);

            day = dayFormat.format(Calendar.getInstance().getTime());
            dayLabel.setText(day);

            date = dateFormat.format(Calendar.getInstance().getTime());
            dateLabel.setText(date);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
//                throw new RuntimeException(e);
            }

        }
    }
    public static void main(String[] args) {
        new Clock();
    }
}
