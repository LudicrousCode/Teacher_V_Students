import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by clarissa_briasco on 6/1/17.
 */
public class Tower extends Sprite{

    /**
     * This class will serve as the base class for all defense towers (passive and active)
     * IDK if it'll be abstract or not
     * This class will extend Sprite
     */

    private int price, damage, health;
    private Rectangle boundRect;

    public Tower(int price, int damage, Image pic, int x, int y) throws SlickException {
        super(x, y, pic);
        this.price = price;
        this.damage = damage;
        boundRect = new Rectangle(x*100, y*100, pic.getWidth(), pic.getHeight());

    }


}
