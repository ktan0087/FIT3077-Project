package Frontend;

import Frontend.Button.BtnClose;
import Frontend.Game.InitialBoard;

import javax.swing.*;
import java.awt.*;

public class Tutorial extends JPanel{
    protected JButton btnClose;
    public Tutorial(){
        this.btnClose = new BtnClose();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(createWelcomeMessage(), gbc);

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
        this.add(createNextButton(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(createBackground(), gbc);
    }

    private static JPanel createBackground(){
        JPanel background = new JPanel(){
            @Override
            public boolean isOptimizedDrawingEnabled(){
                return false;
            }
        };
        background.setLayout(new OverlayLayout(background));

        JPanel initialBoard = new InitialBoard();
        JPanel dimLayer = createDimLayer();

        background.add(dimLayer);
        background.add(initialBoard);

        return background;
    }

    private static JPanel createDimLayer(){
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

    private static JPanel createWelcomeMessage(){
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

    private static JButton createNextButton(){
        JButton next = new JButton();

        ImageIcon whiteToken = new ImageIcon(Tutorial.class.getResource("/Icons/next.png"));
        IconProcessor icon = new IconProcessor(whiteToken, 100, 100);
        ImageIcon resizedIcon = icon.resizeIcon();
        next.setIcon(resizedIcon);
        next.setFocusable(false);
        next.setOpaque(false);
        next.setContentAreaFilled(false);
        next.setBorderPainted(false);

        return next;
    }

}
