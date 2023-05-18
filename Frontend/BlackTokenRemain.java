package Frontend;

import javax.swing.*;

public class BlackTokenRemain extends TokenRemain {
    /**
     * Display the number of remaining black tokens
     */
    private ImageIcon blackToken = new ImageIcon(getClass().getResource(("/Icons/black-token.png"))); // import the image of black token

    // Constructor
    public BlackTokenRemain(){
        super();
        this.setIcon(blackToken);
    }
}
