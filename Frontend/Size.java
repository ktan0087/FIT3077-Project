package Frontend;

import Frontend.Frame.MainFrame;

import java.awt.*;

public class Size {
    private Dimension dimension;
    private int width;
    private int height;

    public Size(int width, int height) {
        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = (width * dimension.width) / 1280;
        this.height = (height * dimension.height) / 720;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
