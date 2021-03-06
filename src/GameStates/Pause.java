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
    private String volume, sound;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        this.game = stateBasedGame;
        volume = "ON";
        sound = "ON";
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.setColor(new Color(80, 80, 80));
        graphics.fillRect(0, 0, 1200, 800);
        graphics.setColor(Color.black);
        graphics.fillRect(300, 200, 600, 400);
        graphics.drawImage(new Image("res/Menu/options.png"), 505, 210);
        graphics.drawImage(new Image("res/Menu/music_volume.png"), 350, 280);
        graphics.drawImage(new Image("res/Menu/sound_fx.png"), 410, 337);

        //sound fx and music volume
        graphics.setColor(Color.green);
        graphics.setLineWidth(5);
        graphics.drawRect(300, 200, 600, 400);

        graphics.setColor(Color.darkGray);
        graphics.fillRect(554, 300, 40, 20);
        graphics.fillRect(554, 350, 40, 20);

        graphics.setColor(Color.green);
        graphics.drawString(volume, 559, 301);
        graphics.drawString(sound, 559, 351);


        graphics.drawImage(new Image("res/Menu/back_to_game.png"), 382, 450);
        graphics.drawImage(new Image("res/Menu/restart_level.png"), 656, 450);
        graphics.drawImage(new Image("res/Menu/main_menu.png"), 405, 525);

        //buttons
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
            if (a.getMouseX() >= 380 && a.getMouseX() <= 550 && a.getMouseY() >= 450 && a.getMouseY() <= 490)
                game.enterState(1);
            if(a.getMouseX() >= 380 && a.getMouseX() <= 550 && a.getMouseY() >= 525 && a.getMouseY() <= 565)
                game.enterState(3);
            if(a.getMouseX() >= 650 && a.getMouseX() <= 820 && a.getMouseY() >= 450 && a.getMouseY() <= 490)
                game.enterState(1);

            if(a.getMouseX() >= 554 && a.getMouseX() <= 594){
                if(a.getMouseY() >= 300 & a.getMouseY() <= 320){
                    if(volume.equals("ON")){
                        volume = "OFF";
                    }
                    else {
                        volume = "ON";
                    }
                }
                if(a.getMouseY() >= 350 && a.getMouseY() <= 370){
                    if(volume.equals("ON")){
                        volume = "OFF";
                    }
                    else {
                        volume = "ON";
                    }
                }
            }
        }
    }

    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        System.out.println("Entered Pause State");
//        genZombies();
    }

    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException{
        System.out.println("Left Pause State");
    }
}
