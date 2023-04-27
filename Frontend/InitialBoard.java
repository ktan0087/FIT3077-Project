package Frontend;
import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InitialBoard extends JPanel {
    /**
     * The initial board that players can see when they start the game
     */
    ArrayList<Token> whiteList = new ArrayList<>(); // create a list to store white tokens
    Buttons buttons = new Buttons(); // buttons that illustrate hint, restart, close
    Frontend.Board board = new Board(); // create a board
    PlaceToken placeToken = new PlaceToken(); // create a layer to place tokens
    PlayerTurn playerTurn = new PlayerTurn(); // show which player's turn
    WhiteTokenRemain whiteTokenRemain = new WhiteTokenRemain(); // show the remaining number of white tokens
    BlackTokenRemain blackTokenRemain = new BlackTokenRemain(); // show the remaining number of black tokens
    Token selectedToken; // the token that is selected by the player
    boolean isSelected; // whether the player has selected a token
    Game g1; // the game that is played

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

    // Constructor
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
        for (int i = 0; i < btnListLength; i++) {
            Intersection intersection = board.getIntersectionList().get(i); // get each intersection from list

            InitialBoard iniBoard = this;
            intersection.inter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    WhiteToken whiteToken = new WhiteToken(intersection.getCoordinateX(), intersection.getCoordinateY(), iniBoard); // create a white token
                    BlackToken blackToken = new BlackToken(intersection.getCoordinateX(), intersection.getCoordinateY(), iniBoard); // create a black token

                    Token token = whiteToken;

                    if (getGame().getCurrentPlayer().getTokenColour() == TokenColour.PLAYER_2_BLACK) {
                        token = blackToken;
                    }

                    token.addMouseListener(token.tokenSelected);

                    int index = intersection.getAccessibleContext().getAccessibleIndexInParent(); // get the index of the intersection from the list

                    if (checkSelected()){
                        MoveTokenAction newMoveAction = new MoveTokenAction(getGame().getCurrentPlayer(),getGame().getBoard().getIntersection(selectedToken.getCoordinateX(), selectedToken.getCoordinateY()), getGame().getBoard().getIntersection(intersection.getCoordinateX(),intersection.getCoordinateY()), getGame());
                        if (newMoveAction.execute()) {
                            // remove token from board
                            placeToken.remove(selectedToken.index); // remove the selected white token from the previous intersection
                            placeToken.add(new JLabel(), selectedToken.index); // add the placeholder to the previous intersection at place token layer
                            placeToken.remove(index); // remove the previous placeholder
                            placeToken.add(selectedToken, index); // add the selected white token to the intersection that the player wants to move
                            selectedToken.setCoordinateX(intersection.getCoordinateX()); // update the coordinate of the selected white token
                            selectedToken.setCoordinateY(intersection.getCoordinateY());
                            selectedToken.index = index; // update the index of the selected white token
                            selectedToken.selected = false; // remove the red selected border
                            iniBoard.isSelected = false;
                            placeToken.repaint();
                            placeToken.revalidate();
                            getGame().endTurn();
                            playerTurn.changeIcon();
                            return;
                        }
                    }
                    if (!checkSelected()) { // if no white token is selected
                        PlaceTokenAction newPlaceAction = new PlaceTokenAction(getGame().getCurrentPlayer(),getGame().getBoard().getIntersection(intersection.getCoordinateX(),intersection.getCoordinateY()), getGame());
                        if(newPlaceAction.execute()){
                            placeToken.remove(index); // remove the placeholder at the same index
                            placeToken.add(token, index); // add white token at the same index
                            placeToken.repaint();
                            placeToken.revalidate();
                            token.index = index; // set the index of the white token
                            whiteList.add(token); // add white token to the list
                            decreaseTokenRemainder(); // decrease the token remainder after placing a token
                            getGame().endTurn();
                            playerTurn.changeIcon();
                        }

                    }
                }
            });

        }

    }

    /**
     * This method is used to check if any token is selected
     * @return true if a token is selected, false otherwise
     */
    protected boolean checkSelected(){
        for (Token token : whiteList) {
            if (token.selected){
                selectedToken = token; // set selected white token
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to decrease the token remainder after placing a token
     */
    protected void decreaseTokenRemainder(){
        if (getGame().getCurrentPlayer().getTokenColour() == TokenColour.PLAYER_1_WHITE){
            whiteTokenRemain.decreaseAmountToken(); // decrease the white token remainder
        }
        else {
            blackTokenRemain.decreaseAmountToken(); // decrease the black token remainder
        }
    }

}
