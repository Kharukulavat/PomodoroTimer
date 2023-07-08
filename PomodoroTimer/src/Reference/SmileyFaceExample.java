package Reference;
import javax.swing.*;
import java.awt.*;
public class SmileyFaceExample extends JPanel{
    public SmileyFaceExample() {
//        this.setLayout(null);
//        this.setBounds(110, 10, 60, 60);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        g.fillOval(50, 50, 200, 200); //50,50,200,200

        // Draw the eyes
        g.setColor(Color.BLACK);
        g.fillOval(90, 100, 30, 30);
        g.fillOval(180, 100, 30, 30);
        //90,180,30,30
        //180,100,30,30

        // Draw the mouth with natural curve
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.drawArc(100, 130, 100, 60, 200, 140);
        //100,130,100,60,200,140
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Smiley Face");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new timer.SmileyFace());
        frame.pack();
        frame.setVisible(true);
    }
}
