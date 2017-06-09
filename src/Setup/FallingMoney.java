package Setup;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by sarah_zhang on 6/8/17.
 */
public class FallingMoney extends Sprite{

    private int speed;

    public FallingMoney(int x, int y, int speed) throws SlickException {
        super(x, y);
        setPic(new Image("res/money.png"));
        this.speed = speed;
    }

    public void move(){
        setY(getY() + speed);
    }
}
