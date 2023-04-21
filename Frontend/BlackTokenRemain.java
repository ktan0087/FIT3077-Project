package Frontend;

import javax.swing.*;
import java.awt.*;

// Used to display the number of remaining black tokens
public class BlackTokenRemain extends TokenRemain {
    ImageIcon blackToken = new ImageIcon("Frontend/Icons/black-token.png"); // import the image of black token

    public BlackTokenRemain(){
        super();
        this.setIcon(blackToken);
    }
}
