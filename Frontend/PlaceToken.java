package Frontend;

import javax.swing.*;
import java.awt.*;

public class PlaceToken extends JPanel {
    /**
     * This class is used to place the token on the board
     */

    // Constructor
    public PlaceToken(){
        this.setOpaque(false); // set the background of this panel transparent
        this.setPreferredSize(new Dimension(500,500)); // set the size of the board

        // set the layout, the layout must be the same as board (13 rows and 13 columns)
        this.setLayout(new GridLayout(13, 13, -1, -1)); // -1 is to remove the space between intersections and lines

        // create placeholder
        for (int i = 0; i < 169; i++) {
            this.add(new JLabel());
        }

    }
}
