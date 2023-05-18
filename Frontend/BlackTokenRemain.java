package Frontend;

import javax.swing.*;

public class BlackTokenRemain extends TokenRemain {
    /**
     * Display the number of remaining black tokens
     */
    private ImageIcon blackToken;
    // Constructor
    public BlackTokenRemain(){
        super();
        this.blackToken = new ImageIcon(getClass().getResource(("/Icons/black-token.png"))); // import the image of black token
        this.setIcon(blackToken);
    }
}
