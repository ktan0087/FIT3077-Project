package Frontend.Game;
import Backend.*;
import Backend.Action.FlyTokenAction;
import Backend.Action.MoveTokenAction;
import Backend.Action.PlaceTokenAction;
import Backend.Interfaces.CanRemoveMill;
import Backend.Token.TokenColour;
import Frontend.Components.*;
import Frontend.HintCircle;
import Frontend.Layer.PlaceToken;
import Frontend.Line.Mill;
import Frontend.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 * A class to represent the initial board of the game.
 *
 * @see InitialBoardButtons
 * @see PlaceToken
 * @see PlayerTurn
 * @see TokenRemain
 * @see Instruction
 * @see Game
 * @see ResultButtons
 * @see Token
 * @see Mill
 */

public class InitialBoard extends JPanel {
    /**
     * The list of tokens in the game
     */
    private ArrayList<Token> tokenList;

    /**
     * The buttons that illustrate hint, restart, close
     */
    protected InitialBoardButtons buttons;

    /**
     * A board that the game is played on
     */
    private Board board;

    /**
     * The layer that is used to place tokens
     */
    protected PlaceToken placeToken; // create a layer to place tokens

    /**
     * The layer that is used to show the player's turn
     */
    protected PlayerTurn playerTurn; // show which player's turn

    /**
     * The layer that is used to show the remaining number of white token
     */
    private TokenRemain whiteTokenRemain;

    /**
     * The layer that is used to show the remaining number of black token
     */
    protected TokenRemain blackTokenRemain;

    /**
     * The token that is selected by the player
     */
    private Token selectedToken;

    /**
     * A boolean to check whether any token that is selected
     */
    protected boolean isSelected;

    /**
     * The label that is used to show the instruction of the game
     */
    protected Instruction instruction;

    /**
     * The game that is played
     */
    private Game game;

    /**
     * The layer that is used to show the mill
     */
    private PlaceToken millLayer;

    /**
     * The layer that is used to display hint
     */
    private PlaceToken hintLayer;

    /**
     * The result buttons include restart, close
     */
    protected ResultButtons resultButton;

    /**
     * A boolean to check whether the player can remove a mill
     */
    protected Boolean canRemove;

    /**
     * A boolean to check whether the player pressed hint button
     */
    private Boolean hintPressed;

    /**
     * A list to store hint's intersections
     */
    private ArrayList<Intersection> hintList; // a list of all intersections on the board

