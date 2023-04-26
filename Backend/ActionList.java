package Backend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActionList {

    private final Set<AllActions> actionSet = new HashSet<AllActions>();


    public boolean allowedActions(AllActions actions){
        return actionSet.contains(actions);
    }

    public void addAction(AllActions actions){
        if(!allowedActions(actions)) {
            actionSet.add(actions);
        }
    }

    public void removeAction(AllActions actions){
        if(allowedActions(actions)) {
            actionSet.remove(actions);
        }
    }

    public List<AllActions> actionList (){
        return List.copyOf(actionSet);
    }

}
