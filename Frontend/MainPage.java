package Frontend;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JLayeredPane{
    /**
     * Customized the main page and this page is the first page that user will see
     */
    private JLabel gameName;
    protected BtnPlay btnPlay;
    private BtnTutorial btnTut;
    private JLabel background;

    // Constructor
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
