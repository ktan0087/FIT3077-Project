package Frontend;

import javax.swing.*;
import java.awt.*;

public class Instruction extends JLabel {
    /**
     * Customized label for instruction
     */
    private InstructionType instructionType;
    public enum InstructionType{
        EMPTY,
        PLACE,
        MOVE,
        REMOVE,
        FLY
    }
    public Instruction(InstructionType instructionType){
        changeText(InstructionType.PLACE);
        this.setFont(new java.awt.Font("Inter", java.awt.Font.PLAIN, 36)); // set the font of label
        this.setForeground(new Color(0x000000)); // set the color of label
        this.setPreferredSize(new Dimension(500, 80));
        this.setHorizontalAlignment(JLabel.CENTER); // make the text at center
    }

    public void changeText(InstructionType instructionType){
        switch (instructionType){
            case EMPTY:
                this.setText("");
                break;
            case PLACE:
                this.setText("Place a token");
                break;
            case MOVE:
                this.setText("Move a token");
                break;
            case REMOVE:
                this.setText("Remove a token");
                break;
            case FLY:
                this.setText("Fly a token");
                break;
        }
    }
}
