package Frontend;

import Frontend.Frame.Size;

import javax.swing.*;
import java.awt.*;

public class IconProcessor {
    private ImageIcon icon;
    private int width;
    private int height;
    private Size size;

    public IconProcessor(ImageIcon icon, int width, int height) {
        this.icon = icon;
        this.size = new Size(width, height);
        this.width = this.size.getWidth();
        this.height = this.size.getHeight();
    }

    public ImageIcon resizeIcon() {
        Image image = this.icon.getImage();
        Image resizedImage = image.getScaledInstance(this.width, this.height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
