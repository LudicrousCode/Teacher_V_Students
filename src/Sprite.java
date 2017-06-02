import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by andrew_briasco on 6/1/17.
 */
public class Sprite{
    private int x, y, speed;
    private String pic;

    public Sprite(int x, int y, Image pic)throws SlickException{
        this.pic = pic;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
