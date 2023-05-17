package Backend;
import Backend.*;
import Frontend.*;

import javax.swing.*;

/**
 * This class is used to run the game.
 */
public class Application {

    public static void main(String[] args) {
        //invokeLater() method will not wait for the block of code inside the Runnable to execute.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Layout();
            }
        });
    }
}
