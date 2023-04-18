import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Used to display the board
public class Board extends JPanel {
    // Create 24 intersections
    Intersection a7 = new Intersection();
    Intersection d7 = new Intersection();
    Intersection g7 = new Intersection();
    Intersection b6 = new Intersection();
    Intersection d6 = new Intersection();
    Intersection f6 = new Intersection();
    Intersection c5 = new Intersection();
    Intersection d5 = new Intersection();
    Intersection e5 = new Intersection();
    Intersection a4 = new Intersection();
    Intersection b4 = new Intersection();
    Intersection c4 = new Intersection();
    Intersection e4 = new Intersection();
    Intersection f4 = new Intersection();
    Intersection g4 = new Intersection();
    Intersection c3 = new Intersection();
    Intersection d3 = new Intersection();
    Intersection e3 = new Intersection();
    Intersection b2 = new Intersection();
    Intersection d2 = new Intersection();
    Intersection f2 = new Intersection();
    Intersection a1 = new Intersection();
    Intersection d1 = new Intersection();
    Intersection g1 = new Intersection();

    ArrayList<WhiteToken> whiteList=new ArrayList<>(); // create a list to store white tokens
    ArrayList<Intersection> intersectionList=new ArrayList<>(); // create a list to store intersections

    public List<Intersection> getIntersectionList() {
        // Add all intersections to the list
        intersectionList.add(a7);
        intersectionList.add(d7);
        intersectionList.add(g7);
        intersectionList.add(b6);
        intersectionList.add(d6);
        intersectionList.add(f6);
        intersectionList.add(c5);
        intersectionList.add(d5);
        intersectionList.add(e5);
        intersectionList.add(a4);
        intersectionList.add(b4);
        intersectionList.add(c4);
        intersectionList.add(e4);
        intersectionList.add(f4);
        intersectionList.add(g4);
        intersectionList.add(c3);
        intersectionList.add(d3);
        intersectionList.add(e3);
        intersectionList.add(b2);
        intersectionList.add(d2);
        intersectionList.add(f2);
        intersectionList.add(a1);
        intersectionList.add(d1);
        intersectionList.add(g1);

        return this.intersectionList;
    }

    public Board(){
        this.setBackground(new Color(0xE6B380)); // set the background color of the board
        this.setPreferredSize(new Dimension(500,500)); // set the size of the board

        // set the layout of the board, 13 rows and 13 columns
        this.setLayout(new GridLayout(13, 13, -1, -1)); // -1 is to remove the space between intersections and lines

        // Add all intersections and lines to the board
        a7.choosePosition(Intersection.Position.TOP_LEFT);
        this.add(a7);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        d7.choosePosition(Intersection.Position.TOP_MIDDLE);
        this.add(d7);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        g7.choosePosition(Intersection.Position.TOP_RIGHT);
        this.add(g7);

        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new VerticalLine());

        this.add(new VerticalLine());
        this.add(new JLabel());
        b6.choosePosition(Intersection.Position.TOP_LEFT);
        this.add(b6);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        d6.choosePosition(Intersection.Position.MIDDLE);
        this.add(d6);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        f6.choosePosition(Intersection.Position.TOP_RIGHT);
        this.add(f6);
        this.add(new JLabel());
        this.add(new VerticalLine());

        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());

        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        c5.choosePosition(Intersection.Position.TOP_LEFT);
        this.add(c5);
        this.add(new HorizontalLine());
        d5.choosePosition(Intersection.Position.BOTTOM_MIDDLE);
        this.add(d5);
        this.add(new HorizontalLine());
        e5.choosePosition(Intersection.Position.TOP_RIGHT);
        this.add(e5);
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());

        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());

        a4.choosePosition(Intersection.Position.MIDDLE_LEFT);
        this.add(a4);
        this.add(new HorizontalLine());
        b4.choosePosition(Intersection.Position.MIDDLE);
        this.add(b4);
        this.add(new HorizontalLine());
        c4.choosePosition(Intersection.Position.MIDDLE_RIGHT);
        this.add(c4);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        e4.choosePosition(Intersection.Position.MIDDLE_LEFT);
        this.add(e4);
        this.add(new HorizontalLine());
        f4.choosePosition(Intersection.Position.MIDDLE);
        this.add(f4);
        this.add(new HorizontalLine());
        g4.choosePosition(Intersection.Position.MIDDLE_RIGHT);
        this.add(g4);

        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());

        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        c3.choosePosition(Intersection.Position.BOTTOM_LEFT);
        this.add(c3);
        this.add(new HorizontalLine());
        d3.choosePosition(Intersection.Position.TOP_MIDDLE);
        this.add(d3);
        this.add(new HorizontalLine());
        e3.choosePosition(Intersection.Position.BOTTOM_RIGHT);
        this.add(e3);
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());

        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new VerticalLine());

        this.add(new VerticalLine());
        this.add(new JLabel());
        b2.choosePosition(Intersection.Position.BOTTOM_LEFT);
        this.add(b2);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        d2.choosePosition(Intersection.Position.MIDDLE);
        this.add(d2);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        f2.choosePosition(Intersection.Position.BOTTOM_RIGHT);
        this.add(f2);
        this.add(new JLabel());
        this.add(new VerticalLine());

        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new VerticalLine());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new VerticalLine());

        a1.choosePosition(Intersection.Position.BOTTOM_LEFT);
        this.add(a1);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        d1.choosePosition(Intersection.Position.BOTTOM_MIDDLE);
        this.add(d1);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        g1.choosePosition(Intersection.Position.BOTTOM_RIGHT);
        this.add(g1);
    }
}
