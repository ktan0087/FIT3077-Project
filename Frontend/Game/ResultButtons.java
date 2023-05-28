package Frontend.Game;

import Frontend.Button.BtnClose;
import Frontend.Button.BtnRestart;
import Frontend.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class to represent the buttons on the result page.
 * The buttons are restart and close.
 * The reason of create this class, instead of adding the buttons directly in result page, is to prevent disalignment
 * between these two buttons.
 *
 * @see BtnRestart
 * @see BtnClose
 */
public class ResultButtons extends JPanel {
    /**
     * The restart button
     */
    protected BtnRestart btnRestart;

    /**
     * The close button
     */
    protected BtnClose btnClose;

    /**
     * Constructor.
     * Create the restart and close buttons and add them to this panel.
     */
    public ResultButtons(){
        btnRestart = new BtnRestart();
        btnClose = new BtnClose();

        this.setOpaque(false); // set the background of this panel transparent

        this.setLayout(new GridBagLayout()); // set the layout of this panel
        GridBagConstraints gbc = new GridBagConstraints(); // create a GridBagConstraint object
        Size gapSize = new Size(25, 5);
        gbc.insets = new Insets(gapSize.getHeight(), gapSize.getWidth(), gapSize.getHeight(), gapSize.getWidth()); // add gaps between the components

        gbc.gridx = 0; // set the x position of the component
        gbc.gridy = 0; // set the y position of the component
        btnRestart.setBackground(new Color(0xF4E3D3)); // set the background color of the button
        btnRestart.setBorder(BorderFactory.createLineBorder(new Color(0xE27408), 5)); // set the border of the button

        /**
         * Add action listener to the restart button to close the result page.
         */
        btnRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close the result page
                SwingUtilities.getWindowAncestor(ResultButtons.this).dispose(); // close the result page
            }
        });
        this.add(btnRestart, gbc); // add the restart button to this panel

        gbc.gridx = 1;
        gbc.gridy = 0;
        btnClose.setBackground(new Color(0xF4E3D3));
        btnClose.setBorder(BorderFactory.createLineBorder(new Color(0xE27408), 5));

        /**
         * Add action listener to the close button to close the result page.
         */
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close the result page
                SwingUtilities.getWindowAncestor(ResultButtons.this).dispose(); // close the result page
            }
        });
        this.add(btnClose, gbc);
    }
}
