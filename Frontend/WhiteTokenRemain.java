package Frontend;

import javax.swing.*;

public class WhiteTokenRemain extends TokenRemain {
    /**
     * Customized label for White Token Remain
     */
    private ImageIcon whiteToken;

    // Constructor
    public WhiteTokenRemain(){
        super();
        this.whiteToken = new ImageIcon(getClass().getResource("/Icons/white-token.png"));
        this.setIcon(whiteToken);
    }
}
