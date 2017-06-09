package GameStates;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import Setup.*;
import Towers.*;
import Zombies.*;

/**
 * Created by Andrew on 6/6/2017.
 */
public class Title extends BasicGameState{

    public static final int ID = 0;
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
        graphics.setColor(Color.white);

        graphics.drawString("On a warm and sunny April Fools' Day, Mr. Hopps decides to pull an epic prank on the students.", 100, 100);
        graphics.drawString("The only problem is, it's a Saturday, so there's no school. What should he do?", 100, 150);
        graphics.drawString("Hopps creates a computer virus that will spam all the student computers with comp sci memes.", 100, 200);
        graphics.drawString("The student computers are infected with the virus, which goes into effect soon after.", 100, 250);
        graphics.drawString("There are so many memes that most of the computers either crash, or the memes block all other windows.", 100, 300);
        graphics.drawString("The students are angry and confused, but when there are memes roasting the students they guess who did it.", 100, 350);
        graphics.drawString("On the next Monday morning, as Hopps walks to his classroom, he hears the students talking about the virus.", 100, 400);
        graphics.drawString("One of them mentions that Hopps did it. They all turn to see Hopps and begin chasing after him.", 100, 450);
        graphics.drawString("Luckily, Hopps has enough of a headstart to blockade himself in his classroom before all the students attack him...", 100, 500);

        graphics.setColor(Color.blue);
        graphics.setLineWidth(5);
        graphics.drawRect(550, 600, 100, 50);
        graphics.drawString("NEXT", 581, 617);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
//        game.enterState(1);
        Input a = gameContainer.getInput();
        if (a.isMousePressed(0)) {
            System.out.println("mouse clicked");
            if (a.getMouseX() >= 550 && a.getMouseX() <= 650 && a.getMouseY() >= 600 && a.getMouseY() <= 650)
                game.enterState(3);
        }
    }

    @Override
    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        System.out.println("Entered Title State");
    }

    @Override
    public void leave(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        System.out.println("Left Title State");
    }


    //Andrew doesn't understand the purpose of this, doesn't run when mouse is clicked? How is it supposed to work?
    @Override
    public void mouseClicked(int button, int x, int y, int i3) {
    }
}