package Frontend.Layer;

import javax.swing.*;
import java.awt.*;

/**
 * A class to represent the place token panel
 */

public class PlaceToken extends JPanel {
    /**
     * Constructor.
     * Create a panel to place token.
     */
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
