package Frontend;

import javax.swing.*;

public class WhiteTokenRemain extends TokenRemain {
    /**
     * Customized label for White Token Remain
     */
    ImageIcon whiteToken = new ImageIcon("Frontend/Icons/white-token.png");

    // Constructor
    public WhiteTokenRemain(){
        super();
        this.setIcon(whiteToken);
    }
}
