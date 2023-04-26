package Frontend;
import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// This is the InitialBoard that players can see when they start the game
public class InitialBoard extends JPanel {
    ArrayList<Token> whiteList = new ArrayList<>(); // create a list to store white tokens
    Buttons buttons = new Buttons();
    Frontend.Board board = new Board();
    Backend.Board BackendBoard = new Backend.Board();
    PlaceToken placeToken = new PlaceToken();
    PlayerTurn playerTurn = new PlayerTurn();
    WhiteTokenRemain whiteTokenRemain = new WhiteTokenRemain();
    BlackTokenRemain blackTokenRemain = new BlackTokenRemain();
    Token selectedWhite;

    Game g1;

    Boolean alwaysTrue = true;

    public void setGame(Game g1) {
        this.g1 = g1;
    }

    public Game getGame() {
        return g1;
    }

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
                    BlackToken blackToken = new BlackToken(intersection.getCoordinateX(), intersection.getCoordinateY()); // create a black token

                    Token token = whiteToken;

                    if (getGame().getCurrentPlayer().getTokenColour() == TokenColour.PLAYER_2_BLACK) {
                        token = blackToken;
                    }

                    int index = intersection.getAccessibleContext().getAccessibleIndexInParent(); // get the index of the intersection from the list

                    if (checkSelected()){
                        MoveTokenAction newMoveAction = new MoveTokenAction(getGame().getCurrentPlayer(),getGame().getBoard().getIntersection(selectedWhite.getCoordinateX(),selectedWhite.getCoordinateY()), getGame().getBoard().getIntersection(intersection.getCoordinateX(),intersection.getCoordinateY()), getGame());
                        if (newMoveAction.execute()) {
                            // remove token from board
                            placeToken.remove(selectedWhite.index); // remove the selected white token from the previous intersection
                            placeToken.add(new JLabel(), selectedWhite.index); // add the placeholder to the previous intersection at place token layer
                            placeToken.remove(index); // remove the previous placeholder
                            placeToken.add(selectedWhite, index); // add the selected white token to the intersection that the player wants to move
                            selectedWhite.setCoordinateX(intersection.getCoordinateX()); // update the coordinate of the selected white token
                            selectedWhite.setCoordinateY(intersection.getCoordinateY());
                            selectedWhite.index = index; // update the index of the selected white token
                            selectedWhite.selected = false; // remove the red selected border
                            placeToken.repaint();
                            placeToken.revalidate();
                            System.out.println("moved");
                            getGame().endTurn();
                            return;
                        }
                    }

                    System.out.println("hello");
                    // place token on board
                    PlaceTokenAction newPlaceAction = new PlaceTokenAction(getGame().getCurrentPlayer(),getGame().getBoard().getIntersection(intersection.getCoordinateX(),intersection.getCoordinateY()), getGame());
                    if(newPlaceAction.execute()){
                        placeToken.remove(index); // remove the placeholder at the same index
                        placeToken.add(token, index); // add white token at the same index
                        placeToken.repaint();
                        placeToken.revalidate();
                        token.index = index; // set the index of the white token
                        whiteList.add(token); // add white token to the list
                        System.out.println("placed");
                            if (!checkSelected()) { // if no white token is selected
                            token.addMouseListener(token.tokenSelected);
                            getGame().endTurn();
                            System.out.println("Selected here");;
                        }

                    }
//
//                    // **Test Backend code**
//                    DoNothingAction doNothingAction = new DoNothingAction();
//                    System.out.println(doNothingAction.execute());
                }
            });

        }

    }

    // check if any white token is selected
    protected boolean checkSelected(){
        for (Token token : whiteList) {
            token.removeMouseListener(token.tokenSelected);
            if (token.selected){
                token.addMouseListener(token.tokenSelected);
                selectedWhite = token; // set selected white token
                System.out.println("selected");
                System.out.println(selectedWhite);
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
