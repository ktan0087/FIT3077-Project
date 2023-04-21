package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Token extends JLabel {
    protected int coordinateX;
    protected int coordinateY;
    protected int times;
    protected Boolean selected = false;
    protected int index;
    protected Color tokenColor;

    public Token(int coordinateX, int coordinateY){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.times = 0;
        this.index = -1; // index in board panel
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    // When the token is selected, a red circle will be drawn around the token
    protected void paintBorder(Graphics g) {
        if (this.selected) {
            // to cover the intersection point
            repaint();
            revalidate();

            int x = 500/(13*2); // 500 is the width of the panel, 13 is the number of columns/rows, 2 is to get the middle point
            int y = 500/(13*2); // this is to get the middle point of each grid

            // draw the outer circle
            g.setColor(new Color(0xFF0000));
            int diameterOuter = 500/13;
            g.fillOval(0, 0, diameterOuter, diameterOuter);

            // draw the inner circle
            g.setColor(this.tokenColor);
            int radiusInner = 15;
            int diameterInner = radiusInner * 2;
            g.fillOval(x - radiusInner, y - radiusInner, diameterInner, diameterInner);
        }
    }

    protected void paintComponent(Graphics g) {
        if (!this.selected) {
            // to cover the intersection point
            repaint();
            revalidate();

            // paint the white token
            g.setColor(this.tokenColor);

            int x = 500 / (13 * 2); // 500 is the width of the panel, 13 is the number of columns/rows, 2 is to get the middle point
            int y = 500 / (13 * 2); // this is to get the middle point of each grid
            int radius = 15;
            int diameter = radius * 2;

            //shift x and y by the radius of the circle in order to correctly center it
            g.fillOval(x - radius, y - radius, diameter, diameter);
        }
    }

    protected MouseListener tokenSelected = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (times%2 == 0) {
                selected = true; // click the white token to select it
            }
            else {
                selected = false; // click again the selected white token to deselect it
            }
            times++;
        }
    };

}
