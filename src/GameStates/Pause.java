package GameStates;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Towers.*;
import Zombies.*;
import Towers.*;

/**
 * Created by sarah_zhang on 6/9/17.
 */
public class Pause extends BasicGameState{
    public static final int ID = 4;
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
        graphics.setColor(new Color(80, 80, 80));
        graphics.fillRect(0, 0, 1200, 800);
        graphics.setColor(Color.black);
        graphics.fillRect(300, 200, 600, 400);
        graphics.drawImage(new Image("res/Menu/options.png"), 505, 210);
        graphics.drawImage(new Image("res/Menu/music_volume.png"), 350, 280);
        graphics.drawImage(new Image("res/Menu/sound_fx.png"), 350, 337);

        graphics.setColor(Color.green);
        graphics.setLineWidth(5);
        graphics.drawRect(300, 200, 600, 400);

        graphics.drawImage(new Image("res/Menu/back_to_game.png"), 382, 450);
        graphics.drawImage(new Image("res/Menu/restart_level.png"), 656, 450);
        graphics.drawImage(new Image("res/Menu/main_menu.png"), 405, 525);

        graphics.setColor(Color.blue);
        graphics.setLineWidth(3);
        graphics.drawRect(380, 450, 170, 40);
        graphics.drawRect(650, 450, 170, 40);
        graphics.drawRect(380, 525, 170, 40);
        graphics.drawRect(650, 525, 170, 40);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input a = gameContainer.getInput();
        if (a.isMousePressed(0)) {
            //back to game
            if (a.getMouseX() >= 380 && a.getMouseX() <= 550 && a.getMouseY() >= 170 && a.getMouseY() <= 210)
                game.enterState(1);
            if(a.getMouseX() >= 380 && a.getMouseX() <= 550 && a.getMouseY() >= 525 && a.getMouseY() <= 565)
                game.enterState(3);
            if(a.getMouseX() >= 650 && a.getMouseX() <= 820 && a.getMouseY() >= 170 && a.getMouseY() <= 210)
                game.enterState(1);
        }
    }

}
