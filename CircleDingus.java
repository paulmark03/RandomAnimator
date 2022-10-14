import java.awt.Graphics;

/**
 * CircleDingus is an example of a very simple Dingus.
 * 
 * @author Paul Nicolae Marcu
 * @id 1844989
 */
class CircleDingus extends Dingus {
    protected int radius;
    protected boolean filled; // true: filled, false: outline

    /**
     * Create and initialize a new CircleDingus.
     * 
     * @param maxX upper bound for the x coordinate of the position
     * @param maxY upper bound for the y coordinate of the position
     */
    public CircleDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);

        // initialize randomly the CircleDingus properties, i.e., radius and filledness
        radius = random.nextInt(20, maxY / 8);
        filled = true;
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        if (filled) {
            g.fillArc(x, y, 2 * radius, 2 * radius, 0, 360);
        } else {
            g.drawArc(x, y, 2 * radius, 2 * radius, 0, 360);
        }

        //g.drawRect(limitLeftX, limitUpperY, limitRightX - limitLeftX, limitLowerY - limitUpperY);
    }

    @Override
    void updateCoords(int x, int y) {
        this.x = x;
        this.y = y;

        this.limitUpperY = this.y;
        this.limitLowerY = this.y + 2 * this.radius;
        this.limitLeftX = this.x;
        this.limitRightX = this.x + 2 * this.radius;
    }
}
