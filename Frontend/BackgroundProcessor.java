package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BackgroundProcessor extends JLabel{
    private Image image;
    private int imageWidth;
    private int imageHeight;

    public BackgroundProcessor(Image image, int imageWidth, int imageHeight) {
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            int availableWidth = getWidth();
            int availableHeight = getHeight();

            int x = (availableWidth - imageWidth) / 2;
            int y = (availableHeight - imageHeight) / 2;

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(image, x, y, imageWidth, imageHeight, null);
            g2d.dispose();
        }
    }
}
