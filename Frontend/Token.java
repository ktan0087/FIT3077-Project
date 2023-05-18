package Frontend;

import Backend.RemoveTokenAction;
import Backend.TokenColour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Token extends JLabel {
    /**
     * This class is used to create the token
     */
    private int coordinateX;
    private int coordinateY;
    private int times;
    protected Boolean selected;
    protected int index;
    protected Color tokenColor;

    protected TokenColour ownerTokenColour;

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

            int x = 500/(13*2); // 500 is the width of the panel, 13 is the number of columns/rows, 2 is to get the middle point
            int y = 500/(13*2); // this is to get the middle point of each grid

            // draw the outer circle
            g.setColor(new Color(0xFF0000));
            int diameterOuter = 500/13;
            g.fillOval(0, 0, diameterOuter, diameterOuter);

            // draw the inner circle
            g.setColor(this.tokenColor);
            int radiusInner = 15;
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

            int x = 500 / (13 * 2); // 500 is the width of the panel, 13 is the number of columns/rows, 2 is to get the middle point
            int y = 500 / (13 * 2); // this is to get the middle point of each grid
            int radius = 15;
            int diameter = radius * 2;

            //shift x and y by the radius of the circle in order to correctly center it
            g.fillOval(x - radius, y - radius, diameter, diameter);
        }
    }

    /**
     * This method is to select the token that player wants to move
     */
    protected MouseListener tokenSelected = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(iniBoard.canRemove){
                RemoveTokenAction newRemoveTokenAction = new RemoveTokenAction(iniBoard.getGame().getCurrentPlayer(), iniBoard.getGame().getBoard().getIntersection(getCoordinateX(), getCoordinateY()),iniBoard.getGame());
                if (newRemoveTokenAction.execute()){
                    iniBoard.placeToken.remove(index);
                    iniBoard.placeToken.add(new JLabel(), index);
                    iniBoard.placeToken.repaint();
                    iniBoard.placeToken.revalidate();
                    iniBoard.canRemove = false;
                    iniBoard.getGame().getBoard().getIntersection(getCoordinateX(), getCoordinateY()).removeToken();
                    iniBoard.getGame().swapPlayers();
                    iniBoard.playerTurn.changeIcon();
                    System.out.println("------------------");
                    System.out.println(iniBoard.getGame().getCurrentPlayer());
                    System.out.println(iniBoard.getGame().getOtherPlayer());
                    if (iniBoard.getGame().isGameOver()){
                        if (iniBoard.getGame().getWinner().getTokenColour() == TokenColour.PLAYER_2_BLACK) {
                            iniBoard.displayResult(Win.WhoWin.BLACKWIN);
                        }
                        else{
                            iniBoard.displayResult(Win.WhoWin.WHITEWIN);
                        }
                    }
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

}
