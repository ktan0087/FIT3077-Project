package Backend.Board;

import Backend.Board.Intersection;
import Backend.Player;

import java.util.ArrayList;

/**
 * This class represents a mill object on the board
 * A mill object is formed by 3 intersections
 *
 * @see Backend.Board.Intersection
 * @see Backend.Board.Board
 * @see Backend.Board.Mill
 * @see Backend.Action.MoveTokenAction
 * @see Backend.Action.RemoveTokenAction
 */
public class Mill {
    /**
     * Private attributes of Mill
     */
    private ArrayList<Intersection> intersectionList = new ArrayList<>();
    private Intersection intersection[][] = new Intersection[4][9];
    private Player player;

    /**
     * Constructor
     * Function to initialise Mill
     * @param firstIntersection, secondIntersection, thirdIntersection = intersections that form a mill
     * @param player = player who owns the mill
     */
    public Mill(Intersection firstIntersection, Intersection secondIntersection, Intersection thirdIntersection, Player player){
        this.intersectionList.add(firstIntersection);
        this.intersectionList.add(secondIntersection);
        this.intersectionList.add(thirdIntersection);
        this.player = player;
    }

    /**
     * Getter function to get the intersectionList of the mill
     * @return array of intersectionList that form a mill
     */
    public ArrayList<Intersection> getIntersection() {
        return intersectionList;
    }

    /**
     * Getter function to get the player who owns the mill
     * @return player who owns the mill
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Fucntion to print Mill object
     * @return
     */
    @Override
    public String toString() {
        return "Mill{" +
                "intersectionList=" + intersectionList +
//                ", player=" + player.toString() +
                '}';
    }
}
