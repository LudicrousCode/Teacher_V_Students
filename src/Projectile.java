import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by andrew_briasco on 6/2/17.
 */
public class Projectile extends Sprite {

    private int damage, speed;
    private Image pic;


    public Projectile(int x, int y, Image pic, int damage, int s) throws SlickException{
        super(x, y);
        this.setPic(pic);
        speed = s;
        this.pic = pic;

    }

    public int getDamage() {
        return damage;
    }

    public void move(){
        this.setX(this.getX()+speed);
    }
    public Image getImage (){
        return pic;
    }
}
