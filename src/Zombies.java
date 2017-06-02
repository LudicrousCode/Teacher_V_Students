/**
 * Created by rachel_chau on 6/2/17.
 */
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Zombies extends Sprite{

    private int speed, HP;

    public Zombies(int x, int y, Image pic, int speed, int HP) throws SlickException {
        super(x, y);
        this.setPic(pic);
        this.speed = speed;
        this.HP = HP;
    }

    public void draw(){

    }

    public void move(){
        setX(getX()-speed);
    }

    public boolean dead(){
        if(HP <= 0){
            return true;
        }
        return false;
    }

}
