package Frontend.Game;

import Frontend.Button.BtnClose;
import Frontend.Button.BtnNext;
import Frontend.Click;
import Frontend.Components.TokenRemain;
import Frontend.IconProcessor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tutorial extends JPanel{
    private int nextCount;
    private BtnNext btnNext;
    protected BtnClose btnClose;
    protected InitialBoard initialBoard;
    private JPanel dimLayer;
    private JPanel labelLayer;
    private JPanel background;
    private WhiteToken whiteToken_37;
    private BlackToken blackToken_38;
    private WhiteToken whiteToken_18;
    private JLabel click;
    private JLabel leftArrow;
    private JLabel rightArrow;
    private JPanel btnCloseLayer;
    private JLabel btnCloseClick = createClickHint();;
    public Tutorial(){
        this.nextCount = 0;
        this.btnClose = new BtnClose();
        this.btnNext = new BtnNext();
        this.initialBoard = new InitialBoard();
        this.initialBoard.remove(this.initialBoard.buttons.btnClose);
        this.labelLayer = createLabelLayer();
        this.dimLayer = createDimLayer();
        this.click = createClickHint();
        this.leftArrow = createArrowLeft();
        this.rightArrow = createArrowRight();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(17, 10, 17, 10); // add gaps between the components

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.fill = GridBagConstraints.NONE;
        this.add(this.btnClose, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        this.add(this.btnNext, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(createWelcomeMessage(), gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.background = createBackground();
        this.add(this.background, gbc);

        this.btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextCount++;
                checkNext(gbc);
                repaint();
                revalidate();
            }
        });
    }

    public InitialBoard getInitialBoard() {
        return initialBoard;
    }

    private void checkNext(GridBagConstraints gbc){
        if (this.nextCount == 1){
            this.remove(2); // remove welcome message
            createIntro("Place Token");
        }
        else if (this.nextCount == 2){
            this.labelLayer.remove(0); // remove intro
            this.background.remove(this.dimLayer);

            JLabel instruction = createInstruction(420, 80);
            instruction.setText("Click to place a token");
            instruction.setLocation(720, 180);
            this.labelLayer.add(instruction);

            this.click.setBounds(685, 250, 48, 48);
            this.labelLayer.add(this.click);

            this.remove(this.btnNext); // remove next button

            this.initialBoard.board.getIntersectionList().get(8).inter.setEnabled(true);
            this.initialBoard.board.getIntersectionList().get(8).inter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    labelLayer.remove(instruction);
                    labelLayer.remove(click);

                    addNextButton(gbc);

                    repaint();
                    revalidate();
                }
            });
        }
        else if (this.nextCount == 3){
            this.initialBoard.getGame().getGameMode().displayBoardMove();
            this.initialBoard.playerTurn.changeIcon();

            JLabel instruction = createInstruction(350, 125);
            instruction.setText("<html><center>After you place all<br/>your tokens ...<center></html>");
            instruction.setLocation(15, 485);
            this.labelLayer.add(instruction);

            this.leftArrow.setBounds(82, 380, 100, 100);
            this.labelLayer.add(this.leftArrow);

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

            initialBoard.blackTokenRemain.decreaseAmountToken(initialBoard.getGame().getPlayer2());
            initialBoard.whiteTokenRemain.decreaseAmountToken(initialBoard.getGame().getPlayer1());
        }
        else if (this.nextCount == 4){
            // remove instruction and left arrow
            this.labelLayer.remove(0);
            this.labelLayer.remove(this.leftArrow);

            createIntro("Move Token");

            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);
        }
        else if (this.nextCount == 5){
            this.labelLayer.remove(0); // remove intro label
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
                @Override
                public void actionPerformed(ActionEvent e) {
                    labelLayer.remove(1); // remove instruction
                    labelLayer.remove(click);

                    addNextButton(gbc);

                    repaint();
                    revalidate();
                }
            });

            this.whiteToken_37.addMouseListener(this.whiteToken_37.tokenSelected);
            this.whiteToken_37.addMouseListener(new MouseAdapter() {
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

                    whiteToken_37.removeMouseListener(this);
                    initialBoard.board.getIntersectionList().get(16).inter.setEnabled(true);
                    initialBoard.board.getIntersectionList().get(16).inter.addActionListener(whiteTokenTutAction);

                    repaint();
                    revalidate();
                }
            });
        }
        else if (this.nextCount == 6){
            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);

            createIntro("Remove Token");
        }
        else if (this.nextCount == 7) {
            this.background.remove(this.dimLayer);
            this.labelLayer.remove(0); // remove intro label

            JLabel instruction = createInstruction(280, 80);
            instruction.setText("A Mill Formed");
            instruction.setLocation(700, 550);
            this.labelLayer.add(instruction);

            this.leftArrow.setBounds(670, 440, 100, 100);
            this.labelLayer.add(this.leftArrow);
        }
        else if (this.nextCount == 8){
            this.labelLayer.remove(0); // remove intro label
            this.labelLayer.remove(this.leftArrow);
            this.remove(this.btnNext); // remove next button

            JLabel instruction = createInstruction(300, 165);
            instruction.setText("<html><center><h1>Click to remove your<br/>opponent's token</h1><h3>NOTE: You cannot remove your<br/>opponent's token that is in mill</h3></html>");
            instruction.setLocation(450, 150);
            this.labelLayer.add(instruction);

            this.click.setBounds(540, 323, 48, 48);
            this.labelLayer.add(this.click);

            this.blackToken_38.addMouseListener(this.blackToken_38.tokenSelected);
            this.blackToken_38.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    labelLayer.remove(instruction);
                    labelLayer.remove(click);

                    addNextButton(gbc);

                    repaint();
                    revalidate();
                }
            });
        }
        else if (this.nextCount == 9) {
            initialBoard.playerTurn.changeIcon();
            JLabel instruction = createInstruction(380, 165);
            instruction.setText("<html><center>When you only have<br/><b>3</b> tokens left ...<center></html>");
            instruction.setLocation(150, 505);
            this.labelLayer.add(instruction);

            this.rightArrow.setBounds(435, 400, 100, 100);
            this.labelLayer.add(this.rightArrow);

            this.initialBoard.removeMill(Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(1, 6))), Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(2, 6))), Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(3, 6))), this.initialBoard.millLayer);

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
            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);
            this.labelLayer.remove(0); // remove the intro label
            this.labelLayer.remove(this.rightArrow);

            createIntro("Fly Token");
        }
        else if (nextCount == 11) {
            initialBoard.getGame().getGameMode().displayBoardFly();

            this.background.remove(this.dimLayer);
            this.labelLayer.remove(0); // remove the intro label
            this.remove(this.btnNext);

            JLabel instruction = createInstruction(380, 165);
            instruction.setText("<html><center>Select the token that<br/>you want to fly<center></html>");
            instruction.setLocation(220, 455);
            this.labelLayer.add(instruction);

            // Set the location for the clicking arrow
            this.click.setBounds(540, 394, 48, 48);
            this.labelLayer.add(this.click);

            ActionListener whiteTokenTutAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    labelLayer.remove(0);
                    labelLayer.remove(click);

                    addNextButton(gbc);

                    repaint();
                    revalidate();
                }
            };

            this.whiteToken_37.addMouseListener(this.whiteToken_37.tokenSelected);
            this.whiteToken_37.addMouseListener(new MouseAdapter() {
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
            this.initialBoard.playerTurn.changeIcon();
            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);

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
            this.background.remove(this.dimLayer);
            this.labelLayer.remove(0); // remove the intro label

            this.initialBoard.removeMill(Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(1, 8))), Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(2, 8))), Integer.parseInt(String.valueOf(initialBoard.board.getIndexLookUpTable(3, 8))), this.initialBoard.millLayer);

            JLabel instruction = createInstruction(380, 125);
            instruction.setText("<html><center>When your opponent<br/>only has 2 tokens ...<center></html>");
            instruction.setLocation(575, 445);
            this.labelLayer.add(instruction);

            this.leftArrow.setBounds(600, 328, 100, 100);
            this.labelLayer.add(this.leftArrow);
        }
        else if (nextCount == 14) {
            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);

            this.labelLayer.remove(0); // remove the instruction label
            this.labelLayer.remove(this.leftArrow);

            JLabel whiteWin = createIntro("WIN");
            whiteWin.setIcon(new ImageIcon(getClass().getResource("/Icons/white-token.png")));
            whiteWin.setHorizontalTextPosition(JLabel.RIGHT);
            whiteWin.setIconTextGap(45); // set the distance between text and icon
        }
        else if (nextCount == 15) {
            initialBoard.getGame().getGameMode().displayBoardButton();

            this.labelLayer.remove(0); // remove the intro label
            createIntro("Button: Hint");


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
            this.labelLayer.remove(0); // remove the intro label
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
            this.labelLayer.remove(0); // remove the intro label
            createIntro("Example: Hint in Move/Fly Phase", 700, 110);
        }
        else if (nextCount == 18) {
            this.background.remove(this.dimLayer);
            this.labelLayer.remove(0); // remove the instruction label
            this.labelLayer.remove(this.click);
            this.remove(this.btnNext);

            JLabel instruction = createInstruction(300, 165);
            instruction.setText("<html><center><h1>Select your token</h1><h3>NOTE: This step is only<br/>needed in moving<br/> and flying phase</h3><center></html>");
            instruction.setLocation(450, 380);
            this.labelLayer.add(instruction);

            this.click.setBounds(405, 328, 48, 48);
            this.labelLayer.add(this.click);

            ActionListener intersectionTutAction = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    labelLayer.removeAll();

                    addNextButton(gbc);

                    repaint();
                    revalidate();
                }
            };

            ActionListener hintTutAction = (new ActionListener() {
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

                    initialBoard.board.getIntersectionList().get(21).inter.setEnabled(true);
                    initialBoard.board.getIntersectionList().get(21).inter.addActionListener(intersectionTutAction);

                    repaint();
                    revalidate();
                }
            });

            this.whiteToken_18.addMouseListener(this.whiteToken_18.tokenSelected);
            this.whiteToken_18.addMouseListener(new MouseAdapter() {
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
            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);

            createIntro("Button: Restart");
        }
        else if (nextCount == 20) {
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
                @Override
                public void actionPerformed(ActionEvent e) {
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

                        repaint();
                        revalidate();
                    }
                }
            });
        }
        else if (nextCount == 21) {
            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);

            createIntro("You are ready to play!");
        }
        else if (nextCount == 22) {
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

    private void restartBoard(){
        for (int i = 0; i < this.initialBoard.getTokenList().size(); i++){
            int layer = this.initialBoard.getTokenList().get(i).getCoordinateX();
            int position = this.initialBoard.getTokenList().get(i).getCoordinateY();
            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(layer, position))));
            this.initialBoard.placeToken.add(new JLabel(), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(layer, position))));
        }
        this.initialBoard.getTokenList().clear();
    }

    private void addToken(Token token){
        this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(token.getCoordinateX(), token.getCoordinateY()))));
        this.initialBoard.placeToken.add(token, Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(token.getCoordinateX(), token.getCoordinateY()))));
        this.initialBoard.getTokenList().add(token);
        token.setIndex(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(token.getCoordinateX(), token.getCoordinateY())))); // set the index of the white token
    }

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

    private JPanel createLabelLayer(){
        JPanel labelLayer = new JPanel();
        labelLayer.setLayout(null);
        labelLayer.setOpaque(false);
        labelLayer.setVisible(true);

        return labelLayer;
    }

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

    private JLabel createClickHint(){
        JLabel clickHint = new JLabel();
        clickHint.setOpaque(false);

        ImageIcon click = new ImageIcon(Tutorial.class.getResource("/Icons/click.png"));
        IconProcessor clickIcon = new IconProcessor(click, 48, 48);
        ImageIcon resizedIcon = clickIcon.resizeIcon();

        clickHint.setIcon(resizedIcon);

        return clickHint;
    }

    private JLabel createArrowLeft(){
        JLabel arrowLeft = new JLabel();
        arrowLeft.setOpaque(false);

        ImageIcon arrow = new ImageIcon(Tutorial.class.getResource("/Icons/arrow-left.png"));
        IconProcessor arrowIcon = new IconProcessor(arrow, 100, 100);
        ImageIcon resizedIcon = arrowIcon.resizeIcon();

        arrowLeft.setIcon(resizedIcon);

        return arrowLeft;
    }

    private JLabel createArrowRight(){
        JLabel arrowRight = new JLabel();
        arrowRight.setOpaque(false);

        ImageIcon arrow = new ImageIcon(Tutorial.class.getResource("/Icons/arrow-right.png"));
        IconProcessor arrowIcon = new IconProcessor(arrow, 100, 100);
        ImageIcon resizedIcon = arrowIcon.resizeIcon();

        arrowRight.setIcon(resizedIcon);

        return arrowRight;
    }

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
