package Zombies;

import Zombies.Zombie;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by rachel_chau on 6/7/17.
 */
public class Senior extends Zombie {

    public Senior(int x, int y) throws SlickException {
        super(x, y, new Image("res/zombies/senior.png"), 1, 150, 6);
    }

}
