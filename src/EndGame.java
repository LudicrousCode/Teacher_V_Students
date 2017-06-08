import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Andrew on 6/6/2017.
 */
public class EndGame extends BasicGameState {

    public static final int ID = 2;
    private StateBasedGame game;
    private int rand;


    public int getID() {
        return ID;
    }

    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = stateBasedGame;
        rand = (int)(Math.random()*6);
    }

    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 1200, 800);

        if(rand == 0)
            graphics.drawImage(new Image("res/EndScreen/noob.png"), 568, 300);
        else if(rand == 1)
            graphics.drawImage(new Image("res/EndScreen/you_suck.png"), 542, 300);
        else if(rand == 2)
            graphics.drawImage(new Image("res/EndScreen/you_took_the_L.png"), 517, 300);
        else if(rand == 3)
            graphics.drawImage(new Image("res/EndScreen/get_rekt.png"), 545, 300);
        else if(rand == 4)
            graphics.drawImage(new Image("res/EndScreen/you're_bad.png"), 530, 300);
        else
            graphics.drawImage(new Image("res/EndScreen/you_should_really_reconsider_your_life_choices.png"), 218, 300);

        graphics.setColor(Color.blue);
        graphics.setLineWidth(5);
        graphics.drawRect(525, 600, 150, 50);
        graphics.drawString("RESTART LEVEL", 542, 617);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input a = gameContainer.getInput();
        if (a.isMousePressed(0)) {
            System.out.println("mouse clicked");
            if (a.getMouseX() >= 525 && a.getMouseX() <= 675 && a.getMouseY() >= 600 && a.getMouseY() <= 650)
                game.enterState(1);
        }
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
