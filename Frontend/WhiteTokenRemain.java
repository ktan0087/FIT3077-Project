package Frontend;

import javax.swing.*;
import java.awt.*;

// Customized label for White Token Remain
public class WhiteTokenRemain extends TokenRemain {
    ImageIcon whiteToken = new ImageIcon("Frontend/Icons/white-token.png");

    public WhiteTokenRemain(){
        super();
        this.setIcon(whiteToken);
    }
}
