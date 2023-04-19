import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This is the InitialBoard that players can see when they start the game
public class InitialBoard extends JPanel {
    Buttons buttons = new Buttons();
    Board board = new Board();
    PlaceToken placeToken = new PlaceToken();
    PlayerTurn playerTurn = new PlayerTurn();
    WhiteTokenRemain whiteTokenRemain = new WhiteTokenRemain();
    BlackTokenRemain blackTokenRemain = new BlackTokenRemain();

    public InitialBoard() {
        // set the background color of the board
        this.setBackground(new Color(0xE0A060));
        this.setOpaque(true);

        // Set layout of Buttons in board
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // add gaps between the components

        gbc.gridx = 2; // set the x position of the component
        gbc.gridy = 0; // set the y position of the component
        gbc.weightx = 0; // horizontal spacing (use values from 0.0 to 1.0)
        gbc.weighty = 0; // vertical spacing
        gbc.anchor = GridBagConstraints.NORTHEAST; // set the position of the component
        this.add(buttons, gbc); // add the component to this panel

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(placeToken, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(board, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        this.add(playerTurn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(whiteTokenRemain, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(blackTokenRemain, gbc);

        // Place token on board
        int btnListLength = board.getIntersectionList().size(); // get the length of the intersection list
        for (int i = 0; i < btnListLength; i++) {
            Intersection intersection = board.getIntersectionList().get(i); // get each intersection from list
            intersection.inter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    WhiteToken whiteToken = new WhiteToken(); // create a white token
                    int index = intersection.getAccessibleContext().getAccessibleIndexInParent(); // get the index of the intersection from the list
//                    board.remove(index); // remove the intersection from the board (by selecting the index in list)
//                    board.add(whiteToken, index); // add white token at the same index
//                    board.repaint(); // repaint the board
//                    board.revalidate(); // revalidate the board

//                    intersection.isWhite = true;
//                    board.whiteList.add(index, whiteToken);

                    placeToken.remove(index);
                    placeToken.add(whiteToken, index); // add white token at the same index
                    placeToken.repaint();
                    placeToken.revalidate();

                }
            });

        }

//        // Select token on board
//        int whiteListLength = board.whiteList.size();
//        for (int i = 0; i < whiteListLength; i++){
//
//        }

    }
}
