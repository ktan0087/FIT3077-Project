import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Customized White Token
public class WhiteToken extends JLabel {
    Boolean clicked = false;
    public WhiteToken(){
        this.addMouseListener(mouseListener);
    }

    // When the token is selected, a red circle will be drawn around the token
    protected void paintBorder(Graphics g){
        if (this.clicked){
            int x = 500/(13*2); // 500 is the width of the panel, 13 is the number of columns/rows, 2 is to get the middle point
            int y = 500/(13*2); // this is to get the middle point of each grid

            // draw the outer circle
            g.setColor(new Color(0xFF0000));
            int diameterOuter = 500/13;
            g.fillOval(0, 0, diameterOuter, diameterOuter);

            // draw the inner circle
            g.setColor(new Color(0xF5F5DC));
            int radiusInner = 15;
            int diameterInner = radiusInner * 2;
            g.fillOval(x - radiusInner, y - radiusInner, diameterInner, diameterInner);
        }
    }

    protected void paintComponent(Graphics g) {
        // to cover the intersection point
        repaint();
        revalidate();

        // paint the white token
        g.setColor(new Color(0xF5F5DC));

        int x = 500/(13*2); // 500 is the width of the panel, 13 is the number of columns/rows, 2 is to get the middle point
        int y = 500/(13*2); // this is to get the middle point of each grid
        int radius = 15;
        int diameter = radius * 2;

        //shift x and y by the radius of the circle in order to correctly center it
        g.fillOval(x - radius, y - radius, diameter, diameter);

    }

    protected MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            clicked = true;
            repaint();
            revalidate();
        }
    };

}
