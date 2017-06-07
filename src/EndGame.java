import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Andrew on 6/6/2017.
 */
public class EndGame extends BasicGameState {

    public static final int ID = 2;
    private StateBasedGame game;


    public int getID() {
        return ID;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = stateBasedGame;


    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        System.out.println("Entered EndGame State");
    }

    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    public void mouseClicked(int i, int i1, int i2, int i3) {
        System.out.println("mouse clicked");
    }

}
