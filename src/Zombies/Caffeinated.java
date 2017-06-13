package Zombies;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by clarissa_briasco on 6/13/17.
 */
public class Caffeinated extends Zombie {

    public Caffeinated(int x, int y) throws SlickException{
        super(x, y, new Image("res/zombies/cafZombie.png"), 1, 100, 25);
        //x, y, picture, speed, HP, damage
    }

}
