package Zombies;

import Zombies.Zombie;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by rachel_chau on 6/7/17.
 */
public class Senior extends Zombie {

    public Senior(int x, int y, Image pic) throws SlickException {
        super(x, y, pic, 1, 150, 6);
    }

}
