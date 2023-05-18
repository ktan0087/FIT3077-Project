package Frontend.Game;

import Frontend.Button.BtnClose;
import Frontend.Button.BtnRestart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultButtons extends JPanel {
    protected BtnRestart btnRestart;
    protected BtnClose btnClose;
    public ResultButtons(){
        btnRestart = new BtnRestart();
        btnClose = new BtnClose();

        this.setOpaque(false); // set the background of this panel transparent

        this.setLayout(new GridBagLayout()); // set the layout of this panel
        GridBagConstraints gbc = new GridBagConstraints(); // create a GridBagConstraint object
        gbc.insets = new Insets(5, 25, 5, 25); // add gaps between the components

        gbc.gridx = 0;
        gbc.gridy = 0;
        btnRestart.setBackground(new Color(0xF4E3D3));
        btnRestart.setBorder(BorderFactory.createLineBorder(new Color(0xE27408), 5));
        btnRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close the result page
                SwingUtilities.getWindowAncestor(ResultButtons.this).dispose();
            }
        });
        this.add(btnRestart, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        btnClose.setBackground(new Color(0xF4E3D3));
        btnClose.setBorder(BorderFactory.createLineBorder(new Color(0xE27408), 5));
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close the result page
                SwingUtilities.getWindowAncestor(ResultButtons.this).dispose();
            }
        });
        this.add(btnClose, gbc);
    }
}
