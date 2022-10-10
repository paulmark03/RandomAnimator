import java.awt.Graphics;

/**
 * TreeDingus is an example of a slightly more advanced Dingus.
 * 
 * TreeDingus draws a "tree" using a rectangle as the trunk, and circle for a
 * crown.
 * 
 * @author NAME
 * @id ID
 * @author NAME
 * @id ID
 */
class TreeDingus extends Dingus {
    private int crownRadius;
    private int trunkHeight;
    private int trunkWidth;
    private boolean filled; // true: filled; false: outline

    /**
     * Create and initialize a new TreeDingus.
     * 
     * @param maxX upper bound for the x coordinate of the position
     * @param maxY upper bound for the y coordinate of the position
     */
    public TreeDingus(int maxX, int maxY) {
        // initialize Dingus properties
        super(maxX, maxY);

        // initialize TreeDingus properties
        crownRadius = random.nextInt(10, maxX / 18); // or something more sophisticated
        trunkHeight = random.nextInt(crownRadius, crownRadius * 2);
        trunkWidth = crownRadius / 3 + 1;
        filled = random.nextBoolean();
    }

    @Override
    void draw(Graphics g) {
        // draw crown
        g.setColor(color);
        if (filled) {
            // more general way to draw an oval than with fillOval (hint :-)
            g.fillArc(x, y, 2 * crownRadius, 2 * crownRadius, 0, 360);
        } else {
            g.drawArc(x, y, 2 * crownRadius, 2 * crownRadius, 0, 360);
        }

        // draw trunk
        g.setColor(color);
        int xx = x + crownRadius - trunkWidth / 2;
        int yy = y + 2 * crownRadius;

        if (filled) {
            g.fillRect(xx, yy, trunkWidth, trunkHeight);
        } else {
            g.drawRect(xx, yy, trunkWidth, trunkHeight);
        }
        // g.drawRect(limitLeftX, limitUpperY, limitRightX - limitLeftX, limitLowerY - limitUpperY);
    }
  
    @Override
    void updateCoords(int x, int y) {
        this.x = x;
        this.y = y;

        this.limitUpperY = this.y;
        this.limitLowerY = this.y + 2 * this.crownRadius + this.trunkHeight;
        this.limitLeftX = this.x;
        this.limitRightX = this.x + 2 * this.crownRadius;
    }
}