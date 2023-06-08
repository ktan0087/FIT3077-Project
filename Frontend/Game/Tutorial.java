package Frontend.Game;

import Frontend.Button.BtnClose;
import Frontend.Button.BtnNext;
import Frontend.Components.Click;
import Frontend.Components.Instruction;
import Frontend.Components.IntersectionPoint;
import Frontend.Layer.DimLayer;
import Frontend.Utils.IconProcessor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is used to create the tutorial to guide the user to play the Nine Men's Morris game.
 *
 * @see BtnNext
 * @see BtnClose
 * @see InitialBoard
 * @see WhiteToken
 * @see BlackToken
 * @see Click
 */
public class Tutorial extends JPanel{
    /**
     * nextCount is used to keep track of the number of times the next button is clicked
     */
    private int nextCount;

    /**
     * btnNext is the next button to continue the tutorial
     */
    private BtnNext btnNext;

    /**
     * btnClose is the close button to close the tutorial and go back to the main menu
     */
    protected BtnClose btnClose;

    /**
     * initialBoard is the initial board that will be presented in the tutorial
     */
    protected InitialBoard initialBoard;

    /**
     * dimLayer is the dim layer that will be used to dim the background
     */
    private JPanel dimLayer;

    /**
     * labelLayer is the label layer that will be used to display the instructions and supportive images
     */
    private JPanel labelLayer;

    /**
     * The background of the tutorial
     */
    private JPanel background;

    /**
     * The white token that layer is 3, and position is 7
     */
    private WhiteToken whiteToken_37;

    /**
     * The black token that layer is 3, and position is 8
     */
    private BlackToken blackToken_38;

    /**
     * The white token that layer is 1, and position is 8
     */
    private WhiteToken whiteToken_18;

    /**
     * Create a click icon to assist the user to click the token/intersection/buttons.
     */
    private JLabel click;

    /**
     * Create a left arrow icon to hint the user
     */
    private JLabel leftArrow;

