package Frontend;

import javax.swing.*;
import java.awt.*;

public class Mill extends JLabel {
    protected Direction direction;
    public enum Direction{
        HORIZONTAL,
        VERTICAL,
        FIRST_HALF_HORIZONTAL,
        LAST_HALF_HORIZONTAL,
        FIRST_HALF_VERTICAL,
        LAST_HALF_VERTICAL
    }

    public Mill(Direction direction){
        this.direction = direction;
    }

    public void paintComponent(Graphics g){
        //set the color of line
        g.setColor(new Color(0xFFF000));

        //set the width of line
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(7));

        //draw the line from x and y coordinates of first point to the other
        Dimension dimension = this.getSize();
        if(direction == Direction.HORIZONTAL)
            g.drawLine(0,dimension.height/2,dimension.width, dimension.height/2);
        else if (direction == Direction.FIRST_HALF_HORIZONTAL){
            g.drawLine(dimension.width/2,dimension.height/2,dimension.width, dimension.height/2);
        }
        else if (direction == Direction.LAST_HALF_HORIZONTAL){
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
