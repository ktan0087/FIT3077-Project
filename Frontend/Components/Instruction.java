package Frontend.Components;

import javax.swing.*;
import java.awt.*;

/**
 * A class to represent the instruction label that will show at the top-middle section of the game board
 * This instruction label is used to tell the player what to do next
 */

public class Instruction extends JLabel {
    /**
     * The type of instruction that will be shown on the label
     */
    private InstructionType instructionType;

    /**
     * An enum to represent the type of instruction that will be shown on the label
     */
    public enum InstructionType{
        EMPTY,
        PLACE,
        MOVE,
        REMOVE,
        FLY
    }

    /**
     * A constructor to create an instruction label.
     * The label will show the PLACE instruction by default.
     *
     * @param instructionType the type of instruction that will be shown on the label
     */
    public Instruction(InstructionType instructionType){
        changeText(InstructionType.PLACE); // set the text of label to PLACE
        this.setFont(new java.awt.Font("Inter", java.awt.Font.PLAIN, 36)); // set the font of label
        this.setForeground(new Color(0x000000)); // set the color of label
        this.setPreferredSize(new Dimension(500, 80)); // set the size of label
        this.setHorizontalAlignment(JLabel.CENTER); // make the text at center
    }

    /**
     * A method to change the text of the label based on the instruction type.
     *
     * @param instructionType is the type of instruction that will be shown on the label, which includes:
     *                        EMPTY, PLACE, MOVE, REMOVE, FLY
     */
    public void changeText(InstructionType instructionType){
        switch (instructionType){
            case EMPTY:
                this.setText(""); // set the text of label to empty
                break;
            case PLACE:
                this.setText("Place a token");
                break;
            case MOVE:
                this.setText("Move a token");
                break;
            case REMOVE:
                this.setText("Remove opponent's token");
                break;
            case FLY:
                this.setText("Fly a token");
                break;
        }
    }
}
