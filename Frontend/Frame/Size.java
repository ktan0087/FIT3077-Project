package Frontend.Frame;

import java.awt.*;

public class Size {
    private Dimension dimension;
    private int width;
    private int height;

    public Size(int width, int height) {
//        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
//        this.dimension = MainFrame.getWindows()[0].getSize();
        this.dimension =new Dimension(1904, 1041);
        this.width = (width * dimension.width) / 1266;
        this.height = (height * dimension.height) / 683;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
