package Frontend;

import javax.swing.*;
import java.awt.*;

public class IconProcessor {
    private ImageIcon icon;
    private int width;
    private int height;

    public IconProcessor(ImageIcon icon, int width, int height) {
        this.icon = icon;
        this.width = width;
        this.height = height;
    }

    public ImageIcon resizeIcon() {
        Image image = this.icon.getImage();
        Image resizedImage = image.getScaledInstance(this.width, this.height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
