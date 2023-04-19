import javax.swing.*;
import java.awt.*;

public class PlaceToken extends JPanel {
    public PlaceToken(){
        this.setOpaque(false); // set the background of this panel transparent
        this.setPreferredSize(new Dimension(500,500)); // set the size of the board

        // set the layout of the board, 13 rows and 13 columns
        this.setLayout(new GridLayout(13, 13, -1, -1)); // -1 is to remove the space between intersections and lines

        // create placeholder
        for (int i = 0; i < 169; i++) {
            this.add(new JLabel());
        }

    }
}
