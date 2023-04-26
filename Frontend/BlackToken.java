package Frontend;

import java.awt.*;

public class BlackToken extends Token{
    public BlackToken(int coordinateX, int coordinateY, InitialBoard iniBoard) {
        super(coordinateX, coordinateY, iniBoard);
        this.tokenColor = new Color(0x4D4D4D);
    }
}
