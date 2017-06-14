package Zombies;

import Zombies.Zombie;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by rachel_chau on 6/7/17.
 */
public class Freshman extends Zombie {

    public Freshman(int x, int y, Image pic) throws SlickException {
        super(x, y, pic, 3, 80, 20);
    }

}
