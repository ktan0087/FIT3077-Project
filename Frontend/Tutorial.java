package Frontend;

import Frontend.Game.InitialBoard;

import javax.swing.*;
import java.awt.*;

public class Tutorial extends InitialBoard {
    private JPanel dimLayer = new JPanel();
    public Tutorial(){
        this.setOpaque(true);
        this.setEnabled(false);

        this.dimLayer.setBackground(new Color(0x80000000, true));
        this.dimLayer.setOpaque(false);

//        this.add(this.dimLayer, BorderLayout.CENTER);

    }
}
