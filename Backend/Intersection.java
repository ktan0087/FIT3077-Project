package Backend;

import java.util.ArrayList;
import java.util.Arrays;

public class Intersection {
    /**
     * Private attributes of Intersection
     */
    private int layer;
    private int position;
    private Token token = null;

    /**
     * Constructor
     * Function to initialise Intersection
     * @param layer = x-coordinate of Intersection
     * @param pos = y-coordinate of Intersection
     */
    public Intersection(int layer, int pos){
        this.layer = layer;
        this.position = pos;
    }

    /**
     * Getter function to get the layer of the Intersection
     * @return current layer
     */
    public int getLayer() {
        return layer;
    }

    /**
     * Getter function to get the position of Intersection
     * @return current position
     */
    public int getPosition() {
        return position;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int setPosition(int position) {
        return this.position = position;
    }

    /**
     * Function to set token on the intersection
     * @param token
     */
    public void addToken(Token token){
        this.token = token;
    }

    /**
     * Function to remove token from intersection
     */
    public void removeToken(){
        this.token = null;
    }

    /**
     * Getter function to get the token on that intersection
     * @return token on the intersection
     */
    public Token getToken() {
        return token;
    }

    /**
     * Function to check if the two intersection is next to each other
     * @param other = Intersection used to compared to current intersection
     * @return true if the Intersections are adjacent to the other intersection
     *         false if the Intersections are not adjacent to the other intersection
     */
    public boolean isAdjacent(Intersection other){
        boolean flag = false;
        //get the difference between the layer and position of the two intersections
        int dx = Math.abs(this.layer - other.layer);
        int dy = Math.abs(this.position - other.position);
        //check if both intersection are on the same layer
        if (dx == 0) {
            //check if the difference is 1 or 7
            if (dx+dy == 1 || dx+dy == 7)
            flag = true;
        }
        //check if both intersection are on the same position
        //and the position is even
        else if (dy == 0 && position % 2 == 0){
            flag = true;
        }
        return flag;
    }

    /**
     * Function that check if the intersection is empty
     * @return true if intersection is empty
     *         false if the intersection is not empty
     */
    public boolean isEmpty(){
        boolean flag = true;
        //check if the intersection has a token
        if (this.token != null){
            flag = false;
        }
        return flag;
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "layer=" + layer +
                ", position=" + position +
                ", token=" + getToken() +
                '}';
    }

//    public static void main(String[] args) {
//        Intersection i2 = new Intersection(1, 1);
//        i2.addToken(new Token(TokenColour.PLAYER_1_WHITE));
//        Intersection i1 = new Intersection(1, 3);
//        i1.getAdjacentIntersection();
//    }
}
