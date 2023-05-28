package Frontend.Game;

import Backend.Action.RemoveTokenAction;
import Backend.Token.TokenColour;
import Frontend.Components.Instruction;
import Frontend.Components.Win;
import Frontend.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class is used to create the token
 */
public abstract class Token extends JLabel {

    /**
     * x coordinates of the Token (Layer in backend)
     */
    private int coordinateX;
    /**
     * y coordinates of the Token (Position in backend)
     */
    private int coordinateY;
    /**
     * times is used to keep track of the number of times the token is clicked
     */
    private int times;
    /**
     * selected is used to keep track of whether the token is selected
     */
    protected Boolean selected;
    /**
     * index is used to keep track of the index of the token in frontend Board
     */
    protected int index;
    /**
     * tokenColor is used to keep track of the color of the token
     */
    protected Color tokenColor;

    /**
     * ownerTokenColour is used to keep track of the color of the token
     */
    protected TokenColour ownerTokenColour;

    /**
     * iniBoard is the iniBoard of frontend
     */
    private InitialBoard iniBoard;

    // Constructor
    public Token(int coordinateX, int coordinateY, InitialBoard iniBoard){
        this.selected = false;

        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.times = 0;
        this.index = -1; // index in board panel
        this.iniBoard = iniBoard;
    }

    /**
     * This method is used to set the index of the token in Board
     * @param index is the index of the token in Board (from 0 to 168, as it is a 13x13 board)
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * This method is used to get the x coordinate of the token
     * @return x coordinate (layer) of the token
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * This method is used to get the y coordinate of the token
     * @return y coordinate (position) of the token
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * This method is used to set the layer of the token in PlaceToken panel
     * @param coordinateX is the layer of the token (e.g. TOP_LEFT has the layer of 1)
     */
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * This method is used to set the position of the token in PlaceToken panel
     * @param coordinateY is the position of the token (e.g. TOP_LEFT has the position of 1)
     */
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    /**
     * When the token is selected, a red border will be drawn around the token
     * @param g  is the graphics object
     */
    protected void paintBorder(Graphics g) {
        if (this.selected) {
            // to cover the intersection point
            repaint();
            revalidate();

            int x = getWidth()/2; // to get the middle point
            int y = getHeight()/2; // to get the middle point

            // draw the outer circle
            g.setColor(new Color(0xFF0000));
            int diameterOuter = getWidth();
            g.fillOval(0, 0, diameterOuter, diameterOuter);

            // draw the inner circle
            g.setColor(this.tokenColor);
            int radiusInner = new Size(15, 15).getHeight();
            int diameterInner = radiusInner * 2;
            g.fillOval(x - radiusInner, y - radiusInner, diameterInner, diameterInner);
        }
    }

    /**
     * When the token is not selected, token will be drawn by its token color
     * @param g is the graphics object
     */
    protected void paintComponent(Graphics g) {
        if (!this.selected) {
            // to cover the intersection point
            repaint();
            revalidate();

            // paint the white token
            g.setColor(this.tokenColor);

            int x = getWidth()/2; // to get the middle point
            int y = getHeight()/2; // to get the middle point
            int radius = new Size(15, 15).getHeight();
            int diameter = radius * 2;

            //shift x and y by the radius of the circle in order to correctly center it
            g.fillOval(x - radius, y - radius, diameter, diameter);
        }
    }

    /**
     * This method dictates the action of the token when it is clicked
     * This method check if a player can remove a token after forming a mill, if yes, the player can remove a token.
     * If the player cannot remove a token, the game will draw.
     * If the player can remove a token, the player can remove a token by clicking on the token. After removing the
     * token, the game will check if the game is over.
     *
     */
    protected MouseListener tokenSelected = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            //If there is a player that can remove a token by forming a mill
            if(iniBoard.canRemove){
                //Checks if the current player have any valid tokens to remove(tokens not in a mill)
                if (!iniBoard.getGame().checkPossibleRemove(iniBoard.getGame().getOtherPlayer())){
                    iniBoard.displayResult(Win.WhoWin.DRAW); //Game draws if the player cannot remove a token after forming a mill
                }
                RemoveTokenAction newRemoveTokenAction = new RemoveTokenAction(iniBoard.getGame().getCurrentPlayer(), iniBoard.getGame().getBoard().getIntersection(getCoordinateX(), getCoordinateY()),iniBoard.getGame());
                if (newRemoveTokenAction.execute()){                //If a player can remove a token
                    iniBoard.placeToken.remove(index);
                    iniBoard.placeToken.add(new JLabel(), index);
                    iniBoard.placeToken.repaint();
                    iniBoard.placeToken.revalidate();
                    iniBoard.canRemove = false;
                    iniBoard.getGame().getBoard().getIntersection(getCoordinateX(), getCoordinateY()).removeToken();
                    iniBoard.getGame().endTurn();
                    iniBoard.playerTurn.changeIcon();
                    //Check if the opponent have any possible moves after its token is removed
                    if (!iniBoard.getGame().checkPossibleMove(iniBoard.getGame().getCurrentPlayer())){
                        //If the opponent does not have any possible moves, the game ends and the player that has possible moves wins
                        if (iniBoard.getGame().getCurrentPlayer().getTokenColour() == TokenColour.PLAYER_1_WHITE){
                            iniBoard.displayResult(Win.WhoWin.BLACKWIN);
                        }
                        else {
                            iniBoard.displayResult(Win.WhoWin.WHITEWIN);
                        }
                    }
                    iniBoard.checkEndGame();
                    swapInstruction();
                    return;
                }

            }
            if (times%2 == 0 && !iniBoard.isSelected && iniBoard.getGame().getCurrentPlayer().getTokenColour() == ownerTokenColour) {
                selected = true; // click the white token to select it
                iniBoard.isSelected = true;
            }
            else if (iniBoard.isSelected && selected){
                selected = false;
                iniBoard.isSelected = false;
            }
            else {
                selected = false; // click again the selected white token to deselect it
            }
            times++;
        }
    };

    /**
     * This method is used to swap the instruction displayed during various phases of the game
     */
    public void swapInstruction(){
        if (iniBoard.blackTokenRemain.getAmountToken() > 0){
            iniBoard.instruction.changeText(Instruction.InstructionType.PLACE);
        }
        else if (iniBoard.blackTokenRemain.getAmountToken() == 0){
            if (iniBoard.getGame().getCurrentPlayer().getTokensOnBoard() == 3){
                iniBoard.instruction.changeText(Instruction.InstructionType.FLY);
            }
            else {
                iniBoard.instruction.changeText(Instruction.InstructionType.MOVE);
            }
        }
    }

}
