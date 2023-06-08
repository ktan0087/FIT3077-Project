package Frontend.Components;

import Frontend.Components.Board;
import Frontend.Game.Intersection;
import Frontend.Layer.PlaceToken;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is used to create the hint circle in tutorial.
 */
public class HintCircle extends JLabel {
    /**
     * circleVisible is used to keep track of whether the circle is visible, and it is used to help the circle blink.
     */
    private boolean circleVisible;

    /**
     * Type is used to keep track of the size of the hint circle. (either intersection or token)
     */
    private Type type;

    /**
     * An enum to represent the type of the hint circle.
     */
    public enum Type{
        INTERSECTION,
        TOKEN
    }

    /**
     * Constructor.
     * Creates a new hint circle with the given type.
     *
     * @param type the type of the hint circle, either intersection or token
     */
    public HintCircle(Type type) {
        this.circleVisible = true; // set to true so that the circle will be visible at the start
        this.type = type;

        /**
         * Create a timer to make the hint circle blink.
         */
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circleVisible = !circleVisible; // change the visibility of the hint circle
                repaint();
            }
        });
        timer.start();
    }

    /**
     * Paints the hint circle with the type given.
     * @param g the graphics context
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int radius = 0; // initialize the radius

        // set the radius according to the type
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

    /**
     * This method is used to show the hint circle on the given intersection.
     *
     * @param hintLayer the layer to show the hint circle on
     * @param intersection the intersection to show the hint circle on
     * @param b the board
     */
    public void showHint(PlaceToken hintLayer, Intersection intersection, Board b){
        hintLayer.remove(Integer.parseInt(String.valueOf(b.getIndexLookUpTable(intersection.getCoordinateX(), intersection.getCoordinateY()))));
        hintLayer.add(this, Integer.parseInt(String.valueOf(b.getIndexLookUpTable(intersection.getCoordinateX(), intersection.getCoordinateY()))));
    }
}