    /**
     * Create a right arrow icon to hint the user
     */
    private JLabel rightArrow;
    public Tutorial() {
        // Create the components of the tutorial
        this.nextCount = 0; // count how many times the next button is clicked
        this.btnClose = new BtnClose(); // create the close button
        this.btnNext = new BtnNext(); // create the next button
        this.initialBoard = new InitialBoard(); // create the initial board
        this.initialBoard.remove(this.initialBoard.buttons.btnClose); // remove the close button from the initial board
        this.labelLayer = createLabelLayer(); // create the label layer
        this.dimLayer = new DimLayer(); // create the dim layer
        this.click = new Click(); // create the click image
        this.leftArrow = createArrowLeft(); // create the left arrow image
        this.rightArrow = createArrowRight(); // create the right arrow image

        this.setLayout(new GridBagLayout()); // set the layout of the tutorial
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(17, 10, 17, 10); // add gaps between the components

        // add the close button to the top right of the tutorial
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        this.add(this.btnClose, gbc);

        // add the next button to the bottom right of the tutorial
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        this.add(this.btnNext, gbc);

        // add the welcome message to the center of the tutorial
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(createWelcomeMessage(), gbc);

        // add the initial board to the center of the tutorial
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.background = createBackground();
        this.add(this.background, gbc);

        this.btnNext.addActionListener(new ActionListener() {
            /**
             * Navigate users to the next tutorial page
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                nextCount++;
                checkNext(gbc); // check which page to go to
                repaint();
                revalidate();
            }
        });
    }

    /**
     * Get initial board.
     *
     * @return initial board
     */
    public InitialBoard getInitialBoard() {
        return initialBoard;
    }

    /**
     * Check which page to navigate to.
     *
     * @param gbc the grid bag constraints
     */
    private void checkNext(GridBagConstraints gbc){
        if (this.nextCount == 1){
            //Page 1: Place token introduction

            this.remove(2); // remove welcome message
            createIntro("Place Token");
        }
        else if (this.nextCount == 2){
            // Page 2: Instruct user to place a white token

            this.labelLayer.removeAll(); // remove intro
            this.background.remove(this.dimLayer);

            JLabel instruction = createInstruction(420, 80);
            instruction.setText("Click to place a token");
            instruction.setLocation(720, 180);
            this.labelLayer.add(instruction);

            this.click.setBounds(685, 250, 48, 48); // set the position of the click image
            this.labelLayer.add(this.click);

            this.remove(this.btnNext); // remove next button

            // Enable the intersection at layer 3, position 3, that is index 8 in the intersection list
            // The intersection is inserted to the intersection list from left to right, top to bottom
            // For example, the top left intersection is at layer 1, position 1, thus the index in the intersection list is 0
            this.initialBoard.board.getIntersectionList().get(8).inter.setEnabled(true);
            this.initialBoard.board.getIntersectionList().get(8).inter.addActionListener(new ActionListener() {
                /**
                 * Page 3: Instruct user to the next page after place token tutorial
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    labelLayer.removeAll();

                    addNextButton(gbc); // add next button

                    repaint();
                    revalidate();
                }
            });
        }
        else if (this.nextCount == 3){
            // Page 4: Show the condition of moving a token

            // Set up the board for move token tutorial
            this.initialBoard.getGame().getGameMode().displayBoardMove();
            this.initialBoard.playerTurn.changeIcon();
            this.initialBoard.instruction.changeText(Instruction.InstructionType.MOVE);

            JLabel instruction = createInstruction(350, 125);
            instruction.setText("<html><center>After you place all<br/>your tokens ...<center></html>");
            instruction.setLocation(15, 485);
            this.labelLayer.add(instruction);

            this.leftArrow.setBounds(82, 380, 100, 100);
            this.labelLayer.add(this.leftArrow);

            // Set up the board for move token tutorial, by adding tokens to the board
            this.addToken(new BlackToken(1, 3, this.initialBoard));
            this.addToken(new BlackToken(2, 2, this.initialBoard));
            this.addToken(new BlackToken(3, 1, this.initialBoard));
            this.addToken(new WhiteToken(2, 3, this.initialBoard));
            this.addToken(new WhiteToken(1, 8, this.initialBoard));
            this.addToken(new BlackToken(2, 8, this.initialBoard));
            this.blackToken_38 = new BlackToken(3, 8, this.initialBoard);
            this.addToken(this.blackToken_38);
            this.addToken(new WhiteToken(3, 4, this.initialBoard));
            this.addToken(new BlackToken(2, 4, this.initialBoard));
            this.addToken(new WhiteToken(1, 4, this.initialBoard));
            this.whiteToken_37 = new WhiteToken(3, 7, this.initialBoard);
            this.addToken(this.whiteToken_37);
            this.addToken(new WhiteToken(2, 6, this.initialBoard));
            this.addToken(new BlackToken(3, 5, this.initialBoard));
            this.addToken(new BlackToken(2, 5, this.initialBoard));
            this.addToken(new BlackToken(1, 7, this.initialBoard));
            this.addToken(new WhiteToken(1, 6, this.initialBoard));
            this.addToken(new WhiteToken(1, 5, this.initialBoard));

            // Set up the token remainders
            initialBoard.blackTokenRemain.decreaseAmountToken(initialBoard.getGame().getPlayer2());
            initialBoard.whiteTokenRemain.decreaseAmountToken(initialBoard.getGame().getPlayer1());
        }
        else if (this.nextCount == 4){
            // Page 5: Move token introduction

            // remove instruction and left arrow
            this.labelLayer.removeAll();

            createIntro("Move Token");

            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);
        }
        else if (this.nextCount == 5){
            // Page 6: Instruct user to select a white token to move

            this.labelLayer.removeAll(); // remove intro label
            this.background.remove(this.dimLayer);
            this.remove(this.btnNext); // remove next button

            JLabel instruction = createInstruction(380, 125);
            instruction.setText("<html><center>Select the token that<br/>you want to move<center></html>");
            instruction.setLocation(585, 280);
            this.labelLayer.add(instruction);

            // Set the location for the clicking arrow
            this.click.setBounds(540, 394, 48, 48);
            this.labelLayer.add(this.click);

            ActionListener whiteTokenTutAction = (new ActionListener() {
                /**
                 * When a white token is selected, the next button will be added
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    labelLayer.removeAll();

                    addNextButton(gbc);

                    initialBoard.board.getIntersectionList().get(16).inter.setEnabled(false); // disable the intersection
                    initialBoard.board.getIntersectionList().get(16).inter.removeActionListener(this); // remove the action listener

                    repaint();
                    revalidate();
                }
            });

            this.whiteToken_37.addMouseListener(this.whiteToken_37.tokenSelected); // add tokenSelected mouse listener to the token

            this.whiteToken_37.addMouseListener(new MouseAdapter() {
                /**
                 * Page 7: Instruct user to move the selected token
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    labelLayer.remove(instruction);
                    labelLayer.remove(click);

                    click.setBounds(613, 394, 48, 48);
                    labelLayer.add(click);

                    JLabel instruction = createInstruction(380, 125);
                    instruction.setText("<html><center>Move to the empty<br/>adjacent intersection<center></html>");
                    instruction.setLocation(650, 265);
                    labelLayer.add(instruction);

                    whiteToken_37.removeMouseListener(whiteToken_37.tokenSelected);
                    whiteToken_37.removeMouseListener(this);

                    initialBoard.board.getIntersectionList().get(16).inter.setEnabled(true);
                    initialBoard.board.getIntersectionList().get(16).inter.addActionListener(whiteTokenTutAction);

                    repaint();
                    revalidate();
                }
            });
        }
        else if (this.nextCount == 6){
            // Page 8: Remove token introduction

            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);

            createIntro("Remove Token");
        }
        else if (this.nextCount == 7) {
            // Page 9: Instruct user to form a mill

            this.background.remove(this.dimLayer);
            this.labelLayer.removeAll(); // remove intro label

            JLabel instruction = createInstruction(280, 80);
            instruction.setText("A Mill Formed");
            instruction.setLocation(700, 550);
            this.labelLayer.add(instruction);

            this.leftArrow.setBounds(670, 440, 100, 100);
            this.labelLayer.add(this.leftArrow);
        }
        else if (this.nextCount == 8){
            // Page 10: Instruct user to remove opponent's token

            this.labelLayer.removeAll();
            this.remove(this.btnNext); // remove next button

            JLabel instruction = createInstruction(300, 165);
            instruction.setText("<html><center><h1>Click to remove your<br/>opponent's token</h1><h3>NOTE: You cannot remove your<br/>opponent's token that is in mill</h3></html>");
            instruction.setLocation(450, 150);
            this.labelLayer.add(instruction);

            this.click.setBounds(540, 323, 48, 48);
            this.labelLayer.add(this.click);

            this.blackToken_38.addMouseListener(this.blackToken_38.tokenSelected); // add tokenSelected mouse listener to the token
            this.blackToken_38.addMouseListener(new MouseAdapter() {
                /**
                 * Page 11: Show the board after removing the opponent's token
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    labelLayer.removeAll();

                    addNextButton(gbc);

                    blackToken_38.removeMouseListener(this);

                    repaint();
                    revalidate();
                }
            });
        }
        else if (this.nextCount == 9) {
            // Page 12: Show the condition of flying token

            initialBoard.playerTurn.changeIcon(); // change the player turn icon
            this.initialBoard.instruction.changeText(Instruction.InstructionType.FLY); // change the instruction text

            JLabel instruction = createInstruction(380, 165);
            instruction.setText("<html><center>When you only have<br/><b>3</b> tokens left ...<center></html>");
            instruction.setLocation(150, 505);
            this.labelLayer.add(instruction);

            this.rightArrow.setBounds(435, 400, 100, 100);
            this.labelLayer.add(this.rightArrow);

            // Remove the mill
            this.initialBoard.removeMill(Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(1, 6))), Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(2, 6))), Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(3, 6))), this.initialBoard.millLayer);

            // Set up the board for flying token
            this.restartBoard();
            this.addToken(new BlackToken(1, 3, this.initialBoard));
            this.addToken(new BlackToken(1, 5, this.initialBoard));
            this.addToken(new BlackToken(1, 6, this.initialBoard));
            this.addToken(new WhiteToken(2, 6, this.initialBoard));
            this.addToken(new WhiteToken(3, 6, this.initialBoard));
            this.whiteToken_37 = new WhiteToken(3, 7, this.initialBoard);
            this.addToken(this.whiteToken_37);
            this.addToken(new BlackToken(1, 8, this.initialBoard));
            this.addToken(new BlackToken(2, 8, this.initialBoard));
            this.addToken(new BlackToken(3, 8, this.initialBoard));

            this.initialBoard.addMill(Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(1, 8))), Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(2, 8))), Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(3, 8))), this.initialBoard.millLayer);
        }
        else if (nextCount == 10) {
            // Page 13: Fly token introduction

            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);
            this.labelLayer.removeAll();

            createIntro("Fly Token");
        }
        else if (nextCount == 11) {
            // Page 14: Instruct user to select a white token to fly

            initialBoard.getGame().getGameMode().displayBoardFly();

            this.background.remove(this.dimLayer);
            this.labelLayer.removeAll(); // remove the intro label
            this.remove(this.btnNext);

            JLabel instruction = createInstruction(380, 165);
            instruction.setText("<html><center>Select the token that<br/>you want to fly<center></html>");
            instruction.setLocation(220, 455);
            this.labelLayer.add(instruction);

            // Set the location for the clicking arrow
            this.click.setBounds(540, 394, 48, 48);
            this.labelLayer.add(this.click);

            ActionListener whiteTokenTutAction = new ActionListener() {
                /**
                 * Page 16: Show the board after flying the token
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    initialBoard.instruction.changeText(Instruction.InstructionType.MOVE);

                    labelLayer.remove(0);
                    labelLayer.remove(click);

                    addNextButton(gbc);

                    initialBoard.board.getIntersectionList().get(14).inter.removeActionListener(this);

                    repaint();
                    revalidate();
                }
            };

            this.whiteToken_37.addMouseListener(this.whiteToken_37.tokenSelected);
            this.whiteToken_37.addMouseListener(new MouseAdapter() {
                /**
                 * Page 15: Instruct user to fly the selected token to an empty intersection
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    labelLayer.remove(instruction);
                    labelLayer.remove(click);

                    JLabel instruction = createInstruction(380, 165);
                    instruction.setText("<html><center>Fly to any empty<br/>intersection<center></html>");
                    instruction.setLocation(720, 400);
                    labelLayer.add(instruction);

                    // Set the location for the clicking arrow
                    click.setBounds(829, 321, 48, 48);
                    labelLayer.add(click);

                    whiteToken_37.removeMouseListener(this);
                    initialBoard.board.getIntersectionList().get(14).inter.setEnabled(true);
                    initialBoard.board.getIntersectionList().get(14).inter.addActionListener(whiteTokenTutAction);

                    repaint();
                    revalidate();
                }
            });
        }
        else if (nextCount == 12) {
            // Page 17: Win condition introduction

            this.initialBoard.playerTurn.changeIcon();
            this.initialBoard.instruction.changeText(Instruction.InstructionType.MOVE);

            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);

            // Set up the board for win condition
            this.restartBoard();
            this.whiteToken_18 = new WhiteToken(1, 8, this.initialBoard);
            this.addToken(this.whiteToken_18);
            this.addToken(new WhiteToken(1, 5, this.initialBoard));
            this.addToken(new WhiteToken(1, 6, this.initialBoard));
            this.addToken(new WhiteToken(2, 3, this.initialBoard));
            this.addToken(new WhiteToken(2, 6, this.initialBoard));
            this.addToken(new BlackToken(2, 8, this.initialBoard));
            this.addToken(new BlackToken(3, 8, this.initialBoard));

            createIntro("Win Condition");
        }
        else if (nextCount == 13) {
            // Page 18: Show the condition of win the Nine Men's Morris game

            this.background.remove(this.dimLayer);
            this.labelLayer.removeAll(); // remove the intro label

            this.initialBoard.removeMill(Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(1, 8))), Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(2, 8))), Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(3, 8))), this.initialBoard.millLayer);

            JLabel instruction = createInstruction(380, 125);
            instruction.setText("<html><center>When your opponent<br/>only has 2 tokens ...<center></html>");
            instruction.setLocation(575, 445);
            this.labelLayer.add(instruction);

            this.leftArrow.setBounds(600, 328, 100, 100);
            this.labelLayer.add(this.leftArrow);
        }
        else if (nextCount == 14) {
            // Page 19: Show the win result page

            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);

            this.labelLayer.removeAll();

            JLabel whiteWin = createIntro("WIN");
            whiteWin.setIcon(new ImageIcon(getClass().getResource("/Icons/white-token.png")));
            whiteWin.setHorizontalTextPosition(JLabel.RIGHT);
            whiteWin.setIconTextGap(45); // set the distance between text and icon
        }
        else if (nextCount == 15) {
            // Page 20: Hint button introduction

            initialBoard.getGame().getGameMode().displayBoardButton();

            this.labelLayer.removeAll(); // remove the intro label
            createIntro("Button: Hint");

            // Set up the board for hint button
            this.addToken(new BlackToken(1, 3, this.initialBoard));
            this.addToken(new WhiteToken(1, 4, this.initialBoard));
            this.addToken(new BlackToken(2, 2, this.initialBoard));
            this.addToken(new BlackToken(3, 1, this.initialBoard));
            this.addToken(new WhiteToken(3, 3, this.initialBoard));
            this.addToken(new WhiteToken(3, 4, this.initialBoard));
            this.addToken(new BlackToken(3, 5, this.initialBoard));
            this.addToken(new BlackToken(3, 6, this.initialBoard));
            this.addToken(new WhiteToken(3, 7, this.initialBoard));
            this.addToken(new BlackToken(2, 4, this.initialBoard));
            this.addToken(new WhiteToken(1, 5, this.initialBoard));
        }
        else if (nextCount == 16){
            // Page 21: Explain all hint situation in the game

            this.labelLayer.removeAll(); // remove the intro label
            createIntro("<html><h1>Place Phase:</h1>" +
                    "<h2>During the Place Phase, you can quickly find possible placements by" +
                    "<br/>selecting the hint button. This will highlight empty intersections with a red" +
                    "<br/>circle, giving you hints on where you can place your token.</h2>" +
                    "<h1>Move/Fly Phase:</h1>" +
                    "<h2>In the Move/Fly Phase, start by selecting a token. Afterward, click on the" +
                    "<br/>hint button to reveal available moves. Legal intersections for the selected" +
                    "<br/>token will be indicated by a red circle, helping you plan your next move" +
                    "<br/>strategically.</h2>" +
                    "<h1>Remove Phase:</h1>" +
                    "<h2>When it's time for the Remove Phase, simply select the hint button to" +
                    "<br/>receive valuable hints. This will highlight opponent's tokens that are not" +
                    "<br/>part of any mill with a red circle, making it easier for you to identify which" +
                    "<br/>tokens can be removed.</h2></html>", 700, 550);
        }
        else if (nextCount == 17) {
            // Page 22: Show the hint button example at move/fly phase in the game

            this.labelLayer.removeAll(); // remove the intro label
            createIntro("Example: Hint in Move Phase", 700, 110);
        }
        else if (nextCount == 18) {
            // Page 23: Instruct user to select the token that he/she wants to move

            this.background.remove(this.dimLayer);
            this.labelLayer.removeAll();
            this.remove(this.btnNext);

            JLabel instruction = createInstruction(300, 165);
            instruction.setText("<html><center><h1>Select your token</h1><h3>NOTE: This step is only<br/>needed in moving<br/> and flying phase</h3><center></html>");
            instruction.setLocation(450, 380);
            this.labelLayer.add(instruction);

            this.click.setBounds(398, 324, 48, 48);
            this.labelLayer.add(this.click);

            // The intersection that the user needs to move to (click)
            IntersectionPoint intersect_17 = initialBoard.board.getIntersectionList().get(21).inter;

            ActionListener intersectionTutAction = new ActionListener() {
                /**
                 * Show the board after moving the token according to the hint given
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    initialBoard.instruction.changeText(Instruction.InstructionType.REMOVE);

                    labelLayer.removeAll();

                    addNextButton(gbc);

                    intersect_17.removeActionListener(this);
                    intersect_17.setEnabled(false);

                    repaint();
                    revalidate();
                }
            };

            ActionListener hintTutAction = (new ActionListener() {
                /**
                 * Page 25: Instruct user to click the hint button to show all legal moves and move to the intersection
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    labelLayer.remove(0); // remove the instruction label
                    labelLayer.remove(click);

                    JLabel instructionFirst = createInstruction(300, 150);
                    instructionFirst.setText("<html><center>1. Click again to<br/>hide the hints<center></html>");
                    instructionFirst.setLocation(950, 100);
                    labelLayer.add(instructionFirst);

                    click.setBounds(1130, 40, 48, 48);
                    labelLayer.add(click);

                    JLabel instructionSecond = createInstruction(450, 150);
                    instructionSecond.setText("<html><center>2. Or move to any empty<br/>adjacent intersection<center></html>");
                    instructionSecond.setLocation(30, 375);
                    labelLayer.add(instructionSecond);

                    Click secondClick = new Click();
                    secondClick.setBounds(398, 538, 48, 48);
                    labelLayer.add(secondClick);

                    initialBoard.buttons.btnHint.removeActionListener(this);

                    intersect_17.setEnabled(true);
                    intersect_17.addActionListener(intersectionTutAction);

                    repaint();
                    revalidate();
                }
            });

            this.whiteToken_18.addMouseListener(this.whiteToken_18.tokenSelected);
            this.whiteToken_18.addMouseListener(new MouseAdapter() {
                /**
                 * Page 24: Instruct user to click on the hint button
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    initialBoard.buttons.btnHint.setVisible(true);

                    labelLayer.remove(0); // remove the instruction label
                    labelLayer.remove(click);

                    JLabel instruction = createInstruction(300, 165);
                    instruction.setText("<html><center>Click to show all<br/>legal moves<center></html>");
                    instruction.setLocation(950, 100);
                    labelLayer.add(instruction);

                    click.setBounds(1130, 40, 48, 48);
                    labelLayer.add(click);

                    initialBoard.buttons.btnHint.addActionListener(hintTutAction);

                    whiteToken_18.removeMouseListener(this);

                    repaint();
                    revalidate();
                }
            });

        }
        else if (nextCount == 19) {
            // Page 26: Restart button introduction

            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);

            createIntro("Button: Restart");
        }
        else if (nextCount == 20) {
            // Page 27: Instruct user to click the restart button

            this.background.remove(this.dimLayer);
            this.labelLayer.removeAll();
            this.remove(this.btnNext);

            this.initialBoard.buttons.btnRestart.setVisible(true);

            JLabel instruction = createInstruction(300, 165);
            instruction.setText("<html><center>Click to restart<br/>the game<center></html>");
            instruction.setLocation(950, 100);
            labelLayer.add(instruction);

            click.setBounds(1130, 40, 48, 48);
            labelLayer.add(click);

            this.initialBoard.buttons.btnRestart.addActionListener(new ActionListener() {
                /**
                 * Page 28: Show the board after restarting the game
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    // A prompt to ensure users want to restart the game
                    String message = "Do you want to restart the game?";
                    String title = "NINE MEN'S MORRIS";
                    int result = JOptionPane.showConfirmDialog(initialBoard.buttons.btnRestart, message, title, JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION){
                        labelLayer.removeAll();
                        addNextButton(gbc);

                        initialBoard.placeToken.setVisible(false);
                        initialBoard.millLayer.setVisible(false);

                        initialBoard.playerTurn.changeIcon();
                        initialBoard.blackTokenRemain.setText("9");
                        initialBoard.whiteTokenRemain.setText("9");
                        initialBoard.instruction.changeText(Instruction.InstructionType.PLACE);

                        repaint();
                        revalidate();
                    }
                }
            });
        }
        else if (nextCount == 21) {
            // Page 28: Inform user that the tutorial is almost finished

            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);

            createIntro("You are ready to play!");
        }
        else if (nextCount == 22) {
            // Page 29: Instruct user to click the exit button and bring user back to the main menu

            this.background.remove(this.dimLayer);
            this.labelLayer.removeAll();
            this.remove(this.btnNext);

            JLabel instruction = createInstruction(300, 165);
            instruction.setText("<html><center>Click to exit<br/>the game<center></html>");
            instruction.setLocation(950, 150);
            this.labelLayer.add(instruction);

            this.rightArrow.setBounds(1085, 35, 100, 100);
            this.labelLayer.add(this.rightArrow);
        }
    }

    /**
     * A method to restart the board.
     */
    private void restartBoard(){
        for (int i = 0; i < this.initialBoard.getTokenList().size(); i++){
            int layer = this.initialBoard.getTokenList().get(i).getCoordinateX();
            int position = this.initialBoard.getTokenList().get(i).getCoordinateY();
            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(layer, position))));
            this.initialBoard.placeToken.add(new JLabel(), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(layer, position))));
        }
        this.initialBoard.getTokenList().clear();
    }

    /**
     * A method to add a token to the board.
     *
     * @param token the token to be added
     */
    private void addToken(Token token){
        this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(token.getCoordinateX(), token.getCoordinateY()))));
        this.initialBoard.placeToken.add(token, Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(token.getCoordinateX(), token.getCoordinateY()))));
        this.initialBoard.getTokenList().add(token);
        token.setIndex(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(token.getCoordinateX(), token.getCoordinateY())))); // set the index of the white token
    }

    /**
     * A method to create a background for the tutorial,
     * including a label layer to place instruction and support image,
     * a dim layer to show the information clearer
     * an initial board to guide user how to play the game.
     *
     * @return the background
     */
    private JPanel createBackground(){
        JPanel background = new JPanel(){
            @Override
            public boolean isOptimizedDrawingEnabled(){
                return false;
            }
        };
        background.setLayout(new OverlayLayout(background));

        this.initialBoard.setEnabled(false); // make buttons not enabled
        this.initialBoard.buttons.btnRestart.setVisible(false);
        this.initialBoard.buttons.btnHint.setVisible(false);

        for (Intersection intersection : this.initialBoard.board.getIntersectionList()){
            intersection.inter.setEnabled(false);
        }

        background.add(this.labelLayer);
        background.add(this.dimLayer);
        background.add(this.initialBoard);

        return background;
    }

    /**
     * A method to create a dim layer to show the information clearer.
     *
     * @return a dim layer
     */
    private JPanel createDimLayer(){
        JPanel dimLayer = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                g.setColor(new Color(0x80000000, true));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        dimLayer.setOpaque(false);
        dimLayer.setVisible(true);
        return dimLayer;
    }

    /**
     * A method to create a label layer to place instruction and support image.
     *
     * @return a label layer
     */
    private JPanel createLabelLayer(){
        JPanel labelLayer = new JPanel();
        labelLayer.setLayout(null);
        labelLayer.setOpaque(false);
        labelLayer.setVisible(true);

        return labelLayer;
    }

    /**
     * A method to place the label in the center of the frame.
     *
     * @param label the instruction
     */
    public void setComponentCenter(JLabel label) {
        // Set the size of the label
        label.setSize(label.getPreferredSize());

        // Get the frame's width and height
        double frameSizeWidth = 1280.0;
        double frameSizeHeight = 720.0;

        // Calculate the center position for the label
        int labelX = (int) ((frameSizeWidth - label.getWidth()) / 2);
        int labelY = (int) ((frameSizeHeight - label.getHeight()) / 2);

        // Set the position of the label
        label.setLocation(labelX, labelY);
    }

    /**
     * A method to create a JPanel consists of welcome message.
     *
     * @return a welcome message
     */
    private JPanel createWelcomeMessage(){
        JLabel welcome = new JLabel();
        welcome.setText("Welcome to the tutorial");
        welcome.setFont(new Font("Inter", Font.PLAIN, 42));
        welcome.setBackground(new Color(0xF4E3D3));
        welcome.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0xE27408)));
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setOpaque(true);

        JLabel message = new JLabel();
        message.setText("You are ");
        message.setFont(new Font("Inter", Font.PLAIN, 42));
        message.setBackground(new Color(0xF4E3D3));
        message.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0xE27408)));
        message.setOpaque(true);

        ImageIcon whiteToken = new ImageIcon(Tutorial.class.getResource("/Icons/white-token.png"));
        IconProcessor icon = new IconProcessor(whiteToken, 68, 68);
        ImageIcon resizedIcon = icon.resizeIcon();

        welcome.setPreferredSize(new Dimension(500, 110));
        message.setPreferredSize(new Dimension(500, 110));

        message.setIcon(resizedIcon);
        message.setHorizontalTextPosition(JLabel.LEFT);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setIconTextGap(45); // set the distance between text and icon

        JPanel welcomeMessage = new JPanel();
        welcomeMessage.setOpaque(false);
        welcomeMessage.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 25, 25, 25); // add gaps between the components

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        welcomeMessage.add(welcome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        welcomeMessage.add(message, gbc);

        return welcomeMessage;
    }

    /**
     * A method to create a JLabel consists of the introduction of the game.
     *
     * @param text the introduction information
     * @return the introduction JLabel
     */
    private JLabel createIntro(String text){
        JLabel intro = new JLabel();
        intro.setFont(new Font("Inter", Font.PLAIN, 42));
        intro.setText(text);
        intro.setBackground(new Color(0xF4E3D3));

        intro.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0xE27408)));
        intro.setHorizontalAlignment(JLabel.CENTER);
        intro.setOpaque(true);

        intro.setPreferredSize(new Dimension(500, 110));

        setComponentCenter(intro);
        labelLayer.add(intro);

        return intro;
    }

    /**
     * A method to create a JLabel consists of the introduction of the game,
     * which can its size can be adjusted through parameters.
     *
     * @param text the introduction information
     * @param width the width of the JLabel
     * @param height the height of the JLabel
     * @return the introduction JLabel with the information and size given
     */
    private JLabel createIntro(String text, int width, int height){
        JLabel intro = new JLabel();
        intro.setFont(new Font("Inter", Font.PLAIN, 42));
        intro.setText(text);
        intro.setBackground(new Color(0xF4E3D3));

        intro.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0xE27408)));
        intro.setHorizontalAlignment(JLabel.CENTER);
        intro.setOpaque(true);

        intro.setPreferredSize(new Dimension(width, height));

        setComponentCenter(intro);
        labelLayer.add(intro);

        return intro;
    }

    /**
     * A method to create a JLabel consists of the instruction of the game.
     *
     * @param width the width of the JLabel
     * @param height the height of the JLabel
     * @return the instruction JLabel with the size given
     */
    private JLabel createInstruction(int width, int height){
        JLabel instruction = new JLabel();
        instruction.setFont(new Font("Inter", Font.PLAIN, 36));
        instruction.setBackground(new Color(0xF4E3D3));

        Border matteBorder = new MatteBorder(5, 5, 5, 5, new Color(0xFF0000));
        Border emptyBorder = new EmptyBorder(10, 10, 10, 10);
        instruction.setBorder(BorderFactory.createCompoundBorder(matteBorder, emptyBorder));

        instruction.setHorizontalAlignment(JLabel.CENTER);
        instruction.setOpaque(true);

        instruction.setPreferredSize(new Dimension(width, height));
        instruction.setSize(new Dimension(width, height));

        return instruction;
    }

    /**
     * A method to create the left arrow image on the tutorial to hint the user with the provided instruction.
     *
     * @return a JLabel consists of a left arrow image
     */
    private JLabel createArrowLeft(){
        JLabel arrowLeft = new JLabel();
        arrowLeft.setOpaque(false);

        ImageIcon arrow = new ImageIcon(Tutorial.class.getResource("/Icons/arrow-left.png"));
        IconProcessor arrowIcon = new IconProcessor(arrow, 100, 100);
        ImageIcon resizedIcon = arrowIcon.resizeIcon();

        arrowLeft.setIcon(resizedIcon);

        return arrowLeft;
    }

    /**
     * A method to create the right arrow image on the tutorial to hint the user with the provided instruction.
     *
     * @return a JLabel consists of a right arrow image
     */
    private JLabel createArrowRight(){
        JLabel arrowRight = new JLabel();
        arrowRight.setOpaque(false);

        ImageIcon arrow = new ImageIcon(Tutorial.class.getResource("/Icons/arrow-right.png"));
        IconProcessor arrowIcon = new IconProcessor(arrow, 100, 100);
        ImageIcon resizedIcon = arrowIcon.resizeIcon();

        arrowRight.setIcon(resizedIcon);

        return arrowRight;
    }

    /**
     * A method to create the next button to navigate user to the next page.
     *
     * @param gbc the GridBagConstraints to set the position of the button
     */
    private void addNextButton(GridBagConstraints gbc){
        gbc.insets = new Insets(25, 10, 25, 10); // add gaps between the components

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        add(btnNext, gbc);
        setComponentZOrder(btnNext, 1);
    }

}
