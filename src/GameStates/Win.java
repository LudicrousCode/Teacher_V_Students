package GameStates;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by sarah_zhang on 6/12/17.
 */
public class Win extends BasicGameState{

    public static final int ID = 5;
    private StateBasedGame game;
    private Sound win;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = stateBasedGame;
        win = new Sound("res/Sounds/zombiesOnYourLawn1.wav");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, 1200, 800);
        graphics.drawImage(new Image("res/you_win.png"), 480, 200);
        graphics.drawImage(new Image("res/Menu/main_menu.png"), 539, 400);
        graphics.setColor(Color.blue);
        graphics.setLineWidth(3);
        graphics.drawRect(539, 400, 123, 40);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input a = gameContainer.getInput();
        if (a.isMousePressed(0)){
            if(a.getMouseX() >= 539 && a.getMouseX() <= 662 && a.getMouseY() >= 400 && a.getMouseY() <= 440)
                game.enterState(3);
        }
    }

    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        System.out.println("Entered Win State");
        win.play();

    }
}
