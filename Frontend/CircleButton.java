import javax.swing.*;
import java.awt.*;

// Customized circle button
public class CircleButton extends JButton {
//    private final String label;

    public CircleButton(){
//        super(label);
//        this.label = label;
        this.setBackground(new Color(0x000000));
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        g.fillOval(0, 0, getSize().width-1,getSize().height-1);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width-1,getSize().height-1);
    }

}
