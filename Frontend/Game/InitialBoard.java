package Frontend.Game;
import Backend.*;
import Backend.Action.FlyTokenAction;
import Backend.Action.MoveTokenAction;
import Backend.Action.PlaceTokenAction;
import Backend.Interfaces.CanRemoveMill;
import Backend.Token.TokenColour;
import Frontend.Components.Instruction;
import Frontend.Components.PlayerTurn;
import Frontend.Components.TokenRemain;
import Frontend.Components.Win;
import Frontend.Layer.PlaceToken;
import Frontend.Line.Mill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class InitialBoard extends JPanel {
    /**
     * The initial board that players can see when they start the game
     */
    private ArrayList<Token> tokenList; // create a list to store tokens
    protected InnitialBoardButtons buttons; // buttons that illustrate hint, restart, close
    private Board board; // create a board
    protected PlaceToken placeToken; // create a layer to place tokens
    protected PlayerTurn playerTurn; // show which player's turn
    private TokenRemain whiteTokenRemain; // show the remaining number of white tokens
    protected TokenRemain blackTokenRemain; // show the remaining number of black tokens
    private Token selectedToken; // the token that is selected by the player
    protected boolean isSelected; // whether the player has selected a token
    protected Instruction instruction; // provide the instruction of the game
    private Game game; // the game that is played
    private PlaceToken millLayer; // the layer that shows the mill
    protected ResultButtons resultButton;
    private Token tokenToRemove; // the token that is selected to be removed

    protected Boolean canRemove = false;
    private int millCount;

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

    // Constructor
    public InitialBoard() {
        // Create components in the initial board
        this.tokenList = new ArrayList<>(); // create a list to store tokens
        this.buttons = new InnitialBoardButtons(); // buttons that illustrate hint, restart, close
        this.board = new Board(); // create a board
        this.placeToken = new PlaceToken(); // create a layer to place tokens
        this.playerTurn = new PlayerTurn(); // show which player's turn
        this.whiteTokenRemain = new TokenRemain(TokenRemain.TokenColour.WHITE); // show the remaining number of white tokens
        this.blackTokenRemain = new TokenRemain(TokenRemain.TokenColour.BLACK); // show the remaining number of black tokens
        this.instruction = new Instruction(Instruction.InstructionType.EMPTY); // provide the instruction of the game
        this.millLayer = new PlaceToken(); // the layer that shows the mill
        this.resultButton = new ResultButtons();

        // set the background color of the board
        this.setBackground(new Color(0xE0A060));
        this.setOpaque(true);

        // Set layout of Buttons in board
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // add gaps between the components

        // add buttons to top right of the panel
        gbc.gridx = 3; // set the x position of the component
        gbc.gridy = 0; // set the y position of the component
        gbc.weightx = 0; // horizontal spacing (use values from 0.0 to 1.0)
        gbc.weighty = 0; // vertical spacing
        gbc.anchor = GridBagConstraints.NORTHEAST; // set the position of the component
        this.add(buttons, gbc); // add the component to this panel

        // add the board to the center of the panel
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 3;
        this.add(instruction, gbc);

        // add the board to the center of the panel
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 3;
        this.add(placeToken, gbc);
        this.add(millLayer, gbc);
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
                        FlyTokenAction newFlyAction = new FlyTokenAction(getGame().getCurrentPlayer(),getGame().getBoard().getIntersection(selectedToken.getCoordinateX(), selectedToken.getCoordinateY()), getGame().getBoard().getIntersection(intersection.getCoordinateX(),intersection.getCoordinateY()), getGame());
                        if (newFlyAction.execute()){
                            System.out.println("*********************************************************************************************Fly Token Action executed");
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

                            checkAndRemoveMills(newFlyAction);

                            // Check if a mill is formed and then do approriate actions and draw them
                            checkAndDrawMills();

                            getGame().endTurn(); // end the turn
                            playerTurn.changeIcon(); // change the player turn icon between black and white token
                            // Display which player wins the game
                            checkEndGame();
                            return;
                        }
                    }

                    if (checkSelected()){
                        MoveTokenAction newMoveAction = new MoveTokenAction(getGame().getCurrentPlayer(),getGame().getBoard().getIntersection(selectedToken.getCoordinateX(), selectedToken.getCoordinateY()), getGame().getBoard().getIntersection(intersection.getCoordinateX(),intersection.getCoordinateY()), getGame());
                        if (newMoveAction.execute()) {
                            System.out.println("*********************************************************************************************Move Token Action executed");
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

                            checkAndRemoveMills(newMoveAction);

                            // Check if a mill is formed and then do approriate actions and draw them
                            checkAndDrawMills();

                            getGame().endTurn(); // end the turn
                            playerTurn.changeIcon(); // change the player turn icon between black and white token

                            // Display which player wins the game
                            checkEndGame();
                            return;
                        }
                    }
                    if (!checkSelected()) { // if no white token is selected
                        PlaceTokenAction newPlaceAction = new PlaceTokenAction(getGame().getCurrentPlayer(),getGame().getBoard().getIntersection(intersection.getCoordinateX(),intersection.getCoordinateY()), getGame());
                        if(newPlaceAction.execute()){
                            instruction.changeText(Instruction.InstructionType.PLACE);
                            placeToken.remove(index); // remove the placeholder at the same index
                            placeToken.add(token, index); // add white token at the same index
                            placeToken.repaint();
                            placeToken.revalidate();
                            token.setIndex(index); // set the index of the white token
                            tokenList.add(token); // add white token to the list
                            decreaseTokenRemainder(); // decrease the token remainder after placing a token
                            getGame().endTurn();
                            playerTurn.changeIcon();
                            System.out.println("------------------");
                            System.out.println(getGame().getCurrentPlayer());
                            System.out.println(getGame().getOtherPlayer());
                        }
                    }

                    // Check if a mill is formed and then do approriate actions and draw them
                    checkAndDrawMills();
                    // Display which player wins the game
                    checkEndGame();
                }
            });

        }

    }

    public void checkAndRemoveMills(CanRemoveMill action){
        for (Backend.Board.Mill removedMill : action.getRemoveMillList()) {
            int firstIndexLayer = removedMill.getIntersection().get(0).getLayer();
            int firstIndexPosition = removedMill.getIntersection().get(0).getPosition();
            int firstIndexTemp = board.getIndexLookUpTable(firstIndexLayer, firstIndexPosition);

            int secondIndexLayer = removedMill.getIntersection().get(1).getLayer();
            int secondIndexPosition = removedMill.getIntersection().get(1).getPosition();
            int secondIndexTemp = board.getIndexLookUpTable(secondIndexLayer, secondIndexPosition);

            int thirdIndexLayer = removedMill.getIntersection().get(2).getLayer();
            int thirdIndexPosition = removedMill.getIntersection().get(2).getPosition();
            int thirdIndexTemp = board.getIndexLookUpTable(thirdIndexLayer, thirdIndexPosition);

            removeMill(firstIndexTemp, secondIndexTemp, thirdIndexTemp, millLayer);
            millCount--;
        }
    }

    public void checkAndDrawMills(){
        // Check if a mill is formed
        if (game.getBoard().getMills().size() > 0){
            if (millCount != game.getBoard().getMills().size()){
                instruction.changeText(Instruction.InstructionType.REMOVE);
                game.swapPlayers();
                playerTurn.changeIcon();
                millCount = game.getBoard().getMills().size();
            }

            for (Backend.Board.Mill mills : game.getBoard().getMills()){
                //continue here
                int firstIndexLayer = mills.getIntersection().get(0).getLayer();
                int firstIndexPosition = mills.getIntersection().get(0).getPosition();
                int firstIndexTemp = board.getIndexLookUpTable(firstIndexLayer, firstIndexPosition);

                int secondIndexLayer = mills.getIntersection().get(1).getLayer();
                int secondIndexPosition = mills.getIntersection().get(1).getPosition();
                int secondIndexTemp = board.getIndexLookUpTable(secondIndexLayer, secondIndexPosition);

                int thirdIndexLayer = mills.getIntersection().get(2).getLayer();
                int thirdIndexPosition = mills.getIntersection().get(2).getPosition();
                int thirdIndexTemp = board.getIndexLookUpTable(thirdIndexLayer, thirdIndexPosition);

                addMill(firstIndexTemp, secondIndexTemp, thirdIndexTemp, millLayer);
            }
            canRemove = true;
        }

    }

    /**
     * This method is used to display the result of the game
     */
    public void checkEndGame(){
        // Display which player wins the game
        if (game.isGameOver()) {
            this.instruction.changeText(Instruction.InstructionType.EMPTY);
            if (game.getWinner().getTokenColour() == TokenColour.PLAYER_2_BLACK) {
                displayResult(Win.WhoWin.BLACKWIN);
            } else {
                displayResult(Win.WhoWin.WHITEWIN);
            }
        }
    }

    /**
     * This method is used to check if any token is selected
     * @return true if a token is selected, false otherwise
     */
    public boolean checkSelected(){
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
    public void decreaseTokenRemainder(){
        if (getGame().getCurrentPlayer().getTokenColour() == TokenColour.PLAYER_1_WHITE){
            whiteTokenRemain.decreaseAmountToken(); // decrease the white token remainder
        }
        else {
            int blackRemaining = blackTokenRemain.decreaseAmountToken(); // decrease the black token remainder
            if (blackRemaining == 0){
                instruction.changeText(Instruction.InstructionType.MOVE);
            }
        }
    }

    public int findSmallest(int index1, int index2, int index3){
        if(index1 < index2 && index1 < index3)
        {
            return index1;
        }
        else return Math.min(index2, index3);
    };

    public int findBiggest(int index1, int index2, int index3){
        if(index1 > index2 && index1 > index3)
        {
            return index1;
        }
        else return Math.max(index2, index3);
    };

    public void addMill(int index1, int index2, int index3, PlaceToken millLayer){
        int minusValue;
        int smallIndex = findSmallest(index1, index2, index3);
        int bigIndex = findBiggest(index1, index2, index3);

        minusValue = bigIndex - smallIndex;
        if (minusValue == 52 || minusValue == 104 || minusValue == 156){
            millLayer.remove(smallIndex);
            millLayer.add(new Mill(Mill.Direction.FIRST_HALF_VERTICAL), smallIndex);
            for (int i = smallIndex + 13; i < bigIndex; i += 13){
                millLayer.remove(i);
                millLayer.add(new Mill(Mill.Direction.VERTICAL), i);
            }
            millLayer.remove(bigIndex);
            millLayer.add(new Mill(Mill.Direction.LAST_HALF_VERTICAL), bigIndex);
        }
        else if (minusValue == 4 || minusValue == 8 || minusValue == 12){
            millLayer.remove(smallIndex);
            millLayer.add(new Mill(Mill.Direction.FIRST_HALF_HORIZONTAL), smallIndex);
            for (int i = smallIndex + 1; i < bigIndex; i++){
                millLayer.remove(i);
                millLayer.add(new Mill(Mill.Direction.HORIZONTAL), i);
            }
            millLayer.remove(bigIndex);
            millLayer.add(new Mill(Mill.Direction.LAST_HALF_HORIZONTAL), bigIndex);
        }
    }

    public void removeMill(int index1, int index2, int index3, PlaceToken millLayer){
        int minusValue;
        int smallIndex = findSmallest(index1, index2, index3);
        int bigIndex = findBiggest(index1, index2, index3);

        minusValue = bigIndex - smallIndex;
        if (minusValue == 52 || minusValue == 104 || minusValue == 156){
            for (int i = smallIndex ; i <= bigIndex; i += 13){
                millLayer.remove(i);
                millLayer.add(new JLabel(), i);
            }
        }
        else if (minusValue == 4 || minusValue == 8 || minusValue == 12){
            for (int i = smallIndex; i <= bigIndex; i++){
                millLayer.remove(i);
                millLayer.add(new JLabel(), i);
            }
        }
    }

    public void displayResult(Win.WhoWin winner){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // get the screen size
        Win win;

        JFrame frame = new JFrame();
        JDialog result = new JDialog(frame); // super(new JFrame(), true)
        result.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        // result screen cannot be closed unless press the 2 buttons (restart or close)
        result.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        result.setUndecorated(true);

        // set a dimmed and transparent background for the result page
        result.setPreferredSize(screenSize);
        result.getRootPane().setOpaque (false);
        result.getContentPane().setBackground (new Color (0, 0, 0, 0));
        result.setBackground(new Color(0x80000000, true));

        // arrange the components in result page
        result.setLayout(new GridBagLayout()); // set the layout of this panel
        GridBagConstraints gbc = new GridBagConstraints(); // create a GridBagConstraint object
        gbc.insets = new Insets(5, 5, 5, 5); // add gaps between the components

        // Display which player wins
        gbc.gridx = 0; // set the x position of the component
        gbc.gridy = 0; // set the y position of the component
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // set the position of the component
        win = new Win(winner);
        result.add(win, gbc); // add the component to this panel

        // Display the restart and close buttons
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        result.add(resultButton, gbc);

        result.pack();
        result.setLocationRelativeTo(null);
        result.setVisible(true);
    }

}
