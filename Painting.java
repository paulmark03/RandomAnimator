import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Painting.
 * 
 * Paint with Dinguses, i.e., generate a new painting by making a random
 * composition of Dingus shapes.
 *
 * TODO:
 *
 * @author Paul Nicolae Marcu
 * @id 1844989
 */
public class Painting extends JPanel implements ActionListener {

    /*---- Randomness ----*/

    /**
     * Seed for the random number generator.
     * 
     * You can change the variable SEED if you like. The same program with the same
     * SEED will generate exactly the same sequence of pictures.
     */
    static final long SEED = 37;

    // DON'T CHANGE the following three lines:
    // RANDOM is the random number generator used and shared by all classes in your
    // program.
    static final Random RANDOM = new Random(SEED);
    int numberOfRegenerates = 0;

    // ---- Screenshots ----
    // DON'T CHANGE the following two lines:
    char current = '0';
    String filename = "randomshot_"; // prefix

    /*---- Dinguses ----*/
    ArrayList<Dingus> shapes = new ArrayList<Dingus>();

    // ...

    /**
     * Create a new painting.
     */
    public Painting() {
        setPreferredSize(new Dimension(800, 450)); // make panel 800 by 450 pixels.

    }

    @Override
    protected void paintComponent(Graphics g) { // draw all your shapes
        super.paintComponent(g); // clears the panel

        // draw all shapes
        // TODO
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).draw(g);
        }
    }

    boolean startPressed = false;

    /**
     * Reaction to button press.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Regenerate".equals(e.getActionCommand())) {
            regenerate();
            repaint();
        } else if ("Screenshot".equals(e.getActionCommand())) { // screenshot
            saveScreenshot(this, filename + current++); // ++ to show of compact code :-)
        } else if ("recolor".equals(e.getActionCommand())) {
            recolor();
        } else if ("stop".equals(e.getActionCommand())) {
            pause();
        } else { // start
            start();
            startPressed = true;
        }

    }

    /**
     * Regenerate this painting.
     */
    void regenerate() {
        numberOfRegenerates++; // do not change

        // clear the shapes list
        // TODO
        shapes.clear();

        // create random shapes
        // TODO

        int numberOfShapes = RANDOM.nextInt(10, 21);
        for (int i = 0; i < numberOfShapes; i++) {
            int task = RANDOM.nextInt(1, 5);
            switch (task) {
                case 1:
                    CircleDingus shape = new CircleDingus(800, 450);
                    shapes.add(shape);
                    break;
                case 2:
                    RectangleDingus shape2 = new RectangleDingus(800, 450);
                    shapes.add(shape2);
                    break;
                case 3:
                    OvalDingus shape4 = new OvalDingus(800, 450);
                    shapes.add(shape4);
                    break;
                case 4:
                    TreeDingus shape3 = new TreeDingus(800, 450);
                    shapes.add(shape3);
                    break;
                default:
                    break;
            }
        }
        int shapesToMove = RANDOM.nextInt(5, 10);
        for (int i = 0; i < shapes.size(); i++) {
            if (i < shapesToMove) {
                shapes.get(i).velX = RANDOM.nextInt(-3, 3);
                shapes.get(i).velY = RANDOM.nextInt(-3, 3);
            } else {
                shapes.get(i).velX = 0;
                shapes.get(i).velY = 0;
            }
        }
    }

    /**
     * we use the recolor method in order to give
     * random colors to out shapes. Each time the
     * button is pressed every shape changes its
     * color.
     */
    void recolor() {
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).color = new Color(
                    RANDOM.nextInt(255),
                    RANDOM.nextInt(255),
                    RANDOM.nextInt(255));
        }
        repaint();
    }

    /**
     * This method stops the animation.
     * 
     */
    void pause() {
        this.timer.stop();
        startPressed = false;
    }

    Timer timer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int x;
            int y;
            for (int i = 0; i < shapes.size(); i++) {

                shapes.get(i).checkOutOfBounds();

                x = shapes.get(i).x + shapes.get(i).velX;
                y = shapes.get(i).y + shapes.get(i).velY;

                shapes.get(i).updateCoords(x, y);

                repaint();
            }

        }
    });

    /**
     * The method start starts a timer that repaints the whole
     * panel every five miliseconds and reupdates the new
     * coordonates of the shapes which creates movment.
     */
    void start() {

        if (!startPressed) {
            int shapesToMove = RANDOM.nextInt(5, 11);
            Collections.shuffle(shapes);
            startPressed = true;

            for (int i = 0; i < shapes.size(); i++) {
                if (i < shapesToMove) {
                    shapes.get(i).velX = RANDOM.nextInt(-3, 3);
                    shapes.get(i).velY = RANDOM.nextInt(-3, 3);
                } else {
                    shapes.get(i).velX = 0;
                    shapes.get(i).velY = 0;
                }
            }
            this.timer.start();
        }

    }

    /**
     * Saves a screenshot of this painting to disk.
     * 
     * Note. This action will override existing files!
     *
     * @param component Component to be saved
     * @param name      filename of the screenshot, followed by a sequence number
     * 
     */
    void saveScreenshot(Component component, String name) {
        // minus 1 because the initial picture should not count
        String randomInfo = "" + SEED + "+" + (numberOfRegenerates - 1);
        System.out.println(SwingUtilities.isEventDispatchThread());
        BufferedImage image = new BufferedImage(
                component.getWidth(),
                component.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        // call the Component's paint method, using
        // the Graphics object of the image.
        Graphics graphics = image.getGraphics();
        component.paint(graphics); // alternately use .printAll(..)
        graphics.drawString(randomInfo, 0, component.getHeight());

        try {
            ImageIO.write(image, "PNG", new File(name + ".png"));
            System.out.println("Saved screenshot as " + name);
        } catch (IOException e) {
            System.out.println("Saving screenshot failed: " + e);
        }
    }
}