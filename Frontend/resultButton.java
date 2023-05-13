package Frontend;

import javax.swing.*;
import java.awt.*;

public class resultButton extends JPanel {
    protected BtnRestart btnRestart = new BtnRestart();
    protected BtnClose btnClose = new BtnClose();
    public resultButton(){
        this.setOpaque(false); // set the background of this panel transparent

        this.setLayout(new GridBagLayout()); // set the layout of this panel
        GridBagConstraints gbc = new GridBagConstraints(); // create a GridBagConstraint object
        gbc.insets = new Insets(5, 25, 5, 25); // add gaps between the components

        gbc.gridx = 0;
        gbc.gridy = 0;
        btnRestart.setBackground(new Color(0xF4E3D3));
        btnRestart.setBorder(BorderFactory.createLineBorder(new Color(0xE27408), 5));
        this.add(btnRestart, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        btnClose.setBackground(new Color(0xF4E3D3));
        btnClose.setBorder(BorderFactory.createLineBorder(new Color(0xE27408), 5));
        this.add(btnClose, gbc);
    }
}
