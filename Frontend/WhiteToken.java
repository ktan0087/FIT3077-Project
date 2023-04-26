package Frontend;

import java.awt.*;

public class WhiteToken extends Token{
    public WhiteToken(int coordinateX, int coordinateY, InitialBoard initialBoard) {
        super(coordinateX, coordinateY, initialBoard);
        this.tokenColor = new Color(0xF5F5DC);
    }
}
