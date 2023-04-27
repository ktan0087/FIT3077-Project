package Frontend;

import javax.swing.*;
import java.awt.*;

public class IntersectionPoint extends JButton {
    /**
     * Customized intersection point (circle button)
     */

    // Constructor
    public IntersectionPoint(){
        this.setBackground(new Color(0x000000)); // set the background color of the circle button to Black
        setContentAreaFilled(false); // to have a transparent button, otherwise it will show like a square button
    }

    /**
     * This method is used to paint the intersection point (circle button)
     * @param g is the abstract base class for all graphics contexts
     */
    protected void paintComponent(Graphics g) {
        g.fillOval(0, 0, getSize().width-1,getSize().height-1);
        super.paintComponent(g);
    }

    /**
     * This method is used to paint the border of the intersection point (circle button)
     * @param g is the abstract base class for all graphics contexts
     */
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width-1,getSize().height-1);
    }

}