    /**
     * The number of mills that the player can remove
     */
    private int millCount;

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
     * Constructor.
     * Create the initial board of the game.
     */
    public InitialBoard() {
        // Create components in the initial board
        this.tokenList = new ArrayList<>(); // create a list to store tokens
        this.buttons = new InitialBoardButtons(); // buttons that illustrate hint, restart, close
        this.board = new Board(); // create a board
        this.placeToken = new PlaceToken(); // create a layer to place tokens
        this.playerTurn = new PlayerTurn(); // show which player's turn
        this.whiteTokenRemain = new TokenRemain(TokenRemain.TokenColour.WHITE); // show the remaining number of white tokens
        this.blackTokenRemain = new TokenRemain(TokenRemain.TokenColour.BLACK); // show the remaining number of black tokens
        this.instruction = new Instruction(Instruction.InstructionType.EMPTY); // provide the instruction of the game
        this.millLayer = new PlaceToken(); // the layer that shows the mill
        this.hintLayer = new PlaceToken(); // the layer that shows the hint
        this.resultButton = new ResultButtons();
        this.canRemove = false;
        this.hintPressed = false;
        this.hintList = (ArrayList<Intersection>) board.getIntersectionList();

        this.buttons.btnHint.addActionListener(hintAction);

        // set the background color of the board
        this.setBackground(new Color(0xE0A060));
        this.setOpaque(true);

        // Set layout of Buttons in board
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Size gapSize = new Size(5, 5);
        gbc.insets = new Insets(gapSize.getHeight(), gapSize.getHeight(), gapSize.getHeight(), gapSize.getHeight()); // add gaps between the components

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
        this.add(hintLayer, gbc);
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
                 *
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

                            // Check if a mill is formed and then do appropriate actions and draw them
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

    /**
     * Check and remove mills. If a mill is formed, remove the token from the board.
     *
     * @param action is the action that is executed that implements the CanRemoveMill interface
     */
    public void checkAndRemoveMills(CanRemoveMill action){
        for (Backend.Board.Mill removedMill : action.getRemoveMillList()) {
            // get the first, second and third index of the mill
            int firstIndexLayer = removedMill.getIntersection().get(0).getLayer(); // get the layer of the intersection
            int firstIndexPosition = removedMill.getIntersection().get(0).getPosition(); // get the position of the intersection
            int firstIndexTemp = board.getIndexLookUpTable(firstIndexLayer, firstIndexPosition); // get the index of the intersection from lookup table

            int secondIndexLayer = removedMill.getIntersection().get(1).getLayer();
            int secondIndexPosition = removedMill.getIntersection().get(1).getPosition();
            int secondIndexTemp = board.getIndexLookUpTable(secondIndexLayer, secondIndexPosition);

            int thirdIndexLayer = removedMill.getIntersection().get(2).getLayer();
            int thirdIndexPosition = removedMill.getIntersection().get(2).getPosition();
            int thirdIndexTemp = board.getIndexLookUpTable(thirdIndexLayer, thirdIndexPosition);

            // remove the token from the board
            removeMill(firstIndexTemp, secondIndexTemp, thirdIndexTemp, millLayer);

            // decrease the mill count by 1
            millCount--;
        }
    }

    /**
     * Check and draw mills. If a mill is formed, draw the mill.
     */
    public void checkAndDrawMills(){
        // Check if a mill is formed
        if (game.getBoard().getMills().size() > 0){ // if a mill is formed
            if (millCount != game.getBoard().getMills().size()){ // if the mill count is not equal to the number of mills in the board
                instruction.changeText(Instruction.InstructionType.REMOVE); // change the instruction that show at the top-middle to remove
                game.swapPlayers(); // swap the player
                playerTurn.changeIcon(); // change the player turn icon
                millCount = game.getBoard().getMills().size(); // update the mill count
            }

            for (Backend.Board.Mill mills : game.getBoard().getMills()){ // for each mill in the board
                // get the first, second and third index of the mill
                int firstIndexLayer = mills.getIntersection().get(0).getLayer();
                int firstIndexPosition = mills.getIntersection().get(0).getPosition();
                int firstIndexTemp = board.getIndexLookUpTable(firstIndexLayer, firstIndexPosition);

                int secondIndexLayer = mills.getIntersection().get(1).getLayer();
                int secondIndexPosition = mills.getIntersection().get(1).getPosition();
                int secondIndexTemp = board.getIndexLookUpTable(secondIndexLayer, secondIndexPosition);

                int thirdIndexLayer = mills.getIntersection().get(2).getLayer();
                int thirdIndexPosition = mills.getIntersection().get(2).getPosition();
                int thirdIndexTemp = board.getIndexLookUpTable(thirdIndexLayer, thirdIndexPosition);

                // draw the mill
                addMill(firstIndexTemp, secondIndexTemp, thirdIndexTemp, millLayer);
            }
            canRemove = true; // the player can remove a token
        }
    }

    /**
     * This method is used to display the result of the game
     */
    public void checkEndGame(){
        // Display which player wins the game
        if (game.isGameOver()) { // if the game is over
            this.instruction.changeText(Instruction.InstructionType.EMPTY); // change the instruction to empty
            if (game.getWinner().getTokenColour() == TokenColour.PLAYER_2_BLACK) { // if the winner is black
                displayResult(Win.WhoWin.BLACKWIN); // display black win
            } else {
                displayResult(Win.WhoWin.WHITEWIN);
            }
        }
    }

    /**
     * This method is used to check if any token is selected
     *
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

    /**
     * This method is used to find the smallest number among 3 numbers
     *
     * @param index1 is the first number
     * @param index2 is the second number
     * @param index3 is the third number
     * @return the smallest number
     */
    public int findSmallest(int index1, int index2, int index3){
        if(index1 < index2 && index1 < index3) // if index1 is smaller than index2 and index3
        {
            return index1;
        }
        else return Math.min(index2, index3); // return the smallest number among index2 and index3
    };

    /**
     * This method is used to find the biggest number among 3 numbers
     *
     * @param index1 is the first number
     * @param index2 is the second number
     * @param index3 is the third number
     * @return the biggest number
     */
    public int findBiggest(int index1, int index2, int index3){
        if(index1 > index2 && index1 > index3)
        {
            return index1;
        }
        else return Math.max(index2, index3);
    };

    /**
     * This method is used to add the yellow mill line on the board
     *
     * @param index1 is the first index of the token
     * @param index2 is the second index of the token
     * @param index3 is the third index of the token
     * @param millLayer is the layer that contains the mill
     */
    public void addMill(int index1, int index2, int index3, PlaceToken millLayer){
        int minusValue; // the difference between the biggest and smallest index
        int smallIndex = findSmallest(index1, index2, index3); // find the smallest index
        int bigIndex = findBiggest(index1, index2, index3); // find the biggest index

        minusValue = bigIndex - smallIndex; // calculate the difference between the biggest and smallest index

        /* The following if-else statements are used to add the mill line on the board
           The mill line is added based on the difference between the biggest and smallest index
           The (13x13) board has 169 grids in total, and the index of the grids are from 0 to 168.
           The first 13 grids are the first row, the second 13 grids are the second row, and so on.

           It looks like this: 0  1  2  3  4  5  6  7  8  9  10  11  12
                               13                19                  25
                               26    28 29 30 31 32 33 34 35 36      38
                               39    41          45          49      51
                               52    54    56 57 58 59 60    62      64
                               65    67    69          73    75      77
                               78 79 80 81 82          86 87 88  89  90
                               91    93    95          99    101     103
                               104   106   108 ...

           If the difference between the biggest and smallest index is 52, 104, or 156, it means that the mill is vertical
           If the difference between the biggest and smallest index is 4, 8, or 12, it means that the mill is horizontal
        */
        if (minusValue == 52 || minusValue == 104 || minusValue == 156){ // if the mill is vertical
            millLayer.remove(smallIndex);
            millLayer.add(new Mill(Mill.Direction.FIRST_HALF_VERTICAL), smallIndex);
            for (int i = smallIndex + 13; i < bigIndex; i += 13){
                millLayer.remove(i);
                millLayer.add(new Mill(Mill.Direction.VERTICAL), i);
            }
            millLayer.remove(bigIndex);
            millLayer.add(new Mill(Mill.Direction.LAST_HALF_VERTICAL), bigIndex);
        }
        else if (minusValue == 4 || minusValue == 8 || minusValue == 12){ // if the mill is horizontal
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

    /**
     * This method is used to remove the yellow mill line on the board
     *
     * @param index1 is the first index of the token
     * @param index2 is the second index of the token
     * @param index3 is the third index of the token
     * @param millLayer is the layer that contains the mill
     */
    public void removeMill(int index1, int index2, int index3, PlaceToken millLayer){
        int minusValue; // the difference between the biggest and smallest index
        int smallIndex = findSmallest(index1, index2, index3); // find the smallest index
        int bigIndex = findBiggest(index1, index2, index3); // find the biggest index

        minusValue = bigIndex - smallIndex; // calculate the difference between the biggest and smallest index

        /* The following if-else statements are used to add the mill line on the board
           The mill line is added based on the difference between the biggest and smallest index
           The (13x13) board has 169 grids in total, and the index of the grids are from 0 to 168.
           The first 13 grids are the first row, the second 13 grids are the second row, and so on.

           It looks like this: 0  1  2  3  4  5  6  7  8  9  10  11  12
                               13                19                  25
                               26    28 29 30 31 32 33 34 35 36      38
                               39    41          45          49      51
                               52    54    56 57 58 59 60    62      64
                               65    67    69          73    75      77
                               78 79 80 81 82          86 87 88  89  90
                               91    93    95          99    101     103
                               104   106   108 ...

           If the difference between the biggest and smallest index is 52, 104, or 156, it means that the mill is vertical
           If the difference between the biggest and smallest index is 4, 8, or 12, it means that the mill is horizontal
        */
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

    /**
     * This method is used to display the result of the game
     *
     * @param winner is the winner of the game
     */
    public void displayResult(Win.WhoWin winner){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // get the screen size
        Win win;

        JFrame frame = new JFrame();
        JDialog result = new JDialog(frame); // create a new dialog that extends JFrame
        result.setModalityType(Dialog.ModalityType.APPLICATION_MODAL); // set the result screen to be a modal

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

    protected ActionListener hintAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!hintPressed){
                hintPressed = true;
                for (Intersection intersection : hintList) {
                    new HintCircle(HintCircle.Type.TOKEN).showHint(hintLayer, intersection);
                }
            }
            else {
                hintPressed = false;
                for (Intersection intersection : hintList) {
                    hintLayer.remove(intersection.getAccessibleContext().getAccessibleIndexInParent());
                    hintLayer.add(new JLabel(), intersection.getAccessibleContext().getAccessibleIndexInParent());
                }
            }
            hintLayer.repaint();
            hintLayer.revalidate();
        }
    };

}
