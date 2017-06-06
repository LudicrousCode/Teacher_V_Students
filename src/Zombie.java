/**
 * Created by rachel_chau on 6/2/17.
 */
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Zombie extends Sprite{

    private int speed, HP, damage;

    public Zombie(int x, int y, Image pic, int speed, int HP, int damage) throws SlickException {
        super(x, y);
        this.setPic(pic);
        this.speed = speed;
        this.HP = HP;
        this.damage = damage;
    }

//    public void draw(){
//
//    }

    public void move(){
        setX(getX()-speed);
    }


    public boolean dead(){
       return (HP <= 0);
    }

    public void takeDamage(int d){
        HP-= d;
    }

    public int getDamage(){
        return damage;
    }

}
