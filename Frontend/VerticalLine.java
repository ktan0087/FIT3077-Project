package Frontend;

import javax.swing.*;
import java.awt.*;

public class VerticalLine extends JLabel {
    /**
     * This class is used to create a vertical line
     */

    /**
     * This class is used to create a vertical line
     * @param g is the abstract base class for all graphics contexts
     */
    public void paintComponent(Graphics g){
        //set the width of line
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        //draw the line from x and y coordinates of first point to the other
        Dimension dimension = this.getSize();
        g.drawLine(dimension.height/2,0,dimension.height/2, dimension.width);
    }
}
