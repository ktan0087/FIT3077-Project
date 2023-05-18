// Lazic B. (10 Mar 2013). Java swing GUI tutorial #18: CardLayout.
// Retrieved from https://www.youtube.com/watch?v=sAReaaTxNGU

package Frontend;

import Backend.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Layout{
    /**
     * This class is used to switch between different panels (MainPage and InitialBoard)
     */
    private JFrame mainFrame;
    private JPanel panelCont; // create Panel Container to add all panels inside
    private MainPage mainPage;
    private InitialBoard iniBoard;
    private CardLayout cLayout; // create card layout
    private Game game;

    /**
     * This method is used to set the game that is played
     * @param game is the game that is played
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * This method is used to get the game that is played
     * @return the current game that is played
     */
    public Game getGame() {
        return game;
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
        this.mainFrame = new MainFrame();
        this.panelCont = new JPanel(); // create Panel Container to add all panels inside
        this.mainPage = new MainPage();
        this.iniBoard = new InitialBoard();
        this.cLayout = new CardLayout(); // create card layout

        panelCont.setLayout(cLayout); // set card layout for panel container

        panelCont.add(mainPage, "1"); // add main page to panel container
        panelCont.add(iniBoard, "2"); // add initial board to panel container
        cLayout.show(panelCont, "1"); // show main page first

        mainPage.btnPlay.addActionListener(play); // make PLAY button work
        iniBoard.buttons.btnRestart.addActionListener(restart); // make RESTART button work
        iniBoard.buttons.btnClose.addActionListener(close); // make CLOSE button work
        iniBoard.resultButton.btnRestart.addActionListener(getRestart);
        iniBoard.resultButton.btnClose.addActionListener(getClose);

        mainFrame.add(panelCont); // add panel container to main frame
    }

    protected ActionListener play = new ActionListener() {
        /**
         * This method is used to bring the user to start the game (InitialBoard)
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            cLayout.show(panelCont, "2"); // show initial board
            setGame(new Game()); // create new game
            iniBoard.setGame(getGame());
        }
    };

    protected ActionListener restart = new ActionListener() {
        /**
         * This method is used to restart the game
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = "Do you want to restart the game?";
            String title = "NINE MEN'S MORRIS";
            int result = JOptionPane.showConfirmDialog(iniBoard.buttons.btnRestart, message, title, JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION){
                getRestart.actionPerformed(e);
            }
        }
    };

    protected ActionListener close = new ActionListener() {
        /**
         * This method is used to exit the game (bring the user to main page (MainPage))
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = "Do you want to exit?";
            String title = "NINE MEN'S MORRIS";
            int result = JOptionPane.showConfirmDialog(iniBoard.buttons.btnClose, message, title, JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION){
                getClose.actionPerformed(e);
            }
        }
    };

    protected ActionListener getRestart = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panelCont.remove(getIniBoard()); // remove old initial board
            InitialBoard newIniBoard = new InitialBoard(); // create new initial board
            setIniBoard(newIniBoard); // set new initial board
            getGame().restartGame();
            iniBoard.setGame(getGame()); // set game for new initial board
            panelCont.add(getIniBoard(), "2"); // add new initial board to panel container
            iniBoard.buttons.btnRestart.addActionListener(restart); // add action listener to RESTART button, otherwise the button won't work after restart
            iniBoard.buttons.btnClose.addActionListener(close); // add action listener to CLOSE button
            iniBoard.resultButton.btnRestart.addActionListener(getRestart);
            iniBoard.resultButton.btnClose.addActionListener(getClose);
            cLayout.show(panelCont, "2"); // show initial board
        }
    };

    protected ActionListener getClose = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            panelCont.remove(getIniBoard());
            InitialBoard newIniBoard = new InitialBoard();
            setIniBoard(newIniBoard);
            getGame().restartGame();
            iniBoard.setGame(getGame());
            panelCont.add(getIniBoard(), "2");
            iniBoard.buttons.btnClose.addActionListener(close);
            iniBoard.buttons.btnRestart.addActionListener(restart);
            iniBoard.resultButton.btnClose.addActionListener(getClose);
            iniBoard.resultButton.btnRestart.addActionListener(getRestart);
            cLayout.show(panelCont, "1"); // show main page
        }
    };

}
