package Frontend;

import java.awt.*;

public class WhiteToken extends Token{
    public WhiteToken(int coordinateX, int coordinateY) {
        super(coordinateX, coordinateY);
        this.tokenColor = new Color(0xF5F5DC);
    }
}
