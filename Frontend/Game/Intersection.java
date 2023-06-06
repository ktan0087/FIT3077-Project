package Frontend.Game;

import Frontend.Components.IntersectionPoint;
import Frontend.Line.HorizontalLine;
import Frontend.Line.VerticalLine;

import javax.swing.*;
import java.awt.*;

/**
 * A class to represent the intersection of the board
 */

public class Intersection extends JLabel {
    /**
     * The x coordinate (layer) of intersection. The range is from 1 to 3.
     */
    private int coordinateX;

    /**
     * The y coordinate (position) of intersection. The range is from 1 to 8.
     */
    private int coordinateY;

    /**
     * This enum is used to determine the position of intersection
     */
    public enum Position{
        TOP_LEFT,
        TOP_MIDDLE,
        TOP_RIGHT,
        MIDDLE_LEFT,
        MIDDLE,
        MIDDLE_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_MIDDLE,
        BOTTOM_RIGHT
    }

    /**
     * The intersection point of the intersection
     * Creates a circle button for intersection
     */
    IntersectionPoint inter;

    /**
     * Constructor.
     * Creates a new intersection with the given x and y coordinates.
     *
     * @param coordinateX the x coordinate (layer) of intersection, e.g. the outmost layer has the x coordinate of 1
     * @param coordinateY the y coordinate (position) of intersection, which is counted like this: 1 2 3
     *                                                                                             8   4
     *                                                                                             7 6 5
     */
    public Intersection(int coordinateX, int coordinateY){
        this.inter = new IntersectionPoint(); // create a circle button for intersection
        this.setLayout(new GridLayout(3, 3));
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    /**
     * Getter for x coordinate (layer) of intersection.
     *
     * @return x coordinate (layer)
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Getter for y coordinate (position) of intersection.
     *
     * @return y coordinate (position)
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * toString method to print out the coordinate of intersection
     * @return Intersection with its coordinate
     */
    @Override
    public String toString() {
        return "Intersection{" +
                "coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                '}';
    }

    /**
     * This method is used to choose the type of intersection by selecting the position of it.
     *
     * @param position the position of intersection, e.g. the top leftmost intersection (1, 1) has the position of TOP_LEFT
     */
    public void choosePosition(Position position){
        switch (position){
            case TOP_LEFT:
                this.add(new JLabel());
                this.add(new JLabel());
                this.add(new JLabel());

                this.add(new JLabel());
                this.add(inter);
                this.add(new HorizontalLine());

                this.add(new JLabel());
                this.add(new VerticalLine());
                this.add(new JLabel());

                break;

            case TOP_MIDDLE:
                this.add(new JLabel());
                this.add(new JLabel());
                this.add(new JLabel());

                this.add(new HorizontalLine());
                this.add(inter);
                this.add(new HorizontalLine());

                this.add(new JLabel());
                this.add(new VerticalLine());
                this.add(new JLabel());

                break;

            case TOP_RIGHT:
                this.add(new JLabel());
                this.add(new JLabel());
                this.add(new JLabel());

                this.add(new HorizontalLine());
                this.add(inter);
                this.add(new JLabel());

                this.add(new JLabel());
                this.add(new VerticalLine());
                this.add(new JLabel());

                break;

            case MIDDLE_LEFT:
                this.add(new JLabel());
                this.add(new VerticalLine());
                this.add(new JLabel());

                this.add(new JLabel());
                this.add(inter);
                this.add(new HorizontalLine());

                this.add(new JLabel());
                this.add(new VerticalLine());
                this.add(new JLabel());

                break;

            case MIDDLE:
                this.add(new JLabel());
                this.add(new VerticalLine());
                this.add(new JLabel());

                this.add(new HorizontalLine());
                this.add(inter);
                this.add(new HorizontalLine());

                this.add(new JLabel());
                this.add(new VerticalLine());
                this.add(new JLabel());

                break;

            case MIDDLE_RIGHT:
                this.add(new JLabel());
                this.add(new VerticalLine());
                this.add(new JLabel());

                this.add(new HorizontalLine());
                this.add(inter);
                this.add(new JLabel());

                this.add(new JLabel());
                this.add(new VerticalLine());
                this.add(new JLabel());

                break;

            case BOTTOM_LEFT:
                this.add(new JLabel());
                this.add(new VerticalLine());
                this.add(new JLabel());

                this.add(new JLabel());
                this.add(inter);
                this.add(new HorizontalLine());

                this.add(new JLabel());
                this.add(new JLabel());
                this.add(new JLabel());

                break;

            case BOTTOM_MIDDLE:
                this.add(new JLabel());
                this.add(new VerticalLine());
                this.add(new JLabel());

                this.add(new HorizontalLine());
                this.add(inter);
                this.add(new HorizontalLine());

                this.add(new JLabel());
                this.add(new JLabel());
                this.add(new JLabel());

                break;

            case BOTTOM_RIGHT:
                this.add(new JLabel());
                this.add(new VerticalLine());
                this.add(new JLabel());

                this.add(new HorizontalLine());
                this.add(inter);
                this.add(new JLabel());

                this.add(new JLabel());
                this.add(new JLabel());
                this.add(new JLabel());

                break;
        }
    }

}
