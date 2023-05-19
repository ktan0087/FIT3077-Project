package Frontend.Line;

import javax.swing.*;
import java.awt.*;

/**
 * A class to represent the yellow Mill Line in the game.
 */
public class Mill extends JLabel {
    /**
     * The direction of the Mill Line
     */
    private Direction direction;

    /**
     * An enum to represent the direction of the Mill Line
     */
    public enum Direction{
        HORIZONTAL,
        VERTICAL,
        FIRST_HALF_HORIZONTAL,
        LAST_HALF_HORIZONTAL,
        FIRST_HALF_VERTICAL,
        LAST_HALF_VERTICAL
    }

    /**
     * Constructor.
     * Creates a Mill Line with the given direction.
     *
     * @param direction the direction of the Mill Line
     */
    public Mill(Direction direction){
        this.direction = direction;
    }

    /**
     * Draws the Mill Line.
     * The Mill Line will draw only half if it is the first or the last section of the Mill Line.
     * So that, the Mill Line will not exceed the token.
     *
     * @param g the graphics object
     */
    public void paintComponent(Graphics g){
        //set the color of line
        g.setColor(new Color(0xFFF000));

        //set the width of line
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(7));

        //get the dimension of current grid
        Dimension dimension = this.getSize();

        //draw the line from x and y coordinates of first point to the other
        if(direction == Direction.HORIZONTAL) // normal horizontal line
            g.drawLine(0,dimension.height/2,dimension.width, dimension.height/2);
        else if (direction == Direction.FIRST_HALF_HORIZONTAL){ // first half of horizontal line
            g.drawLine(dimension.width/2,dimension.height/2,dimension.width, dimension.height/2);
        }
        else if (direction == Direction.LAST_HALF_HORIZONTAL){ // last half of horizontal line
            g.drawLine(0,dimension.height/2,dimension.width/2, dimension.height/2);
        }

        else if (direction == Direction.VERTICAL) {
            g.drawLine(dimension.height/2, 0, dimension.height/2, dimension.width);
        }
        else if (direction == Direction.FIRST_HALF_VERTICAL){
            g.drawLine(dimension.height/2, dimension.width/2, dimension.height/2, dimension.width);
        }
        else if (direction == Direction.LAST_HALF_VERTICAL){
            g.drawLine(dimension.height/2, 0, dimension.height/2, dimension.width/2);
        }
    }
}
