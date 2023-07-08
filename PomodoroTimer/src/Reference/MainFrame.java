package Reference;
import control.DateAndTime;
import timer.Pomodoro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener, MouseListener{
    private JPanel left_panel = new JPanel();
    private JPanel center_panel = new JPanel();
    private JColorChooser colorChooser = new JColorChooser();
    protected Timer timer = new Timer(1000, this);
    int x;
    int y;


    public MainFrame(String title) {
        super(title);
//        this.setLayout(null);
        left_panel.setBounds(0,0,200,400);
        center_panel.setBounds(200,0,500,400);

        left_panel.add(new DateAndTime());


        center_panel.setBackground(Color.LIGHT_GRAY);
        center_panel.add(new Pomodoro());
//        center_panel.add(new PomodoroControl());
//        left_panel.setBackground(Color.GRAY);


        this.add(left_panel);
        this.add(center_panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        System.out.printf(String.format("%d , %d%n",x,y));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame("PomodoroTimer");
        ImageIcon PomodoroIcon = new ImageIcon("PomodoroTimerIcon.png");
//        frame.pack();
        frame.setSize(700,400);
        frame.setResizable(true);
        frame.setIconImage(PomodoroIcon.getImage());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
