public class Intersection {

    private final int layer;
    private final int position;

    //Constructor
    public Intersection(int layer, int pos){
        this.layer = layer;
        this.position = pos;
        //token
    }

    //Getters
    public int getLayer() {
        return layer;
    }

    public int getPosition() {
        return position;
    }

    public boolean isAdjacent(Intersection other){
        boolean flag = false;
        int dx = Math.abs(this.layer - other.layer);
        int dy = Math.abs(this.position - other.position);

        if (dx+dy == 1 || dx+dy == 7){
            flag = true;
        }
        else if (dx != 0 && position % 2 == 1){
            if (this.position == other.position)
                flag = true;
        }

        return flag;
    }

    //A function to check if this intersection is empty
}
