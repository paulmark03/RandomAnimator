import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Dingus represents an arbitraty shape.
 * 
 * @author Paul Nicolae Marcu
 * @id 1844989
 */
abstract class Dingus {
    /**
     * Random generator to be used by all subclasses of Dingus.
     * DON'T CHANGE
     */
    Random random = Painting.RANDOM;

    /**
     * Postion of the shape (upper left corner).
     *
     */
    protected int x;
    protected int y;

    protected int limitUpperY;
    protected int limitLowerY;
    protected int limitLeftX;
    protected int limitRightX;

    int velX = 2;
    int velY = 2;

    /**
     * Color used for drawing this shape.
     */
    protected Color color;

    /**
     * Maximal coordinates; drawing area is (0,0)- (maxX,maxY).
     */
    int maxX;
    int maxY;

    /**
     * Initialize color and position to random values.
     *
     * @param maxX upper bound for the x coordinate of the position
     * @param maxY upper bound for the y coordinate of the position
     */
    public Dingus(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;

        // Initialize to a random position
        this.updateCoords(random.nextInt(maxX * 3 / 4), random.nextInt(maxY * 2 / 3));

        // Initialize to a random color
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        Color color = new Color(r, g, b);
        this.color = color;

    }

    abstract void draw(Graphics g);

    abstract void updateCoords(int x, int y);

    /**
     * This method checks the position of every shape
     * and when a shape hits a boarder it changes its
     * velocity to a negative value.
     * 
     */
    public void checkOutOfBounds() {
        if (this.limitLeftX < 0 || this.limitRightX > this.maxX) {
            this.velX = -this.velX;
        }
        if (this.limitUpperY < 0 || this.limitLowerY > this.maxY) {
            this.velY = -this.velY;
        }
    }
}