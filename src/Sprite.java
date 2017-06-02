import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by andrew_briasco on 6/1/17.
 */
public class Sprite{
    private int x, y;
    private Image pic;
    public Sprite(int x, int y, Image pic)throws SlickException{
        this.pic = pic;
        this.x = x;
        this.y = y;


    }

}
