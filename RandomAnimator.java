import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/** 
 * This class is used for declaring the GUI and all its elements.
 * It is also the main class.
 * 
 * @author Paul Nicolae Marcu
 * @id 1844989
 */
public class RandomAnimator {
    JFrame frame;
    Painting painting; // panel that provides the random painting
    JButton regenerateButton;
    JButton shotButton;
    JButton recolor;
    JButton start;
    JButton stop;
    
    /**
     * Create a new instance of the RandomAnimator application.
     */
    RandomAnimator() {
        // invokeLater: preferred way to create components
        SwingUtilities.invokeLater(new Runnable() { //what is this
            @Override
            public void run() {
                painting = new Painting();
                // painting.setBorder(BorderFactory.createLineBorder(Color.black));
                frame = new JFrame("Computer Assisted Random Animator");
                frame.add(painting, BorderLayout.CENTER);

                JPanel buttonsPanel = new JPanel();

                regenerateButton = new JButton("Regenerate");
                regenerateButton.addActionListener(painting); 
                buttonsPanel.add(regenerateButton);

                shotButton = new JButton("Screenshot");
                shotButton.addActionListener(painting);
                buttonsPanel.add(shotButton);
               
                recolor = new JButton("recolor");
                recolor.addActionListener(painting);
                buttonsPanel.add(recolor);

                start = new JButton("start");
                start.addActionListener(painting);
                buttonsPanel.add(start);

                stop = new JButton("stop");
                stop.addActionListener(painting);
                buttonsPanel.add(stop);

                frame.add(buttonsPanel, BorderLayout.SOUTH);

                frame.pack();

                painting.regenerate(); // can be done here since painting has a size!
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] arg) {
        new RandomAnimator();
    }
}