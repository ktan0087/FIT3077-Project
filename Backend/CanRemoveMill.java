package Backend;

import java.util.ArrayList;

public interface CanRemoveMill {
    ArrayList<Mill> removeMillList = new ArrayList<>();

    void addRemoveMill();

    ArrayList<Mill> getRemoveMillList();
}
