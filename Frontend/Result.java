package Frontend;

import javax.swing.*;
import java.awt.*;

public class Result extends JDialog {
    protected ResultButton resultButton = new ResultButton();
    private Win win;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // get the screen size

    public Result(Win.WhoWin winner) {
        // result screen cannot be closed unless press the 2 buttons (restart or close)
        super(new JFrame(), true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        // set a dimmed and transparent background for the result page
        this.setPreferredSize(screenSize);
        this.getRootPane().setOpaque (false);
        this.getContentPane().setBackground (new Color (0, 0, 0, 0));
        this.setBackground(new Color(0x80000000, true));

        // arrange the components in result page
        this.setLayout(new GridBagLayout()); // set the layout of this panel
        GridBagConstraints gbc = new GridBagConstraints(); // create a GridBagConstraint object
        gbc.insets = new Insets(5, 5, 5, 5); // add gaps between the components

        // Display which player wins
        gbc.gridx = 0; // set the x position of the component
        gbc.gridy = 0; // set the y position of the component
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // set the position of the component
        win = new Win(winner);
        this.add(win, gbc); // add the component to this panel

        // Display the restart and close buttons
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(resultButton, gbc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}