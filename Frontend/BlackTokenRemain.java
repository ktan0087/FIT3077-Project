package Frontend;

import javax.swing.*;

public class BlackTokenRemain extends TokenRemain {
    /**
     * Display the number of remaining black tokens
     */
    ImageIcon blackToken = new ImageIcon("Frontend/Icons/black-token.png"); // import the image of black token

    // Constructor
    public BlackTokenRemain(){
        super();
        this.setIcon(blackToken);
    }
}
