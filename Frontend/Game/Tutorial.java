package Frontend.Game;

import Frontend.Button.BtnClose;
import Frontend.Button.BtnNext;
import Frontend.IconProcessor;
import Frontend.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tutorial extends JPanel{
    private int nextCount;
    private BtnNext btnNext;
    protected BtnClose btnClose;
    private InitialBoard initialBoard;
    private JPanel dimLayer;
    private JPanel labelLayer;
    private JPanel background;
    private WhiteToken whiteToken_37;
    public Tutorial(){
        this.nextCount = 0;
        this.btnClose = new BtnClose();
        this.btnNext = new BtnNext();
        this.initialBoard = new InitialBoard();
        this.labelLayer = createLabelLayer();
        this.dimLayer = createDimLayer();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Size gapSize = new Size(10, 25);
        gbc.insets = new Insets(gapSize.getHeight(), gapSize.getWidth(), gapSize.getHeight(), gapSize.getWidth()); // add gaps between the components

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
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

            createIntro("Place Token", gbc);
        }
        else if (this.nextCount == 2){
            this.labelLayer.remove(0); // remove intro
            this.background.remove(this.dimLayer);

            JLabel instruction = createInstruction(450, 80);
            instruction.setText("Click to place a token");

            Size instructionLocation = new Size(125, 165);
            gbc.insets = new Insets(instructionLocation.getHeight(), instructionLocation.getWidth(), instructionLocation.getHeight(), instructionLocation.getWidth());

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 0;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            gbc.fill = GridBagConstraints.NONE;
            this.add(instruction, gbc);
            this.setComponentZOrder(instruction, 2);

            Size clickLocation = new Size(540, 258);
            gbc.insets = new Insets(clickLocation.getHeight(), clickLocation.getWidth(), clickLocation.getHeight(), clickLocation.getWidth());

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 0;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            gbc.fill = GridBagConstraints.NONE;
            JLabel click = createClickHint();
            this.add(click, gbc);
            this.setComponentZOrder(click, 2);

            this.remove(this.btnNext); // remove next button

            this.initialBoard.board.getIntersectionList().get(8).inter.setEnabled(true);
            this.initialBoard.board.getIntersectionList().get(8).inter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(instruction);
                    remove(click);

                    Size gapSize = new Size(10, 25);
                    gbc.insets = new Insets(gapSize.getHeight(), gapSize.getWidth(), gapSize.getHeight(), gapSize.getWidth()); // add gaps between the components

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.weightx = 0;
                    gbc.weighty = 0;
                    gbc.anchor = GridBagConstraints.SOUTHEAST;
                    gbc.fill = GridBagConstraints.NONE;
                    add(btnNext, gbc);
                    setComponentZOrder(btnNext, 1);

                    repaint();
                    revalidate();
                }
            });
        }
        else if (this.nextCount == 3){
            initialBoard.getGame().getGameMode().displayBoardMove();

            JLabel instruction = createInstruction(350, 125);
            instruction.setText("<html><center>After you place all<br/>your tokens ...<center></html>");

            Size instructionLocation = new Size(45, 100);
            gbc.insets = new Insets(instructionLocation.getHeight(), instructionLocation.getWidth(), instructionLocation.getHeight(), instructionLocation.getWidth());

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 0;
            gbc.anchor = GridBagConstraints.SOUTHWEST;
            gbc.fill = GridBagConstraints.NONE;
            this.add(instruction, gbc);
            this.setComponentZOrder(instruction, 2);

            JLabel arrow = createArrowLeft();

            Size arrowLocation = new Size(115, 225);
            gbc.insets = new Insets(arrowLocation.getHeight(), arrowLocation.getWidth(), arrowLocation.getHeight(), arrowLocation.getWidth());

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 0;
            gbc.anchor = GridBagConstraints.SOUTHWEST;
            gbc.fill = GridBagConstraints.NONE;
            this.add(arrow, gbc);
            this.setComponentZOrder(arrow, 2);

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(1, 3))));
            this.initialBoard.placeToken.add(new BlackToken(1, 3, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(1, 3))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(2, 2))));
            this.initialBoard.placeToken.add(new BlackToken(2, 2, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(2, 2))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(3, 1))));
            this.initialBoard.placeToken.add(new BlackToken(3, 1, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(3, 1))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(2, 3))));
            this.initialBoard.placeToken.add(new WhiteToken(2, 3, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(2, 3))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(1, 8))));
            this.initialBoard.placeToken.add(new WhiteToken(1, 8, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(1, 8))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(2, 8))));
            this.initialBoard.placeToken.add(new BlackToken(2, 8, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(2, 8))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(3, 8))));
            this.initialBoard.placeToken.add(new BlackToken(3, 8, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(3, 8))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(3, 4))));
            this.initialBoard.placeToken.add(new WhiteToken(3, 4, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(3, 4))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(2, 4))));
            this.initialBoard.placeToken.add(new BlackToken(2, 4, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(2, 4))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(1, 4))));
            this.initialBoard.placeToken.add(new WhiteToken(1, 4, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(1, 4))));

            this.whiteToken_37 = new WhiteToken(3, 7, this.initialBoard);
            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(3, 7))));
            this.initialBoard.placeToken.add(whiteToken_37, Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(3, 7))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(2, 6))));
            this.initialBoard.placeToken.add(new WhiteToken(2, 6, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(2, 6))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(3, 5))));
            this.initialBoard.placeToken.add(new BlackToken(3, 5, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(3, 5))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(2, 5))));
            this.initialBoard.placeToken.add(new BlackToken(2, 5, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(2, 5))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(1, 7))));
            this.initialBoard.placeToken.add(new BlackToken(1, 7, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(1, 7))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(1, 6))));
            this.initialBoard.placeToken.add(new WhiteToken(1, 6, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(1, 6))));

            this.initialBoard.placeToken.remove(Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(1, 5))));
            this.initialBoard.placeToken.add(new WhiteToken(1, 5, this.initialBoard), Integer.parseInt(String.valueOf(this.initialBoard.board.getIndexLookUpTable(1, 5))));

            initialBoard.blackTokenRemain.setText("0");
            initialBoard.whiteTokenRemain.setText("0");
            initialBoard.playerTurn.changeIcon();
        }
        else if (this.nextCount == 4){
            // remove instruction and left arrow
            this.remove(2);
            this.remove(2);

            createIntro("Move Token", gbc);

            this.background.add(this.dimLayer);
            this.background.setComponentZOrder(this.dimLayer, 1);
        }
        else if (this.nextCount == 5){
            this.labelLayer.remove(0); // remove intro label
            this.background.remove(this.dimLayer);
            this.remove(this.btnNext); // remove next button

            JLabel instruction = createInstruction(350, 125);
            instruction.setText("<html><center>Select the token that<br/>you want to move<center></html>");

            // Set the location for the instruction
            Size instructionLocation = new Size(332, 275);
            gbc.insets = new Insets(instructionLocation.getHeight(), instructionLocation.getWidth(), instructionLocation.getHeight(), instructionLocation.getWidth());

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            gbc.fill = GridBagConstraints.NONE;

            this.labelLayer.add(instruction, gbc);

            // Set the location for the clicking arrow
            Size clickLocation = new Size(548, 248);
            gbc.insets = new Insets(clickLocation.getHeight(), clickLocation.getWidth(), clickLocation.getHeight(), clickLocation.getWidth());

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 0;
            gbc.anchor = GridBagConstraints.SOUTHWEST;
            gbc.fill = GridBagConstraints.NONE;
            JLabel click = createClickHint();
            this.labelLayer.add(click, gbc);


        }
    }

    private JPanel createBackground(){
        JPanel background = new JPanel(){
            @Override
            public boolean isOptimizedDrawingEnabled(){
                return false;
            }
        };
        background.setLayout(new OverlayLayout(background));

        this.initialBoard.setEnabled(false); // make buttons enabled
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
        labelLayer.setLayout(new GridBagLayout());
        labelLayer.setOpaque(false);
        labelLayer.setVisible(true);

        return labelLayer;
    }

    private JPanel createWelcomeMessage(){
        JLabel welcome = new JLabel();
        welcome.setText("Welcome to the tutorial");
        welcome.setFont(new Font("Inter", Font.PLAIN, new Size(42, 42).getHeight()));
        welcome.setBackground(new Color(0xF4E3D3));
        welcome.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0xE27408)));
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setOpaque(true);

        JLabel message = new JLabel();
        message.setText("You are ");
        message.setFont(new Font("Inter", Font.PLAIN, new Size(42, 42).getHeight()));
        message.setBackground(new Color(0xF4E3D3));
        message.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0xE27408)));
        message.setOpaque(true);

        ImageIcon whiteToken = new ImageIcon(Tutorial.class.getResource("/Icons/white-token.png"));
        IconProcessor icon = new IconProcessor(whiteToken, 68, 68);
        ImageIcon resizedIcon = icon.resizeIcon();

        Size size = new Size(500, 110);
        welcome.setPreferredSize(new Dimension(size.getWidth(), size.getHeight()));
        message.setPreferredSize(new Dimension(size.getWidth(), size.getHeight()));

        message.setIcon(resizedIcon);
        message.setHorizontalTextPosition(JLabel.LEFT);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setIconTextGap(45); // set the distance between text and icon

        JPanel welcomeMessage = new JPanel();
        welcomeMessage.setOpaque(false);
        welcomeMessage.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Size gapSize = new Size(25, 25);
        gbc.insets = new Insets(gapSize.getHeight(), gapSize.getWidth(), gapSize.getHeight(), gapSize.getWidth()); // add gaps between the components

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

    private JLabel createIntro(String text, GridBagConstraints gbc){
        JLabel intro = new JLabel();
        intro.setFont(new Font("Inter", Font.PLAIN, new Size(42, 42).getHeight()));
        intro.setText(text);
        intro.setBackground(new Color(0xF4E3D3));

        intro.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0xE27408)));
        intro.setHorizontalAlignment(JLabel.CENTER);
        intro.setOpaque(true);

        Size size = new Size(500, 110);
        intro.setPreferredSize(new Dimension(size.getWidth(), size.getHeight()));

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        this.labelLayer.add(intro, gbc);

        return intro;
    }

    private JLabel createInstruction(int width, int height){
        JLabel instruction = new JLabel();
        instruction.setFont(new Font("Inter", Font.PLAIN, new Size(36, 36).getHeight()));
        instruction.setBackground(new Color(0xF4E3D3));
        instruction.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0xFF0000)));
        instruction.setHorizontalAlignment(JLabel.CENTER);
        instruction.setOpaque(true);

        Size size = new Size(width, height);
        instruction.setPreferredSize(new Dimension(size.getWidth(), size.getHeight()));

//        GridBagConstraints gbc = new GridBagConstraints();
//
//        gbc.insets = new Insets(0, 0, 0, 0);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.weightx = 1;
//        gbc.weighty = 1;
//        gbc.anchor = GridBagConstraints.NORTH;
//        gbc.fill = GridBagConstraints.NONE;
//        this.labelLayer.add(instruction, gbc);

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

}
