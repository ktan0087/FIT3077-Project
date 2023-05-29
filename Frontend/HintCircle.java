package Frontend;

import Frontend.Game.Intersection;
import Frontend.Layer.PlaceToken;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HintCircle extends JLabel {
    private boolean circleVisible = true;

    public HintCircle() {
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circleVisible = !circleVisible;
                repaint();
            }
        });
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(5));

        if (circleVisible) {
            int x = getWidth()/2; // to get the middle point
            int y = getHeight()/2; // to get the middle point
            int radius = 8;
            int diameter = radius * 2;

            //shift x and y by the radius of the circle in order to correctly center it
            g.drawOval(x - radius, y - radius, diameter, diameter);

        }
    }

    public void showHint(PlaceToken hintLayer, Intersection intersection){
        hintLayer.remove(intersection.getAccessibleContext().getAccessibleIndexInParent());
        hintLayer.add(this, intersection.getAccessibleContext().getAccessibleIndexInParent());
    }
}
