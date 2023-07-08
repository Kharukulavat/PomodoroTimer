package timer;
import javax.swing.*;
import java.awt.*;
public class TimerPanel extends JPanel{
    public TimerPanel() {
        this.setLayout(null);
//        this.setBackground(Color.LIGHT_GRAY);

    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Draw TimerCircle
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(18));
        g2.drawOval(65,80,170,170);
    }

//    public static void main(String[] args) {
//        JFrame frame3 = new JFrame();
//        TimerPanel timerPanel = new TimerPanel();
//        frame3.add(timerPanel);
//        frame3.setSize(700,400);
//        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame3.setVisible(true);
//    }

}
