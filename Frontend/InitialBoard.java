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
    private ArrayList<Token> tokenList = new ArrayList<>(); // create a list to store tokens
    protected Buttons buttons = new Buttons(); // buttons that illustrate hint, restart, close
    private Frontend.Board board = new Board(); // create a board
    private PlaceToken placeToken = new PlaceToken(); // create a layer to place tokens
    private PlayerTurn playerTurn = new PlayerTurn(); // show which player's turn
    private WhiteTokenRemain whiteTokenRemain = new WhiteTokenRemain(); // show the remaining number of white tokens
    private BlackTokenRemain blackTokenRemain = new BlackTokenRemain(); // show the remaining number of black tokens
    private Token selectedToken; // the token that is selected by the player
    protected boolean isSelected; // whether the player has selected a token
    private Game g1; // the game that is played

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

        // add buttons to top right of the panel
        gbc.gridx = 2; // set the x position of the component
        gbc.gridy = 0; // set the y position of the component
        gbc.weightx = 0; // horizontal spacing (use values from 0.0 to 1.0)
        gbc.weighty = 0; // vertical spacing
        gbc.anchor = GridBagConstraints.NORTHEAST; // set the position of the component
        this.add(buttons, gbc); // add the component to this panel

        // add the board to the center of the panel
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(placeToken, gbc);
        this.add(board, gbc);

        // add the player turn to the bottom of the panel
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        this.add(playerTurn, gbc);

        // add the remaining number of white tokens to the left of the panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(whiteTokenRemain, gbc);

        // add the remaining number of black tokens to the right of the panel
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
                /**
                 * Invoked when an intersection point is clicked.
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {

                    WhiteToken whiteToken = new WhiteToken(intersection.getCoordinateX(), intersection.getCoordinateY(), iniBoard); // create a white token
                    BlackToken blackToken = new BlackToken(intersection.getCoordinateX(), intersection.getCoordinateY(), iniBoard); // create a black token

                    Token token = whiteToken; // set the token to white token by default

                    // set the token to black token if the current player is player 2
                    if (getGame().getCurrentPlayer().getTokenColour() == TokenColour.PLAYER_2_BLACK) {
                        token = blackToken;
                    }

                    // make the token can be selected after clicking it
                    // (If the token is selected, a red border will appear around the token)
                    token.addMouseListener(token.tokenSelected);

                    int index = intersection.getAccessibleContext().getAccessibleIndexInParent(); // get the index of the intersection from the list

                    if (checkSelected()){
                        MoveTokenAction newMoveAction = new MoveTokenAction(getGame().getCurrentPlayer(),getGame().getBoard().getIntersection(selectedToken.getCoordinateX(), selectedToken.getCoordinateY()), getGame().getBoard().getIntersection(intersection.getCoordinateX(),intersection.getCoordinateY()), getGame());
                        if (newMoveAction.execute()) {
                            // move token on board (placeToken panel)
                            placeToken.remove(selectedToken.index); // remove the selected white token from the previous intersection
                            placeToken.add(new JLabel(), selectedToken.index); // add the placeholder to the previous intersection at place token layer
                            placeToken.remove(index); // remove the previous placeholder
                            placeToken.add(selectedToken, index); // add the selected white token to the intersection that the player wants to move
                            selectedToken.setCoordinateX(intersection.getCoordinateX()); // update the coordinate of the selected token
                            selectedToken.setCoordinateY(intersection.getCoordinateY());
                            selectedToken.setIndex(index); // update the index of the selected white token
                            selectedToken.selected = false; // remove the red selected border
                            iniBoard.isSelected = false; // no token is selected
                            placeToken.repaint();
                            placeToken.revalidate();
                            getGame().endTurn(); // end the turn
                            playerTurn.changeIcon(); // change the player turn icon between black and white token
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
                            tokenList.add(token); // add white token to the list
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
        for (Token token : tokenList) {
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
