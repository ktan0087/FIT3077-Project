package Frontend;

import Frontend.Game.Tutorial;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Click extends JLabel {
    public Click(){
        this.setOpaque(false);

        ImageIcon click = new ImageIcon(Tutorial.class.getResource("/Icons/click.png"));
        IconProcessor clickIcon = new IconProcessor(click, 48, 48);
        ImageIcon resizedIcon = clickIcon.resizeIcon();

        this.setIcon(resizedIcon);

        Timer timer = new Timer(500, new ActionListener() {
            Boolean clickVisible = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                clickVisible = !clickVisible;
                repaint();
            }
        });
        timer.start();

    }
}
