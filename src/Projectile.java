import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by andrew_briasco on 6/2/17.
 */
public class Projectile extends Sprite {

    private int damage;


    public Projectile(int x, int y, Image pic, int damage) throws SlickException{
        super(x, y);
        this.setPic(pic);
    }

    public int getDamage() {
        return damage;
    }
}
