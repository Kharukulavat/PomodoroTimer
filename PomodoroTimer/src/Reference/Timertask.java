package Reference;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Timertask {
    public static void main(String[] args) {

        //Timer = A facility for threads to schedule tasks for future execution in a background thread
        //TimerTask = A task that can be scheduled for one-time or repeated execution by a Timer
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int counter = 10;
            @Override
            public void run() {
                if (counter > 0) {
                    System.out.println(counter + " seconds");
                    counter--;
                }
                else {
                    System.out.println("WAKE UP!!!");
                    timer.cancel();
                }
            }
        };
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR,2023);
        date.set(Calendar.MONTH,Calendar.APRIL);
        date.set(Calendar.DAY_OF_MONTH ,15);
        date.set(Calendar.HOUR_OF_DAY,0);
        date.set(Calendar.MINUTE,0);
        date.set(Calendar.SECOND,50);
        date.set(Calendar.MILLISECOND,0);

        timer.schedule(task,date.getTime());
        timer.scheduleAtFixedRate(task,0,1000);
//        timer.schedule(task,date.getTime(),1000);
    }
}
