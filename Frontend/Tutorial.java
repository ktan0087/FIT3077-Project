package Frontend;

import Frontend.Game.InitialBoard;

import javax.swing.*;
import java.awt.*;

public class Tutorial extends JPanel {
    InitialBoard background;
    public Tutorial(){
        this.background = new InitialBoard();
        this.background.setOpaque(false);
        this.background.setEnabled(false);
    }
}
