package Frontend;

import Frontend.Components.Board;
import Frontend.Game.Intersection;
import Frontend.Layer.PlaceToken;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HintCircle extends JLabel {
    private boolean circleVisible;
    private Type type;

    public enum Type{
        INTERSECTION,
        TOKEN
    }

    public HintCircle(Type type) {
        this.circleVisible = true;
        this.type = type;
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
        int radius = 0;

        if (this.type == Type.INTERSECTION) {
            radius = 8;
        }
        else if (this.type == Type.TOKEN) {
            radius = 16;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0xFF0000));
        g2d.setStroke(new BasicStroke(5));

        if (circleVisible) {
            int x = getWidth()/2; // to get the middle point
            int y = getHeight()/2; // to get the middle point
            int diameter = radius * 2;

            //shift x and y by the radius of the circle in order to correctly center it
            g.drawOval(x - radius, y - radius, diameter, diameter);

        }
    }

    public void showHint(PlaceToken hintLayer, Intersection intersection, Board b){
        hintLayer.remove(Integer.parseInt(String.valueOf(b.getIndexLookUpTable(intersection.getCoordinateX(), intersection.getCoordinateY()))));
        hintLayer.add(this, Integer.parseInt(String.valueOf(b.getIndexLookUpTable(intersection.getCoordinateX(), intersection.getCoordinateY()))));
    }
}
