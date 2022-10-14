import java.awt.Graphics;

/**
 * CircleDingus is an example of a very simple Dingus.
 * 
 * @author Paul Nicolae Marcu
 * @id 1844989
 */
class RectangleDingus extends Dingus {
    protected int height;
    protected int width;
    protected boolean filled; // true: filled, false: outline

    /**
     * Create and initialize a new CircleDingus.
     * 
     * @param maxX upper bound for the x coordinate of the position
     * @param maxY upper bound for the y coordinate of the position
     */
    public RectangleDingus(int maxX, int maxY) {
        // intialize randomly the Dingus properties, i.e., position and color
        super(maxX, maxY);

        // initialize randomly the CircleDingus properties, i.e., radius and filledness
        width = random.nextInt(20, maxX / 4);
        height = random.nextInt(20, maxY / 4);
        filled = true;
    }

    @Override
    void draw(Graphics g) {
        g.setColor(color);
        if (filled) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawRect(x, y, width, height);
        }
        
        //g.drawRect(limitLeftX, limitUpperY, limitRightX - limitLeftX, limitLowerY - limitUpperY);
    }

    @Override
    void updateCoords(int x, int y) {
        this.x = x;
        this.y = y;

        this.limitUpperY = this.y;
        this.limitLowerY = this.y + this.height;
        this.limitLeftX = this.x;
        this.limitRightX = this.x + this.width;
    }
}
