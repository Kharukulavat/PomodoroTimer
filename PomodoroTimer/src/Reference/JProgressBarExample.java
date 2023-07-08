package Reference;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class JProgressBarExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JProgressBar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setLayout(new BorderLayout());

        // Create a JProgressBar
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        frame.add(progressBar, BorderLayout.CENTER);

        // Add a JButton to simulate a task
        JButton button = new JButton("Start Task");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setEnabled(false);

                // Simulate a long-running task in a separate thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 100; i++) {
                            try {
                                Thread.sleep(100); // Simulate a delay in the task
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }

                            // Update the progress bar value
                            int finalI = i;
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setValue(finalI);
                                }
                            });
                        }
                        button.setEnabled(true);
                    }
                }).start();
            }
        });
        frame.add(button, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
