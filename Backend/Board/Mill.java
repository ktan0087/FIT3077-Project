package Backend.Board;

import Backend.Board.Intersection;
import Backend.Player;

import java.util.ArrayList;

public class Mill {
    /**
     * Private attributes of Mill
     */
    private ArrayList<Intersection> intersectionList = new ArrayList<>();
    private Intersection intersection[][] = new Intersection[4][9];
    private Player player;
    private boolean isDrawn = false;

    /**
     * Constructor
     * Function to initialise Mill
     * @param intersections = array of intersectionList that form a mill
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

    public void setDrawn(boolean drawn) {
        isDrawn = drawn;
    }

    public boolean getIsDrawn(){
        return isDrawn;
    }

    /**
     * Getter function to get the player who owns the mill
     * @return player who owns the mill
     */
    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Mill{" +
                "intersectionList=" + intersectionList +
//                ", player=" + player.toString() +
                '}';
    }
}
