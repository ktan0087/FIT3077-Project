package Frontend;

import javax.swing.*;

public class WhiteWin extends Win{
    private ImageIcon whiteToken;

    // Constructor
    public WhiteWin(){
        super();
        this.whiteToken = new ImageIcon("Frontend/Icons/white-token.png");
        this.setIcon(whiteToken);
    }
}
