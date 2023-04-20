package Frontend;
// REFERENCE: https://www.youtube.com/watch?v=sAReaaTxNGU

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Use CardLayout to switch between different panels (MainPage and InitialBoard)
public class Layout implements ActionListener{
    JFrame mainFrame = new MainFrame();
    JPanel panelCont = new JPanel(); // create Panel Container to add all panels inside
    MainPage mainPage = new MainPage();
    InitialBoard iniBoard = new InitialBoard();
    CardLayout cLayout = new CardLayout(); // create card layout

    public void setIniBoard(InitialBoard iniBoard) {
        this.iniBoard = iniBoard;
    }

    public InitialBoard getIniBoard() {
        return this.iniBoard;
    }

    public Layout() {
        panelCont.setLayout(cLayout); // set card layout for panel container

        panelCont.add(mainPage, "1"); // add main page to panel container
        panelCont.add(iniBoard, "2"); // add initial board to panel container
        cLayout.show(panelCont, "1"); // show main page first

//        // Click PLAY button and bring the user to start the game (InitialBoard)
//        mainPage.btnPlay.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                cLayout.show(panelCont, "2");
//            }
//        });

//        // Click RESTART button in InitialBoard and restart the game
//        iniBoard.buttons.btnRestart.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String message = "Do you want to restart the game?";
//                String title = "NINE MEN'S MORRIS";
//                int result = JOptionPane.showConfirmDialog(iniBoard.buttons.btnRestart, message, title, JOptionPane.YES_NO_OPTION);
//                if (result == JOptionPane.YES_OPTION){
//                    panelCont.remove(iniBoard); // remove old initial board
//                    iniBoard = new InitialBoard(); // create new initial board
//                    panelCont.add(iniBoard, "2"); // add new initial board to panel container
//                    iniBoard.buttons.btnRestart.addActionListener(this); // add action listener to RESTART button, otherwise the button won't work after restart
//                    cLayout.show(panelCont, "2"); // show initial board
//                }
//            }
//        });
//
//        // Click CLOSE icon button in InitialBoard and bring the user to main page (MainPage)
//        iniBoard.buttons.btnClose.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String message = "Do you want to exit?";
//                String title = "NINE MEN'S MORRIS";
//                int result = JOptionPane.showConfirmDialog(iniBoard.buttons.btnRestart, message, title, JOptionPane.YES_NO_OPTION);
//                if (result == JOptionPane.YES_OPTION){
//                    panelCont.remove(iniBoard);
//                    iniBoard = new InitialBoard();
//                    panelCont.add(iniBoard, "2");
//                    iniBoard.buttons.btnClose.addActionListener(this);
//                    cLayout.show(panelCont, "1");
//                }
//
//            }
//        });

        mainPage.btnPlay.addActionListener(this);
        iniBoard.buttons.btnRestart.addActionListener(this);
        iniBoard.buttons.btnClose.addActionListener(this);
        mainFrame.add(panelCont); // add panel container to main frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Layout();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainPage.btnPlay){
            cLayout.show(panelCont, "2");
        }

        // Click RESTART button in InitialBoard and restart the game
        else if (e.getSource() == iniBoard.buttons.btnRestart){
            String message = "Do you want to restart the game?";
            String title = "NINE MEN'S MORRIS";
            int result = JOptionPane.showConfirmDialog(iniBoard.buttons.btnRestart, message, title, JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION){
                panelCont.remove(iniBoard); // remove old initial board
                iniBoard = new InitialBoard(); // create new initial board
                panelCont.add(iniBoard, "2"); // add new initial board to panel container
                iniBoard.buttons.btnRestart.addActionListener(this); // add action listener to RESTART button, otherwise the button won't work after restart
                iniBoard.buttons.btnClose.addActionListener(this); // add action listener to CLOSE button
                cLayout.show(panelCont, "2"); // show initial board

//                panelCont.remove(getIniBoard());
//                InitialBoard newIniBoard = new InitialBoard();
//                setIniBoard(newIniBoard);
//                panelCont.add(getIniBoard(), "2");
//                getIniBoard().buttons.btnRestart.addActionListener(this);
//                cLayout.show(panelCont, "2");
            }
        }

        // Click CLOSE icon button in InitialBoard and bring the user to main page (MainPage)
        else if (e.getSource() == iniBoard.buttons.btnClose){
            String message = "Do you want to exit?";
            String title = "NINE MEN'S MORRIS";
            int result = JOptionPane.showConfirmDialog(iniBoard.buttons.btnRestart, message, title, JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION){
                panelCont.remove(iniBoard);
                iniBoard = new InitialBoard();
                panelCont.add(iniBoard, "2");
                iniBoard.buttons.btnClose.addActionListener(this);
                iniBoard.buttons.btnRestart.addActionListener(this);
                cLayout.show(panelCont, "1");

//                panelCont.remove(getIniBoard());
//                InitialBoard newIniBoard = new InitialBoard();
//                setIniBoard(newIniBoard);
//                panelCont.add(getIniBoard(), "2");
//                getIniBoard().buttons.btnClose.addActionListener(this);
//                cLayout.show(panelCont, "1");
            }
        }
    }
}
