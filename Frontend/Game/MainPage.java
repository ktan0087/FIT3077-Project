package Frontend.Game;

import Frontend.Button.BtnPlay;
import Frontend.Button.BtnTutorial;

import javax.swing.*;
import java.awt.*;

/**
 * A class to represent the main page of the game.
 * The main page includes the game title, play button and tutorial button.
 *
 * @see BtnPlay
 * @see BtnTutorial
 */

public class MainPage extends JLayeredPane{
    /**
     * The game title
     */
    private JLabel gameName;

    /**
     * The play button
     */
    protected BtnPlay btnPlay;

    /**
     * The tutorial button
     */
    protected BtnTutorial btnTut;

    /**
     * The background of the main page
     */
    private JLabel background;

    /**
     * Constructor for the main page.
     * Creates a new main page with a game title, play button and tutorial button.
     */
    public MainPage(){
        this.gameName = new JLabel();
        this.btnPlay = new BtnPlay();
        this.btnTut = new BtnTutorial();
        this.background=new JLabel(new ImageIcon(getClass().getResource("/Icons/background.png")));

        this.setBackground(new Color(0xE0A060));
        this.setOpaque(true);

        // Add Game Title
        gameName.setFont(new Font("Inter", Font.BOLD, 100));
        gameName.setText("<html>NINE MEN'S<br/><center>MORRIS<center></html>");
        gameName.setForeground(new Color(0x000000));

        // Set layout of main page
        setLayout(new BorderLayout());

        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // make the box of buttons same
        gbc.insets = new Insets(10, 10, 10, 10); // add gaps between the components

        gbc.gridx = 0;
        gbc.gridy = 0;
        background.add(gameName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        background.add(btnPlay, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        background.add(btnTut, gbc);

        this.add(background);
    }

}
