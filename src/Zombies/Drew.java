package Zombies;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by clarissa_briasco on 6/13/17.
 */
public class Drew extends Zombie{

    public Drew(int x, int y) throws SlickException {
        super(x, y, new Image("res/zombies/drew.png"), 1, 120, 30);
        //x, y, picture, speed, HP, damage
    }

}
