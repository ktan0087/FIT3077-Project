package Frontend;
import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// This is the InitialBoard that players can see when they start the game
public class InitialBoard extends JPanel {
    ArrayList<WhiteToken> whiteList = new ArrayList<>(); // create a list to store white tokens
    Buttons buttons = new Buttons();
    Board board = new Board();
    PlaceToken placeToken = new PlaceToken();
    PlayerTurn playerTurn = new PlayerTurn();
    WhiteTokenRemain whiteTokenRemain = new WhiteTokenRemain();
    BlackTokenRemain blackTokenRemain = new BlackTokenRemain();
    WhiteToken selectedWhite;
    Boolean alwaysTrue = true;

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

        int btnListLength = board.getIntersectionList().size(); // get the length of the intersection list

        // Move token on board

        // Place token on board
        for (int i = 0; i < btnListLength; i++) {
            Intersection intersection = board.getIntersectionList().get(i); // get each intersection from list
            intersection.inter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    WhiteToken whiteToken = new WhiteToken(intersection.getCoordinateX(), intersection.getCoordinateY()); // create a white token
                    int index = intersection.getAccessibleContext().getAccessibleIndexInParent(); // get the index of the intersection from the list

                    if (checkSelected()){
                        if (true) {
                            // remove token from board
                            int selectedWhiteIndex = selectedWhite.index; // get the index of the selected white token
                            placeToken.add(selectedWhite, index); // add the selected white token to the intersection that the player wants to move
                            placeToken.remove(selectedWhiteIndex); // remove the selected white token from the previous intersection
                            placeToken.add(new JLabel(), selectedWhiteIndex); // add the placeholder to the previous intersection at place token layer
                            selectedWhite.selected = false; // remove the red selected border
                            placeToken.repaint();
                            placeToken.revalidate();
                            return;
                        }
                    }

                    // place token on board
                    placeToken.remove(index); // remove the placeholder at the same index
                    placeToken.add(whiteToken, index); // add white token at the same index
                    placeToken.repaint();
                    placeToken.revalidate();
                    whiteToken.index = index; // set the index of the white token
                    whiteList.add(whiteToken); // add white token to the list
                    if (!checkSelected()) { // if no white token is selected
                        whiteToken.addMouseListener(whiteToken.tokenSelected);
                        System.out.println("Selected here");
                    }

                    // **Test Backend code**
                    DoNothingAction doNothingAction = new DoNothingAction();
                    System.out.println(doNothingAction.execute(new Player("Player 1", TokenColour.PLAYER_1_WHITE)));
                }
            });

        }

    }

    // check if any white token is selected
    protected boolean checkSelected(){
        for (WhiteToken whiteToken : whiteList) {
            whiteToken.removeMouseListener(whiteToken.tokenSelected);
            if (whiteToken.selected){
                whiteToken.addMouseListener(whiteToken.tokenSelected);
                selectedWhite = whiteToken; // set selected white token
                System.out.println("selected");
                return true;
            }
        }
        System.out.println("not selected");
        return false;
    }

    // Place token on board
    protected void placeToken(WhiteToken whiteToken) {
        placeToken.remove(whiteToken.index);
        placeToken.add(whiteToken, whiteToken.index);
        placeToken.repaint();
        placeToken.revalidate();
        whiteList.add(whiteToken);
    }

    // Remove token from board
    protected void removeToken(WhiteToken whiteToken) {
        placeToken.remove(whiteToken.index);
        placeToken.repaint();
        placeToken.revalidate();
        whiteList.remove(whiteToken);
    }

    // Move token on board
    protected void moveToken(WhiteToken whiteToken, Intersection intersection) {
        placeToken.remove(whiteToken.index);
        int intersectionIndex = intersection.getAccessibleContext().getAccessibleIndexInParent();
        placeToken.add(whiteToken, intersectionIndex);
        placeToken.repaint();
        placeToken.revalidate();
    }

}
