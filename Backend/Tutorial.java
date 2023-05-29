package Backend;

public class Tutorial extends Game{

    public Tutorial() {
        super();
        setGameMode(new TutorialMode(this));
    }

}
