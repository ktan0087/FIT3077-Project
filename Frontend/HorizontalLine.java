package Frontend;

import javax.swing.*;
import java.awt.*;

// Create a horizontal line
public class HorizontalLine extends JLabel {
    /**
     * Create a horizontal line
     */

    /**
     * draw a horizontal line
     * @param g the abstract base class for all graphics contexts
     */
    public void paintComponent(Graphics g){
        //set the width of line
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        //draw the line from x and y coordinates of first point to the other
        Dimension dimension = this.getSize();
        g.drawLine(0,dimension.height/2,dimension.width, dimension.height/2);
    }
}
