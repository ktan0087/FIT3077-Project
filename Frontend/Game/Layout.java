// Lazic B. (10 Mar 2013). Java swing GUI tutorial #18: CardLayout.
// Retrieved from https://www.youtube.com/watch?v=sAReaaTxNGU

package Frontend.Game;

import Backend.Game;
import Frontend.Frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class to represent the layout of the game.
 * A layout can have a main frame, a panel container, a main page, an initial board, and a card layout.
 *
 * @see MainFrame
 */

public class Layout{
    /**
     * The main frame of the game
     */
    private JFrame mainFrame;

    /**
     * The panel container of the game
     */
    private JPanel panelCont; // create Panel Container to add all panels inside

    /**
     * The main page of the game
     */
    private MainPage mainPage;

    /**
     * The initial board of the game
     */
    private InitialBoard iniBoard;

    private Tutorial tutorial;

    /**
     * The card layout of the game
     */
    private CardLayout cLayout; // create card layout

    /**
     * The game that is played
     */
    private Game game;

    /**
     * This method is used to set the game that is played
     *
     * @param game is the game that is played
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * This method is used to get the game that is played
     *
     * @return the current game that is played
     */
    public Game getGame() {
        return game;
    }

    /**
     * This method is used to set the initial board
     *
     * @param iniBoard is the initial board
     */
    public void setIniBoard(InitialBoard iniBoard) {
        this.iniBoard = iniBoard;
    }

    /**
     * This method is used to get the initial board
     *
     * @return the current initial board
     */
    public InitialBoard getIniBoard() {
        return iniBoard;
    }

    /**
     * Constructor.
     * Creates a layout of the game.
     */
    public Layout() {
        this.mainFrame = new MainFrame();
        this.panelCont = new JPanel(); // create Panel Container to add all panels inside
        this.mainPage = new MainPage();
        this.iniBoard = new InitialBoard();
        this.tutorial = new Tutorial();
        this.cLayout = new CardLayout(); // create card layout

        panelCont.setLayout(cLayout); // set card layout for panel container

        panelCont.add(mainPage, "1"); // add main page to panel container
        panelCont.add(iniBoard, "2"); // add initial board to panel container
        panelCont.add(tutorial, "3");
        cLayout.show(panelCont, "1"); // show main page first

        mainPage.btnPlay.addActionListener(play); // make PLAY button work
        mainPage.btnTut.addActionListener(startTutorial);
        iniBoard.buttons.btnRestart.addActionListener(restart); // make RESTART button work
        iniBoard.buttons.btnClose.addActionListener(close); // make CLOSE button work
        iniBoard.resultButton.btnRestart.addActionListener(getRestart);
        iniBoard.resultButton.btnClose.addActionListener(getClose);
        tutorial.btnClose.addActionListener(close);

        mainFrame.add(panelCont); // add panel container to main frame
    }

    protected ActionListener startTutorial = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cLayout.show(panelCont, "3");
            setGame(new Backend.Tutorial());
            tutorial.getInitialBoard().setGame(getGame());
            System.out.println(iniBoard.getGame());
        }
    };

    protected ActionListener play = new ActionListener() {
        /**
         * This method is used to bring the user to start the game (InitialBoard)
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            cLayout.show(panelCont, "2"); // show initial board
            setGame(new Game()); // create new game
            iniBoard.setGame(getGame());
            System.out.println(iniBoard.getGame());
        }
    };

    protected ActionListener restart = new ActionListener() {
        /**
         * This method is used to restart the game
         * and it will show a confirmation dialog to ask the user whether they intend to restart
         *
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
         * and it will show a confirmation dialog to ask the user whether they intend to exit
         *
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

    /**
     * This method is used to restart the game
     */
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

    /**
     * This method is used to exit the game (bring the user to main page (MainPage))
     */
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
