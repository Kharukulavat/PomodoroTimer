package timer;
import javax.swing.*;
import java.awt.*;
public class SmileyFace extends JPanel{
    public SmileyFace() {
        this.setLayout(null);
//        this.setBounds(110, 10, 60, 60);
        this.setPreferredSize(new Dimension(40, 40));
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

         //Draw the yellow circle
        g.setColor(new Color(255,255,153)); //bright yellow color
        g.fillOval(15, 18, 30, 30);

        // Draw the eyes
        g.setColor(Color.BLACK);
        g.fillOval(22, 26, 6, 6);
        g.fillOval(32, 26, 6, 6);

        // Draw the mouth with natural curve
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.drawArc(20, 26, 20, 15, 200, 140);
//
//        //Actual normal size
//         //Draw the yellow circle
//        g.setColor(new Color(255,255,153));
//        g.fillOval(50, 50, 200, 200); //50,50,200,200
//
//        // Draw the eyes
//        g.setColor(Color.BLACK);
//        g.fillOval(90, 100, 30, 30);
//        g.fillOval(180, 100, 30, 30);
//        //90,180,30,30
//        //180,100,30,30
//
//        // Draw the mouth with natural curve
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setStroke(new BasicStroke(5));
//        g2.drawArc(100, 130, 100, 60, 200, 140);
//        //100,130,100,60,200,140
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Smiley Face");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(new SmileyFace());
//        frame.pack();
//        frame.setVisible(true);
//    }
}
