package Frontend.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used to create the icon processor to resize the icon.
 */
public class IconProcessor {
    /**
     * The icon to be resized
     */
    private ImageIcon icon;

    /**
     * The width of the icon
     */
    private int width;

    /**
     * The height of the icon
     */
    private int height;

    /**
     * Constructor.
     * Creates a new icon processor with an icon, width and height.
     *
     * @param icon the icon to be resized
     * @param width the width of the icon that is to be resized
     * @param height the height of the icon that is to be resized
     */
    public IconProcessor(ImageIcon icon, int width, int height) {
        this.icon = icon;
        this.width = width;
        this.height = height;
    }

    /**
     * Resizes the icon.
     *
     * @return the resized icon
     */
    public ImageIcon resizeIcon() {
        Image image = this.icon.getImage(); // transform the image icon to an image
        Image resizedImage = image.getScaledInstance(this.width, this.height, Image.SCALE_SMOOTH); // resize the image
        return new ImageIcon(resizedImage); // transform the image back to an image icon
    }
}
