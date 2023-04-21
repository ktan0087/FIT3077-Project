package Frontend;

import javax.swing.*;
import java.awt.*;

// Customized all intersections
public class Intersection extends JLabel {
    private int coordinateX;
    private int coordinateY;
    // the enum is used to determine the position of intersection
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
    protected Boolean isWhite = false;
    IntersectionPoint inter = new IntersectionPoint(); // create a circle button for intersection
    public Intersection(int coordinateX, int coordinateY){
        this.setLayout(new GridLayout(3, 3, -1, -1)); // -1 is to prevent showing grid border
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    // This method is used to choose the type of intersection by selecting the position of it
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
