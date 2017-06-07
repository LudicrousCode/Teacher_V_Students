import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by sarah_zhang on 6/7/17.
 */
public class Logo extends BasicGameState {

    public static final int ID = 3;
    private StateBasedGame game;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = stateBasedGame;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 1200, 800);
        graphics.drawImage(new Image("res/whs.png"), 100, 100);

        graphics.drawImage(new Image("res/logo.png"), 404, 15);
        graphics.setColor(Color.blue);
        graphics.setLineWidth(5);

        graphics.drawRect(550, 725, 100, 50);
        graphics.drawString("PLAY", 581, 742);

        graphics.drawRect(200, 725, 100, 50);
        graphics.drawString("SHOP", 231, 742);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input a = gameContainer.getInput();
        if (a.isMousePressed(0)) {
            System.out.println("mouse clicked");
            if (a.getMouseX() >= 550 && a.getMouseX() <= 650 && a.getMouseY() >= 725 && a.getMouseY() <= 775)
                game.enterState(1);
        }
    }
}
