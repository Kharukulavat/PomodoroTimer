package control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class DateAndTime extends CheckboxControl implements ActionListener, ItemListener {
    //    Calendar calendar; //get the current date and time ,we can call it directly, we don't need this line
    SimpleDateFormat timeFormat; //use to arrange the time we want
    SimpleDateFormat dateFormat;
    JLabel timeLb = new JLabel(); //use to display the time
    JLabel dateLb = new JLabel();
    String time;
    String date;
    protected Timer timer = new Timer(1000,this);
    public DateAndTime() {
        this.setLayout(null);
//        this.setBackground(Color.PINK);
        this.setBounds(0,0,200,400);
        dateFormat = new SimpleDateFormat(" EEEE dd MMMMM, yyyy");
        //E is day name in the week (abbreviated), 'EEEE' for full form
        //'MMM' is month name(abbreviated), 'EEEEE' for full form
        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        //from Date and Time patterns from Oracle8, 'a' is Am/pm marker

        dateLb.setFont(new Font("Serif",Font.PLAIN, 16));

        timeLb.setFont(new Font("Serif", Font.PLAIN, 16));

        dateLb.setBounds(10,10,200,20);
        timeLb.setBounds(15,30,200,20);

        Date_cb.setBounds(10,320,100,20);
        CurrentTime_cb.setBounds(10,345,150,20);

        Date_cb.setSelected(true);
        CurrentTime_cb.setSelected(true);

        Date_cb.addActionListener(this);
        Date_cb.addItemListener(this);
        CurrentTime_cb.addActionListener(this);
        CurrentTime_cb.addItemListener(this);

        this.add(dateLb);
        this.add(timeLb);
        this.add(Date_cb);
        this.add(CurrentTime_cb);

        timer.start();

    }
    public void actionPerformed(ActionEvent e) {
        setTime();
    }
    public void setTime() {
        Calendar calendar = Calendar.getInstance(); // implicit cast from java.util.Date to java.util.Calendar
        time = timeFormat.format(calendar.getTime());
        timeLb.setText(time);

        date = dateFormat.format(calendar.getTime());
        dateLb.setText(date);

//        time = timeFormat.format(Calendar.getInstance().getTime());
//        timeLb.setText(time);
//
//        date = dateFormat.format(Calendar.getInstance().getTime());
//        dateLb.setText(date);

    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == Date_cb && e.getStateChange() == 1) {
            dateLb.setVisible(true);
        } else if (e.getSource() == Date_cb && e.getStateChange() == 2) {
            dateLb.setVisible(false);
        }

        if(e.getSource() == CurrentTime_cb && e.getStateChange() == 1) {
            timeLb.setVisible(true);
        } else if (e.getSource() == CurrentTime_cb && e.getStateChange() == 2) {
            timeLb.setVisible(false);
        }
    }
    //to change the background color of the DateAndTime panel
    public void changeBackgroundColor(Color newColor) {
        this.setBackground(newColor);
    }

}
