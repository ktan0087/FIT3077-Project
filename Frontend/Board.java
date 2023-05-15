package Frontend;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Board extends JPanel {
    /**
     * Display the board
     */

    // Create 24 intersections
    private Intersection intersection_11 = new Intersection(1, 1);
    private Intersection intersection_12 = new Intersection(1, 2);
    private Intersection intersection_13 = new Intersection(1, 3);
    private Intersection intersection_21 = new Intersection(2, 1);
    private Intersection intersection_22 = new Intersection(2, 2);
    private Intersection intersection_23 = new Intersection(2, 3);
    private Intersection intersection_31 = new Intersection(3, 1);
    private Intersection intersection_32 = new Intersection(3, 2);
    private Intersection intersection_33 = new Intersection(3, 3);
    private Intersection intersection_18 = new Intersection(1, 8);
    private Intersection intersection_28 = new Intersection(2, 8);
    private Intersection intersection_38 = new Intersection(3, 8);
    private Intersection intersection_34 = new Intersection(3, 4);
    private Intersection intersection_24 = new Intersection(2, 4);
    private Intersection intersection_14 = new Intersection(1, 4);
    private Intersection intersection_37 = new Intersection(3, 7);
    private Intersection intersection_36 = new Intersection(3, 6);
    private Intersection intersection_35 = new Intersection(3, 5);
    private Intersection intersection_27 = new Intersection(2, 7);
    private Intersection intersection_26 = new Intersection(2, 6);
    private Intersection intersection_25 = new Intersection(2, 5);
    private Intersection intersection_17 = new Intersection(1, 7);
    private Intersection intersection_16 = new Intersection(1, 6);
    private Intersection intersection_15 = new Intersection(1, 5);

    private ArrayList<Intersection> intersectionList;
    private Map<Integer, Integer> indexLookUpTable;

    public List<Intersection> getIntersectionList() {
        return this.intersectionList;
    }

    // Constructor
    public Board() {
        this.setBackground(new Color(0xE6B380)); // set the background color of the board
        this.setPreferredSize(new Dimension(500, 500)); // set the size of the board

        // set the layout of the board, 13 rows and 13 columns
        this.setLayout(new GridLayout(13, 13, -1, -1)); // -1 is to remove the space between intersections and lines

        // Add all intersections and lines to the board
        intersection_11.choosePosition(Intersection.Position.TOP_LEFT);
        this.add(intersection_11);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        intersection_12.choosePosition(Intersection.Position.TOP_MIDDLE);
        this.add(intersection_12);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        intersection_13.choosePosition(Intersection.Position.TOP_RIGHT);
        this.add(intersection_13);

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
        intersection_21.choosePosition(Intersection.Position.TOP_LEFT);
        this.add(intersection_21);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        intersection_22.choosePosition(Intersection.Position.MIDDLE);
        this.add(intersection_22);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        intersection_23.choosePosition(Intersection.Position.TOP_RIGHT);
        this.add(intersection_23);
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
        intersection_31.choosePosition(Intersection.Position.TOP_LEFT);
        this.add(intersection_31);
        this.add(new HorizontalLine());
        intersection_32.choosePosition(Intersection.Position.BOTTOM_MIDDLE);
        this.add(intersection_32);
        this.add(new HorizontalLine());
        intersection_33.choosePosition(Intersection.Position.TOP_RIGHT);
        this.add(intersection_33);
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

        intersection_18.choosePosition(Intersection.Position.MIDDLE_LEFT);
        this.add(intersection_18);
        this.add(new HorizontalLine());
        intersection_28.choosePosition(Intersection.Position.MIDDLE);
        this.add(intersection_28);
        this.add(new HorizontalLine());
        intersection_38.choosePosition(Intersection.Position.MIDDLE_RIGHT);
        this.add(intersection_38);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
        intersection_34.choosePosition(Intersection.Position.MIDDLE_LEFT);
        this.add(intersection_34);
        this.add(new HorizontalLine());
        intersection_24.choosePosition(Intersection.Position.MIDDLE);
        this.add(intersection_24);
        this.add(new HorizontalLine());
        intersection_14.choosePosition(Intersection.Position.MIDDLE_RIGHT);
        this.add(intersection_14);

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
        intersection_37.choosePosition(Intersection.Position.BOTTOM_LEFT);
        this.add(intersection_37);
        this.add(new HorizontalLine());
        intersection_36.choosePosition(Intersection.Position.TOP_MIDDLE);
        this.add(intersection_36);
        this.add(new HorizontalLine());
        intersection_35.choosePosition(Intersection.Position.BOTTOM_RIGHT);
        this.add(intersection_35);
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
        intersection_27.choosePosition(Intersection.Position.BOTTOM_LEFT);
        this.add(intersection_27);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        intersection_26.choosePosition(Intersection.Position.MIDDLE);
        this.add(intersection_26);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        intersection_25.choosePosition(Intersection.Position.BOTTOM_RIGHT);
        this.add(intersection_25);
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

        intersection_17.choosePosition(Intersection.Position.BOTTOM_LEFT);
        this.add(intersection_17);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        intersection_16.choosePosition(Intersection.Position.BOTTOM_MIDDLE);
        this.add(intersection_16);
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        this.add(new HorizontalLine());
        intersection_15.choosePosition(Intersection.Position.BOTTOM_RIGHT);
        this.add(intersection_15);

        this.intersectionList = new ArrayList<>(); // create a list to store intersections
        // Add all intersections to the list
        intersectionList.add(intersection_11);
        intersectionList.add(intersection_12);
        intersectionList.add(intersection_13);
        intersectionList.add(intersection_21);
        intersectionList.add(intersection_22);
        intersectionList.add(intersection_23);
        intersectionList.add(intersection_31);
        intersectionList.add(intersection_32);
        intersectionList.add(intersection_33);
        intersectionList.add(intersection_18);
        intersectionList.add(intersection_28);
        intersectionList.add(intersection_38);
        intersectionList.add(intersection_34);
        intersectionList.add(intersection_24);
        intersectionList.add(intersection_14);
        intersectionList.add(intersection_37);
        intersectionList.add(intersection_36);
        intersectionList.add(intersection_35);
        intersectionList.add(intersection_27);
        intersectionList.add(intersection_26);
        intersectionList.add(intersection_25);
        intersectionList.add(intersection_17);
        intersectionList.add(intersection_16);
        intersectionList.add(intersection_15);

        indexLookUpTable = new HashMap<>();
        indexLookUpTable.put(11, 0);
        indexLookUpTable.put(12, 6);
        indexLookUpTable.put(13, 12);
        indexLookUpTable.put(14, 90);
        indexLookUpTable.put(15, 168);
        indexLookUpTable.put(16, 162);
        indexLookUpTable.put(17, 156);
        indexLookUpTable.put(18, 78);
        indexLookUpTable.put(21, 28);
        indexLookUpTable.put(22, 32);
        indexLookUpTable.put(23, 36);
        indexLookUpTable.put(24, 88);
        indexLookUpTable.put(25, 140);
        indexLookUpTable.put(26, 136);
        indexLookUpTable.put(27, 132);
        indexLookUpTable.put(28, 80);
        indexLookUpTable.put(31, 56);
        indexLookUpTable.put(32, 58);
        indexLookUpTable.put(33, 60);
        indexLookUpTable.put(34, 86);
        indexLookUpTable.put(35, 112);
        indexLookUpTable.put(36, 110);
        indexLookUpTable.put(37, 108);
        indexLookUpTable.put(38, 82);
    }

    /**
     * get the index of intersection in the panel (from 0 to 168)
     *
     * @param intersection is the place where the user can place or move token
     * @return the index of intersection in the panel
     */
    public int getIntersectionPanelIndex(Intersection intersection) {
        return intersection.getAccessibleContext().getAccessibleIndexInParent();
    }

    /**
     * get the intersection in the panel by coordinate
     *
     * @param x is the layer coordinate
     * @param y is the position coordinate
     * @return the intersection in the panel
     */
    public Intersection getIntersectionByCoordinate(int x, int y) {
        for (Intersection intersection : getIntersectionList()) {
            if (intersection.getCoordinateX() == x && intersection.getCoordinateY() == y) {
                return intersection;
            }
        }
        return null;
    }

    public Integer getIndexLookUpTable(int layer, int position) {
        String combine = String.valueOf(layer) + String.valueOf(position);
        int combine1 = Integer.parseInt(combine);

//        for (int i = 1; i <= 38; i++) {
//            if (i == Integer.parseInt(combine)){
//                return indexLookUpTable.get(i);
//            }
//        }
        return indexLookUpTable.get(combine1);
    }

}
