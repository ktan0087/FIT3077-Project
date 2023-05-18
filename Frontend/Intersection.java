package Frontend;

import javax.swing.*;
import java.awt.*;

public class Intersection extends JLabel {
    /**
     * Customized intersection
     */
    private int coordinateX;
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

    IntersectionPoint inter; // create a circle button for intersection

    // Constructor
    public Intersection(int coordinateX, int coordinateY){
        this.inter = new IntersectionPoint(); // create a circle button for intersection
        this.setLayout(new GridLayout(3, 3, -1, -1)); // -1 is to prevent showing grid border
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    /**
     * Get the x coordinate (layer) of intersection
     * @return x coordinate (layer)
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Get the y coordinate (position) of intersection
     * @return y coordinate (position)
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * This method is used to choose the type of intersection by selecting the position of it
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
