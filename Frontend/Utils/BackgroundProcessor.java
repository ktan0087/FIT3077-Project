package Frontend.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * This class is used to create the background processor for the main page to resize the background image
 */
public class BackgroundProcessor extends JLabel{
    /**
     * The image of the background
     */
    private Image image;

    /**
     * The width of the image
     */
    private int imageWidth;

    /**
     * The height of the image
     */
    private int imageHeight;

    /**
     * Constructor.
     * Creates a new background processor with an image, image width and image height.
     *
     * @param image the image of the background
     * @param imageWidth the width of the image
     * @param imageHeight the height of the image
     */
    public BackgroundProcessor(Image image, int imageWidth, int imageHeight) {
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;

        this.addComponentListener(new ComponentAdapter() {
            /**
             * Invoked when the component's size changes.
             *
             * @param e the event to be processed
             */
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }

    /**
     * Paints the background image.
     * This method is invoked when the background processor is resized.
     * The background image will be resized to fit the size given in the background processor.
     * The background image will be centered in the frame.
     *
     * @param g the graphics context
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            int availableWidth = getWidth(); // get the width of the frame
            int availableHeight = getHeight(); // get the height of the frame

            int x = (availableWidth - imageWidth) / 2; // center the x coordinate of the image
            int y = (availableHeight - imageHeight) / 2; // center the y coordinate of the image

            Graphics2D g2d = (Graphics2D) g.create(); // create a new graphics context
            g2d.drawImage(image, x, y, imageWidth, imageHeight, null); // draw the image
            g2d.dispose(); // dispose the graphics context
        }
    }
}
