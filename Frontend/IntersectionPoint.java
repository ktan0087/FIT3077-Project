import javax.swing.*;
import java.awt.*;

// Customized circle button
public class IntersectionPoint extends JButton {
//    private final String label;

    public IntersectionPoint(){
//        super(label);
//        this.label = label;

        this.setBackground(new Color(0x000000)); // set the background color of the circle button to Black
        setContentAreaFilled(false); // to have a transparent button, otherwise it will show like a square button
    }

    // This method is used to paint the circle button
    protected void paintComponent(Graphics g) {
        g.fillOval(0, 0, getSize().width-1,getSize().height-1);
        super.paintComponent(g);
    }

    // This method is used to paint the border of the circle button
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width-1,getSize().height-1);
    }

}
