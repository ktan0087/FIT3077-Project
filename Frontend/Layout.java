package Frontend;

// REFERENCE: https://www.youtube.com/watch?v=sAReaaTxNGU


import Backend.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Layout{
    /**
     * This class is used to switch between different panels (MainPage and InitialBoard)
     */
    JFrame mainFrame = new MainFrame();
    JPanel panelCont = new JPanel(); // create Panel Container to add all panels inside
    MainPage mainPage = new MainPage();
    InitialBoard iniBoard = new InitialBoard();
    CardLayout cLayout = new CardLayout(); // create card layout
    Game g1;

    /**
     * This method is used to set the game that is played
     * @param g1 is the game that is played
     */
    public void setGame(Game g1) {
        this.g1 = g1;
    }

    /**
     * This method is used to get the game that is played
     * @return the current game that is played
     */
    public Game getGame() {
        return g1;
    }

    /**
     * This method is used to set the initial board
     * @param iniBoard is the initial board
     */
    public void setIniBoard(InitialBoard iniBoard) {
        this.iniBoard = iniBoard;
    }

    /**
     * This method is used to get the initial board
     * @return the current initial board
     */
    public InitialBoard getIniBoard() {
        return iniBoard;
    }

    // Constructor
    public Layout() {
        panelCont.setLayout(cLayout); // set card layout for panel container

        panelCont.add(mainPage, "1"); // add main page to panel container
        panelCont.add(iniBoard, "2"); // add initial board to panel container
        cLayout.show(panelCont, "1"); // show main page first

        // Click PLAY button and bring the user to start the game (InitialBoard)
        mainPage.btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cLayout.show(panelCont, "2");
                setGame(new Game());
                iniBoard.setGame(getGame());
            }

        });

        iniBoard.buttons.btnRestart.addActionListener(restart); // make RESTART button work
        iniBoard.buttons.btnClose.addActionListener(close); // make CLOSE button work

        mainFrame.add(panelCont); // add panel container to main frame
    }

    // Click RESTART button in InitialBoard and restart the game
    protected ActionListener restart = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = "Do you want to restart the game?";
            String title = "NINE MEN'S MORRIS";
            int result = JOptionPane.showConfirmDialog(iniBoard.buttons.btnRestart, message, title, JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION){
                panelCont.remove(getIniBoard()); // remove old initial board
                InitialBoard newIniBoard = new InitialBoard(); // create new initial board
                setIniBoard(newIniBoard); // set new initial board
                panelCont.add(getIniBoard(), "2"); // add new initial board to panel container
                iniBoard.buttons.btnRestart.addActionListener(restart); // add action listener to RESTART button, otherwise the button won't work after restart
                iniBoard.buttons.btnClose.addActionListener(close); // add action listener to CLOSE button
                cLayout.show(panelCont, "2"); // show initial board
            }
        }
    };

    // Click CLOSE icon button in InitialBoard and bring the user to main page (MainPage)
    protected ActionListener close = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = "Do you want to exit?";
            String title = "NINE MEN'S MORRIS";
            int result = JOptionPane.showConfirmDialog(iniBoard.buttons.btnClose, message, title, JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION){
                panelCont.remove(getIniBoard());
                InitialBoard newIniBoard = new InitialBoard();
                setIniBoard(newIniBoard);
                panelCont.add(getIniBoard(), "2");
                iniBoard.buttons.btnClose.addActionListener(close);
                iniBoard.buttons.btnRestart.addActionListener(restart);
                cLayout.show(panelCont, "1"); // show main page
            }
        }
    };
}
