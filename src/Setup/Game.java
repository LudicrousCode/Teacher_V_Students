package Setup;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by andrew_briasco on 5/8/17.
 */
public class Game extends StateBasedGame {
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static final boolean FULLSCREEN = false;

    public static final String GAME_NAME = "Teacher V Students";

    public Game(){
        super(GAME_NAME);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {

    }

    public static void main(String[] args) throws SlickException{
        AppGameContainer app = new AppGameContainer(new Game());

        //set the size of the display to the width and height and fullscreen or not
        app.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, FULLSCREEN);
        //this will attempt to create a framerate of approximately 60 frames per second
        app.setTargetFrameRate(60);

        app.start();
    }
}
