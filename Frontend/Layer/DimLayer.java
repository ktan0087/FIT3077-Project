package Frontend.Layer;

import javax.swing.*;
import java.awt.*;

public class DimLayer extends JPanel {
    public DimLayer() {
        this.setOpaque(false);
        this.setVisible(true);
    }

    protected void paintComponent(Graphics g){
        g.setColor(new Color(0x80000000, true));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
